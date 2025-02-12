/**
 * Holds a single record
 *
 * @author CS Staff
 * @version Fall 2024
 */
public class Record implements Comparable<Record> {
    /**
     * 16 bytes per record
     */
    public static final int BYTES = 16;

    private long recID;
    private double key;
    private int runNum;
    private boolean last;

    /**
     * Create new record
     * 
     * @param recID
     *            record id
     * @param key
     *            record key
     * @param runNum
     *            run record belongs to
     * @param isLast
     *            is record last in block
     */
    public Record(long recID, double key, int runNum, boolean isLast) {
        this.recID = recID;
        this.key = key;
        this.runNum = runNum;
        last = isLast;
    }


    // ----------------------------------------------------------
    /**
     * Return the ID value from the record
     *
     * @return record ID
     */
    public long getID() {
        return recID;
    }


    // ----------------------------------------------------------
    /**
     * Return the key value from the record
     *
     * @return record key
     */
    public double getKey() {
        return key;
    }


    /**
     * Get which run the record is part of
     * 
     * @return run number
     */
    public int getRun() {
        return runNum;
    }


    /**
     * Update with run the record is part of
     * 
     * @param num
     *            run number
     */
    public void setRun(int num) {
        runNum = num;
    }


    /**
     * Get record is last in block
     * 
     * @return
     *         true if last record in block
     */
    public boolean isLast() {
        return last;
    }


    // ----------------------------------------------------------
    /**
     * Compare two records based on their keys
     *
     * @return int
     */
    @Override
    public int compareTo(Record toBeCompared) {
        return Double.compare(this.key, toBeCompared.key);
    }
}
