/**
 * @author Randy Fu randyf333
 *         Implementation based on OpenDSA
 * @version 9/25/24
 */
public class BinTree {
    private BinNode root;
    private int nodeCount;
    private int level;

    /**
     * Constructor
     */
    public BinTree() {
        root = new EmptyNode();
        nodeCount = 0;
        level = 0;
    }


    /**
     * Insert seminar into tree
     * 
     * @param s
     *            seminar
     * @param i
     *            x coord of seminar
     * @param j
     *            y coord of seminar
     * @param xwidth
     *            width of world
     * @param ywidth
     *            height of world
     */
    public void insert(Seminar s, int i, int j, int xwidth, int ywidth) {
        root = root.insert(s, i, j, 0, xwidth, 0, ywidth, level);
        nodeCount++;
    }


    /**
     * Print out tree
     */
    public void printTree() {
        if (root.isEmpty()) {
            System.out.println("E");
            return;
        }
        int treeHeight = findTreeHeight();
        root.print(0, treeHeight);
        // printHelper(root, 0, treeHeight);

    }


    /**
     * Find height of tree
     * 
     * @return
     *         height of tree with root at 0
     */
    private int findTreeHeight() {
        return findHeight(root) - 1;
    }


    private int findHeight(BinNode n) {
        if (n == null) {
            return 0;
        }
        int lheight = findHeight(n.getLeft());
        int rheight = findHeight(n.getRight());
        return Math.max(lheight, rheight) + 1;
    }


    /**
     * Delete seminar from tree
     * 
     * @param s
     *            seminar
     * @param xcoord
     *            x coord of seminar
     * @param ycoord
     *            y coord of seminar
     * @param xwidth
     *            width of world
     * @param ywidth
     *            height of world
     */
    public void delete(
        Seminar s,
        int xcoord,
        int ycoord,
        int xwidth,
        int ywidth) {
        root = root.delete(s, xcoord, ycoord, 0, xwidth, 0, ywidth, level);
    }


    /**
     * Get number of nodes in tree
     * 
     * @return
     *         number of nodes
     */
    public int getCount() {
        return nodeCount;
    }


    /**
     * Get root of tree
     * 
     * @return
     *         root
     */
    public BinNode getRoot() {
        return root;
    }


    /**
     * Search and count the number of nodes within given radius
     * 
     * @param xcoord
     *            xcoord of search circle
     * @param ycoord
     *            y coord of search circle
     * @param radius
     *            radius of circle
     * @param xwidth
     *            x width of world
     * @param ywidth
     *            y width of world
     * @return
     *         number of nodes searched
     */
    public int search(
        int xcoord,
        int ycoord,
        int radius,
        int xwidth,
        int ywidth) {
        int x = xcoord - radius;
        int y = ycoord - radius;
        int width = (2 * radius) + 1;
        return root.search(x, y, width, level, 0, xwidth, 0, ywidth, xcoord,
            ycoord, radius);
    }
}
