/**
 * 
 * @author Randy Fu randyf333
 * 
 * @version 10/14/24
 * 
 */

public class InternalNode implements BinNode {
    private BinNode left;
    private BinNode right;
    private int split;

    /**
     * 
     * constructor
     * 
     */
    public InternalNode() {
        left = new EmptyNode();
        right = new EmptyNode();
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
        split = (level % 2 == 0)
            ? calcSplit(xmin, xmax)
            : calcSplit(ymin, ymax);
        if (level % 2 == 0) {
            level += 1;
            if (x >= split) {
                // go right
                right = right.insert(s, x, y, split, xmax, ymin, ymax, level);
            }
            else {
                // go left
                left = left.insert(s, x, y, xmin, split, ymin, ymax, level);
            }
        }
        else {
            level += 1;
            if (y >= split) {
                right = right.insert(s, x, y, xmin, xmax, split, ymax, level);
            }
            else {
                left = left.insert(s, x, y, xmin, xmax, ymin, split, level);
            }
        }
        return this;
    }


    /**
     * 
     * Get left child
     * 
     * 
     * 
     * @return
     * 
     *         left
     * 
     */
    public BinNode getLeft() {
        return left;
    }


    /**
     * 
     * Get right child
     * 
     * 
     * 
     * @return
     * 
     *         right
     * 
     */
    public BinNode getRight() {
        return right;
    }


    /**
     * 
     * Set right child
     * 
     * 
     * 
     * @param node
     * 
     *            new right child
     * 
     */

    public void setRight(BinNode node) {
        right = node;
    }


    /**
     * 
     * Set left child
     * 
     * 
     * 
     * @param node
     * 
     *            new left child
     * 
     */
    public void setLeft(BinNode node) {
        left = node;

    }


    /**
     * 
     * Method to test splitting correctly
     * 
     * 
     * 
     * @return
     * 
     *         split
     * 
     */

    public int getSplit() {
        return split;
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
        split = (level % 2 == 0)
            ? calcSplit(xmin, xmax)
            : calcSplit(ymin, ymax);
        if (level % 2 == 0) {
            level += 1;
            if (x >= split) {
                // go right
                right = right.delete(s, x, y, split, xmax, ymin, ymax, level);
            }
            else {
                // go left
                left = left.delete(s, x, y, xmin, split, ymin, ymax, level);
            }
        }
        else {
            level += 1;
            if (y >= split) {
                right = right.delete(s, x, y, xmin, xmax, split, ymax, level);
            }
            else {
                left = left.delete(s, x, y, xmin, xmax, ymin, split, level);
            }

        }

        // Check left and right if empty
        if (left.isEmpty() && right.isLeaf()) {
            return right;
        }
        if (right.isEmpty() && left.isLeaf()) {
            return left;
        }
        return this;

    }


    @Override

    public void print(int level, int height) {
        String indents = calculateIndents(level, height);
        System.out.println(indents + "(I)");
        right.print(level + 1, height);
        left.print(level + 1, height);
    }


    /**
     * Calculate if boxes overlap
     * 
     * 
     * 
     * @param b1leftx
     * 
     *            x coord of top left corner of box 1
     * 
     * @param b1lefty
     * 
     *            y coord of top left corner of box 1
     * 
     * @param b1rightx
     * 
     *            x coord of bottom right corner of box 1
     * 
     * @param b1righty
     * 
     *            y coord of bottom right corner of box 1
     * 
     * @param b2leftx
     * 
     *            x coord of top left corner of box 1
     * 
     * @param b2lefty
     * 
     *            y coord of top left corner of box 1
     * 
     * @param b2rightx
     * 
     *            x coord of bottom right corner of box 1
     * 
     * @param b2righty
     * 
     *            y coord of bottom right corner of box 1
     * 
     * @return
     * 
     *         true if boxes overlap
     * 
     */

    public boolean calculateBoundary(
        int b1leftx,
        int b1lefty,
        int b1rightx,
        int b1righty,
        int b2leftx,
        int b2lefty,
        int b2rightx,
        int b2righty) {
        // removed the equals
        boolean p1 = b1leftx <= b2rightx && b1rightx >= b2leftx;
        boolean p2 = b1lefty <= b2righty && b1righty >= b2lefty;
        return p1 && p2;
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
        int nodesVisited = 1;
        // Compare bounding box.
        split = (level % 2 == 0)
            ? calcSplit(xmin, xmax)
            : calcSplit(ymin, ymax);
        if (level % 2 == 0) {
            level += 1;
            if (centerx + radius < split) {
                nodesVisited += left.search(x, y, width, level, xmin, split,
                    ymin, ymax, centerx, centery, radius);
            }
            else if (centerx - radius >= split) {
                nodesVisited += right.search(x, y, width, level, split, xmax,
                    ymin, ymax, centerx, centery, radius);
            }
            else {
                if (calculateBoundary(x, y, x + width, y + width, xmin, ymin,
                    split, ymax)) {
                    // go into branch
                    nodesVisited += left.search(x, y, width, level, xmin, split,
                        ymin, ymax, centerx, centery, radius);
                }
                if (calculateBoundary(x, y, x + width, y + width, split, ymin,
                    xmax, ymax)) {
                    nodesVisited += right.search(x, y, width, level, split,
                        xmax, ymin, ymax, centerx, centery, radius);
                }
            }
        }
        else {
            level += 1;
            if (centery + radius < split) {
                nodesVisited += left.search(x, y, width, level, xmin, xmax,
                    ymin, split, centerx, centery, radius);
            }
            else if (centery - radius >= split) {
                nodesVisited += right.search(x, y, width, level, xmin, xmax,
                    split, ymax, centerx, centery, radius);
            }
            else {
                if (calculateBoundary(x, y, x + width, y + width, xmin, ymin,
                    xmax, split)) {
                    nodesVisited += left.search(x, y, width, level, xmin, xmax,
                        ymin, split, centerx, centery, radius);
                }
                if (calculateBoundary(x, y, x + width, y + width, xmin, split,
                    xmax, ymax)) {
                    nodesVisited += right.search(x, y, width, level, xmin, xmax,
                        split, ymax, centerx, centery, radius);
                }
            }
        }
        return nodesVisited;
    }


    private int calcSplit(int min, int max) {
        return (min + max) / 2;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public int findHeight(BinNode n) {
        return 0;
    }


    @Override
    public String calculateIndents(int level, int height) {
        return "    ".repeat(height - level);
    }

}
