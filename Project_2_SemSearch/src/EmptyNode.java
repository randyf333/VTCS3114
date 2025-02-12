/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 */
public class EmptyNode implements BinNode {

    private BinNode node;

    /**
     * Constructor
     */
    public EmptyNode() {
        node = null;
    }


    @Override
    public boolean isLeaf() {
        return false;
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
        node = new LeafNode();
        node.insert(s, x, y, xmin, xmax, ymin, ymax, level);
        return node;
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
        return this;
    }


    @Override
    public void print(int level, int height) {
        String indents = calculateIndents(level, height);
        System.out.println(indents + "(E)");
    }


    @Override
    public String calculateIndents(int level, int height) {
        return "    ".repeat(height - level);
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
        return 1;
    }


    @Override
    public boolean isEmpty() {
        return true;
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

}
