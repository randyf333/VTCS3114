/**
 * @author Randy Fu randyf333
 * @version 11/13/24
 */
public class Handle {
    // Store start and position and length of messages stored in memory manager
    private int startPos;
    private int length;

    /**
     * Create new handle
     * 
     * @param startPos
     *            start position in memory
     * @param length
     *            length of handle
     */
    public Handle(int startPos, int length) {
        this.startPos = startPos;
        this.length = length;
    }


    /**
     * Get start pos of handle
     * 
     * @return
     *         start
     */
    public int getStart() {
        return startPos;
    }


    /**
     * Get length of handle
     * 
     * @return
     *         length
     */
    public int getLength() {
        return length;
    }
}
