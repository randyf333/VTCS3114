/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 */
public class LinkedList {
    private LLNode node;
    private int listCount;

    /**
     * Constructor
     */
    public LinkedList() {
        node = null;
        listCount = 0;
    }


    /**
     * Insert node into list
     * 
     * @param newNode
     *            node to insert
     */
    public void insertNode(LLNode newNode) {
        if (node == null) {
            node = newNode;
        }
        else if (newNode.getSem().id() < node.getSem().id()) {
            newNode.setNext(node);
            node = newNode;
        }
        else {
            LLNode curr = node;
            while (curr.getNext() != null && curr.getNext().getSem()
                .id() < newNode.getSem().id()) {
                curr = curr.getNext();
            }
            LLNode next = curr.getNext();
            curr.setNext(newNode);
            newNode.setNext(next);

        }
        listCount++;
    }


    /**
     * Remove node from linked list
     * 
     * @param s
     *            Seminar to search and remove
     */
    public void removeNode(Seminar s) {
        if (node == null) {
            return;
        }
        if (node.getSem().id() == s.id()) {
            node = node.getNext();
            listCount--;
        }
        else {
            LLNode curr = node;
            while (curr.getNext() != null) {
                if (curr.getNext().getSem().id() == s.id()) {
                    curr.setNext(curr.getNext().getNext());
                    listCount--;
                    break;
                }
                curr = curr.getNext();
            }
        }
    }


    /**
     * Get head node
     * 
     * @return
     *         node
     */
    public LLNode getNode() {
        return node;
    }


    /**
     * Get number of nodes in linked list
     * 
     * @return
     *         listCount
     */
    public int getCount() {
        return listCount;
    }
}
