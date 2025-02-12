/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 *
 *          LinkedList class
 * 
 * @param <T>
 */
public class LinkedList<T> {
    private LLNode<T> node;
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
    public void insertNode(LLNode<T> newNode) {
        if (node == null) {
            node = newNode;
        }
        else {
            LLNode<T> curr = node;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        listCount++;
    }


    /**
     * Get head node
     * 
     * @return
     *         node
     */
    public LLNode<T> getNode() {
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


    /**
     * Empty and reset the linked list
     */
    public void clear() {
        node = null;
        listCount = 0;
    }


    /**
     * Remove first node from list
     * 
     */
    public void removeNode() {
        if (node == null) {
            return;
        }
        node = node.getNext();
        listCount--;
    }
}
