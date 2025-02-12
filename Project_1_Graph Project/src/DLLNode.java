/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class DLLNode {
    // Store
    private int index;
    private DLLNode prev;
    private DLLNode next;

    /**
     * Constructor
     * 
     * @param data
     *            index of DLLNode
     */
    public DLLNode(int data) {
        index = data;
        prev = null;
        next = null;
    }


    /**
     * Get Record
     * 
     * @return
     *         index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Set previous DLLNode to p
     * 
     * @param p
     *            new previous DLLNode
     */
    public void setPrev(DLLNode p) {
        prev = p;
    }


    /**
     * set next DLLNode to n
     * 
     * @param n
     *            new next DLLNode
     */
    public void setNext(DLLNode n) {
        next = n;
    }


    /**
     * Get prev DLLNode
     * 
     * @return
     *         prev
     */
    public DLLNode getPrev() {
        return prev;
    }


    /**
     * Get next DLLNode
     * 
     * @return
     *         next
     */
    public DLLNode getNext() {
        return next;
    }

}
