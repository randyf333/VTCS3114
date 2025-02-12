/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 *
 *          Node for linked list
 * 
 * @param <T>
 */
public class LLNode<T> {

    private T run;
    private LLNode<T> next;

    /**
     * Create new LLNode
     * 
     * @param r
     *            data
     */
    public LLNode(T r) {
        run = r;
        next = null;
    }


    /**
     * Return data in node
     * 
     * @return
     *         data
     */
    public T getData() {
        return run;
    }


    /**
     * Get next node in list
     * 
     * @return
     *         next node
     */
    public LLNode<T> getNext() {
        return next;
    }


    /**
     * Set next node
     * 
     * @param newNode
     *            node to set as next
     */
    public void setNext(LLNode<T> newNode) {
        next = newNode;
    }
}
