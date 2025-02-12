/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class Node {
    private int index;

    /**
     * Constructor
     * 
     * @param data
     *            index of node
     */
    public Node(int data) {
        index = data;
    }


    /**
     * Get index of Node
     * 
     * @return
     *         index of node
     */
    public int getIndex() {
        return index;
    }


    /**
     * Set index of node
     * 
     * @param n
     *            value to set index to
     */
    public void setIndex(int n) {
        index = n;
    }

}
