/**
 * @author Randy Fu randyf333
 * @version 11/13/24
 */

public class Record {
    private int key;
    private Handle handle;

    /**
     * Create new Record
     * 
     * @param keyValue
     *            name of record
     * @param h
     *            handle to store
     * 
     */
    public Record(int keyValue, Handle h) {
        key = keyValue;
        handle = h;
    }


    /**
     * get record key
     * 
     * @return
     *         key
     */
    public int getKey() {
        return key;
    }


    /**
     * Get records handle
     * 
     * @return
     *         handle
     */
    public Handle getHandle() {
        return handle;
    }
}
