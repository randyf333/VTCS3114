/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 *
 *          LinkedList class
 * 
 * @param <T>
 */
public class DoubleLinkedList<T> {
    private DLLNode<T> head;
    private int listCount;

    /**
     * Constructor
     */
    public DoubleLinkedList() {
        head = null;
        listCount = 0;
    }


    /**
     * Insert node into list
     * 
     * @param newNode
     *            node to insert
     */
    public void insertNode(DLLNode<T> newNode) {
        if (head == null) {
            head = newNode;
        }
        else {
            DLLNode<T> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
            newNode.setPrev(curr);
        }
        listCount++;
    }


    /**
     * Get head node
     * 
     * @return
     *         head
     */
    public DLLNode<T> getHead() {
        return head;
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
        head = null;
        listCount = 0;
    }


    /**
     * Remove node from list
     * 
     * @param node
     *            node to remove
     */
    public void removeNode(DLLNode<T> node) {
        if (node == null || head == null) {
            return;
        }

        // If the node is the head node (the first element)
        if (node.getPrev() == null) {
            head = node.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        }
        else {
            // If the node is not the head, update the previous node to point to
            // the next one
            node.getPrev().setNext(node.getNext());

        }
        // If the node is not the last node
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }
        node.setNext(null);
        node.setPrev(null);
        listCount--; // Decrease the count of nodes in the list
    }


    /**
     * Set head of list
     * 
     * @param node
     *            new head
     */
    public void setHead(DLLNode<T> node) {
        head = node;
    }


    /**
     * Utility method to insert node before existingNode
     * 
     * @param existingNode
     *            current existing node
     * @param newNode
     *            node to be inserted before existing node
     */
    public void insertNodeBefore(DLLNode<T> existingNode, DLLNode<T> newNode) {
        if (existingNode == null) {
            // If the existing node is null, add the new node to the end of the
            // list
            insertNode(newNode);
            return;
        }

        if (existingNode.getPrev() == null) {
            // If the existing node is the head, make the new node the head
            newNode.setNext(existingNode);
            existingNode.setPrev(newNode);
            setHead(newNode);
        }
        else {
            // Insert the new node in between the previous node and the existing
            // node
            DLLNode<T> prevNode = existingNode.getPrev();
            prevNode.setNext(newNode);
            newNode.setPrev(prevNode);
            newNode.setNext(existingNode);
            existingNode.setPrev(newNode);
        }
        listCount++;
    }
}
