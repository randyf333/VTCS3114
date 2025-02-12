/**
 * @author Randy Fu randyf333
 * @version 9/25/24
 */
public interface BinNode {
    /**
     * return if node is leaf node
     * 
     * @return
     *         true if leaf node
     */
    public boolean isLeaf();


    /**
     * Insert seminar into nodes
     * 
     * @param s
     *            seminar
     * @param x
     *            x coord of seminar
     * @param y
     *            y coord of seminar
     * @param xmin
     *            min x value of world
     * @param xmax
     *            max x value of world
     * @param ymin
     *            min y value of world
     * @param ymax
     *            max y value of world
     * @param level
     *            level in tree
     * @return
     *         node seminar was inserted into
     */
    public BinNode insert(
        Seminar s,
        int x,
        int y,
        int xmin,
        int xmax,
        int ymin,
        int ymax,
        int level);


    /**
     * delete seminar from tree
     * 
     * @param s
     *            seminar
     * @param x
     *            xcoord of seminar
     * @param y
     *            y coord of seminar
     * @param xmin
     *            min x value of world
     * @param xmax
     *            mzx x of world
     * @param ymin
     *            min y of world
     * @param ymax
     *            max y of world
     * @param level
     *            level in tree
     * @return
     *         node seminar was deleted from
     */
    public BinNode delete(
        Seminar s,
        int x,
        int y,
        int xmin,
        int xmax,
        int ymin,
        int ymax,
        int level);


    /**
     * Print the node
     * 
     * @param level
     *            level in tree
     * @param height
     *            height of tree
     * 
     */
    public void print(int level, int height);


    /**
     * Count the number of nodes within the range
     * 
     * @param x
     *            left corner of bounding box
     * @param y
     *            left corner of bounding box
     * @param width
     *            width of the search box
     * @param level
     *            level of node in tree
     * @param xmin
     *            min x value of world
     * @param xmax
     *            max x value of world
     * @param ymin
     *            min y value of world
     * @param ymax
     *            max y value of world
     * @param centerx
     *            x coord of center of search
     * @param centery
     *            y coord of center of search
     * @param radius
     *            radius of search
     * @return
     *         number of nodes within range
     */
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
        int radius);


    /**
     * Check if node is empty node
     * 
     * @return
     *         true if EmptyNode
     */
    public boolean isEmpty();


    /**
     * Find height of tree
     * 
     * @param n
     *            root node
     * @return
     *         height of tree
     */
    public int findHeight(BinNode n);


    /**
     * Get left child node
     * 
     * @return
     *         left child
     */
    public BinNode getLeft();


    /**
     * Get right child node
     * 
     * @return
     *         right child
     */
    public BinNode getRight();


    /**
     * Calculate number of indents for printing
     * 
     * @param level
     *            level of node
     * @param height
     *            height of tree
     * @return
     *         indents
     */
    public String calculateIndents(int level, int height);

}
