/**
 * @author Randy Fu randyf333
 * @version 11/13/24
 */
public class Block {
// stores starting position and length of a block
// Provides a string representation of the block (start,length)
    private int start;
    private int length;

    /**
     * Create new block
     * 
     * @param start
     *            start position
     * @param length
     *            length
     */
    public Block(int start, int length) {
        this.start = start;
        this.length = length;
    }


    /**
     * Get start of block
     * 
     * @return
     *         start
     */
    public int getStart() {
        return start;
    }


    /**
     * Get length of block
     * 
     * @return
     *         length
     */
    public int getLength() {
        return length;
    }


    /**
     * String representation of block
     * 
     * @return block as string
     */
    public String toString() {
        return "(" + start + "," + length + ")";
    }
}
