/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 */
public class LeafNode implements BinNode {

    private LinkedList list;

    /**
     * Constructor
     */
    public LeafNode() {
        list = new LinkedList();
    }


    /**
     * Get linkedlist
     * 
     * @return
     *         list
     */
    public LinkedList getList() {
        return list;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public BinNode insert(
        Seminar s,
        int x,
        int y,
        int xmin,
        int xmax,
        int ymin,
        int ymax,
        int level) {
        if (list.getNode() == null || (list.getNode().getSem().x() == x && list
            .getNode().getSem().y() == y)) {
            list.insertNode(new LLNode(s));
            return this;
        }

        InternalNode inNode = new InternalNode();
        LLNode temp = list.getNode();
        while (temp != null) {
            inNode.insert(temp.getSem(), temp.getSem().x(), temp.getSem().y(),
                xmin, xmax, ymin, ymax, level);
            temp = temp.getNext();
        }
        inNode.insert(s, x, y, xmin, xmax, ymin, ymax, level);
        return inNode;
    }


    @Override
    public BinNode delete(
        Seminar s,
        int x,
        int y,
        int xmin,
        int xmax,
        int ymin,
        int ymax,
        int level) {
        list.removeNode(s);
        if (list.getNode() == null) {
            return new EmptyNode();
        }
        return this;
    }


    @Override
    public void print(int level, int height) {
        String indents = calculateIndents(level, height);
        LLNode temp = list.getNode();
        String seminars = "";
        while (temp != null) {
            seminars = seminars + " " + temp.getSem().id();
            temp = temp.getNext();
        }
        System.out.println(indents + "(Leaf with " + list.getCount()
            + " objects: " + seminars.trim() + ")");
    }


    @Override
    public int search(
        int x,
        int y,
        int width,
        int level,
        int xmin,
        int xmax,
        int ymin,
        int ymax,
        int centerx,
        int centery,
        int radius) {
        int ax = list.getNode().getSem().x();
        int ay = list.getNode().getSem().y();
        if (calculateProduct(ax, ay, centerx, centery) <= radius * radius) {
            LLNode head = list.getNode();
            while (head != null) {
                System.out.println("Found a record with key value " + head
                    .getSem().id() + " at " + head.getSem().x() + ", " + head
                        .getSem().y());
                head = head.getNext();
            }
        }
        return 1;
    }


    private int calculateProduct(int ax, int ay, int bx, int by) {
        return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public int findHeight(BinNode n) {
        return 1;
    }


    @Override
    public BinNode getLeft() {
        return null;
    }


    @Override
    public BinNode getRight() {
        return null;
    }


    @Override
    public String calculateIndents(int level, int height) {
        return "    ".repeat(height - level);
    }

}
