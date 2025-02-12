/**
 * @author Randy Fu randyf333
 * @version 11/10/24
 */
public class MemManager {
    private DoubleLinkedList<Block> freelist;
    private byte[] memory;
    private int blockSize;

    /**
     * Constructor
     * 
     * @param memorySize
     *            Size of memory in bytes
     */
    public MemManager(int memorySize) {
        Block b = new Block(0, memorySize);
        freelist = new DoubleLinkedList<Block>();
        freelist.insertNode(new DLLNode<Block>(b));
        memory = new byte[memorySize];
        blockSize = memorySize;
    }


    /**
     * Insert byte array of seminar into memeory
     * 
     * @param s
     *            byte array of seminar
     * @return
     *         handle
     */
    public Handle insert(byte[] s) {
        Handle h = null;
        while (h == null) {
            DLLNode<Block> curr = freelist.getHead();
            while (curr != null) {
                Block block = curr.getData();
                if (block.getLength() >= s.length) {
                    h = new Handle(block.getStart(), s.length);
                    System.arraycopy(s, 0, memory, block.getStart(), s.length);
                    if (block.getLength() > s.length) {
                        block = new Block(block.getStart() + s.length, block
                            .getLength() - s.length);
                        curr.setData(block);
                    }
                    else {
                        freelist.removeNode(curr);
                    }
                    break;
                }
                curr = curr.getNext();
            }
            if (h == null) {
                expandMemory();
            }
        }
        return h;
    }


    private void expandMemory() {
        int oldSize = memory.length;
        int newSize = oldSize + blockSize;
        byte[] newMemArray = new byte[newSize];
        System.arraycopy(memory, 0, newMemArray, 0, oldSize);
        memory = newMemArray;

        freelist.insertNode(new DLLNode<Block>(new Block(oldSize, blockSize)));
        mergeMemory();
        System.out.println("Memory pool expanded to " + newSize + " bytes");
    }


    private void mergeMemory() {
        DLLNode<Block> curr = freelist.getHead();
        while (curr != null && curr.getNext() != null) {
            Block currentBlock = curr.getData();
            Block nextBlock = curr.getNext().getData();
            if (currentBlock.getStart() + currentBlock.getLength() == nextBlock
                .getStart()) {
                currentBlock = new Block(currentBlock.getStart(), currentBlock
                    .getLength() + nextBlock.getLength());
                curr.setData(currentBlock);
                freelist.removeNode(curr.getNext());
            }
            else {
                curr = curr.getNext();
            }
        }
    }


    /**
     * Print freelist
     */
    public void print() {
        System.out.println("Freeblock List:");
        if (freelist.getCount() == 0) {
            System.out.println("There are no freeblocks in the memory pool");
        }
        DLLNode<Block> curr = freelist.getHead();
        String output = "";
        for (int i = 0; i < freelist.getCount() - 1; i++) {
            output += curr.getData().toString() + " -> ";
            curr = curr.getNext();
        }
        output += curr.getData().toString();
        System.out.println(output);
    }


    /**
     * Deserialize byte data into seminar
     * 
     * @param start
     *            Start location in memory
     * @param length
     *            Length in memory
     * @return
     *         Deserialized data
     * @throws Exception
     */
    public Seminar searchMemory(int start, int length) throws Exception {
        byte[] seminarData = new byte[length];
        System.arraycopy(memory, start, seminarData, 0, length);
        return Seminar.deserialize(seminarData);
    }


    /**
     * Remove bytes from memory and add back to freelist
     * 
     * @param h
     *            handle to remove
     */
    public void remove(Handle h) {
        Block freeBlock = new Block(h.getStart(), h.getLength());
        DLLNode<Block> curr = freelist.getHead();
        while (curr != null && curr.getData().getStart() < freeBlock
            .getStart()) {
            curr = curr.getNext();
        }

        if (curr == null) {
            freelist.insertNode(new DLLNode<Block>(freeBlock));
        }
        else {
            DLLNode<Block> freeNode = new DLLNode<Block>(freeBlock);
            freelist.insertNodeBefore(curr, freeNode);
        }
        mergeMemory();

    }

}
