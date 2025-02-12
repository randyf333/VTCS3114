/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 * Double linked list node
 * 
 * @param <T>
 */
public class DLLNode<T> {
    private T data;
    private DLLNode<T> prev;
    private DLLNode<T> next;

    /**
     * Constructor
     * 
     * @param data
     *            data of DLLNode
     */
    public DLLNode(T data) {
        this.data = data;
        prev = null;
        next = null;
    }


    /**
     * Get Record
     * 
     * @return
     *         data
     */
    public T getData() {
        return data;
    }


    /**
     * Set previous DLLNode to p
     * 
     * @param p
     *            new previous DLLNode
     */
    public void setPrev(DLLNode<T> p) {
        prev = p;
    }


    /**
     * set next DLLNode to n
     * 
     * @param n
     *            new next DLLNode
     */
    public void setNext(DLLNode<T> n) {
        next = n;
    }


    /**
     * Get prev DLLNode
     * 
     * @return
     *         prev
     */
    public DLLNode<T> getPrev() {
        return prev;
    }


    /**
     * Get next DLLNode
     * 
     * @return
     *         next
     */
    public DLLNode<T> getNext() {
        return next;
    }


    /**
     * Change data in node
     * 
     * @param newData
     *            updated data
     */
    public void setData(T newData) {
        data = newData;
    }

}
