/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 */
public class LLNode {

    private Seminar s;
    private LLNode next;

    /**
     * Constructor
     * 
     * @param newSem
     *            Seminar data
     */
    public LLNode(Seminar newSem) {
        s = newSem;
        next = null;
    }


    /**
     * Return seminar in node
     * 
     * @return
     *         seminar
     */
    public Seminar getSem() {
        return s;
    }


    /**
     * Get next node in list
     * 
     * @return
     *         next node
     */
    public LLNode getNext() {
        return next;
    }


    /**
     * Set next node
     * 
     * @param newNode
     *            node to set as next
     */
    public void setNext(LLNode newNode) {
        next = newNode;
    }
}
