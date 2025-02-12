/**
 * Hash table class
 * 
 * @author Randy Fu randyf333
 *         Implementation was based mostly on OpenDSA and given code
 * @version 8/31/24
 */

public class Hash {

    private Record[] table;
    private int numOfRecords;
    private Record tomb = new Record(-1, new Handle(-1, -1));

    /**
     * Create a new hash table
     * 
     * @param tableLength
     *            Initial length of the hash table
     */

    public Hash(int tableLength) {
        table = new Record[tableLength];
        numOfRecords = 0;
    }


    /**
     * Get current number of records in the hash table
     * 
     * @return
     *         Current records in the hash table
     */
    public int getNumRecords() {
        return numOfRecords;
    }


    /**
     * 
     * @param id
     *            id key of the record
     * @return
     *         record found
     */
    public Record findRecord(int id) {

        int home = h(id, table.length);
        int pos = home;
        int start = pos;
        for (int i = 1; table[pos] != null && id != table[pos].getKey(); i++) {
            pos = (home + (i * (i + 1)) / 2) % table.length;
            if (pos == start) {
                return null;
            }
        }
        if (table[pos] != null && id == table[pos].getKey()) {
            return table[pos];
        }
        return null;
    }


    /**
     * Print out all records by iterating through the hash table
     * 
     * @return
     *         number of records printed
     */
    public int printRecords() {
        System.out.println("Hashtable:");
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].equals(tomb)) {
                    System.out.println(i + ": TOMBSTONE");
                }
                else {
                    System.out.println(i + ": " + table[i].getKey());
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * Expand table when hash table exceeds half current size
     */
    private void expandTable() {

        System.out.println("Hash table expanded to " + table.length * 2
            + " records");
        Record[] oldTable = table;
        int oldSize = table.length;
        table = new Record[oldTable.length * 2];
        numOfRecords = 0;
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != null && !oldTable[i].equals(tomb)) {
                // add handle parameter when implemented
                table[i] = new Record(oldTable[i].getKey(), oldTable[i]
                    .getHandle());
                numOfRecords++;
            }
        }
    }


    /**
     * Insert record in hashtable
     * 
     * @param key
     *            key for hash table
     * @param h
     *            handle
     */
    public void insertRecord(int key, Handle h) {

        if (numOfRecords + 1 > table.length / 2) {
            expandTable();
        }
        int home = h(key, table.length);
        int pos = home;
        for (int i = 1; table[pos] != null && !table[pos].equals(tomb); i++) {
            //
            if (key == table[pos].getKey()) {
                // Duplicate so return
                return;
            }
            pos = (home + (i * (i + 1)) / 2) % table.length;
        }
        table[pos] = new Record(key, h);
        numOfRecords++;

    }


    /**
     * Remove record based on key
     * 
     * @param key
     *            The key of the record to remove
     */
    public void removeRecord(int key) {
        int home = h(key, table.length);
        int pos = home;
        for (int i = 1; table[pos] != null; i++) {
            if (key == table[pos].getKey()) {
                table[pos] = tomb;
                numOfRecords--;
                return;
            }
            pos = (home + (i * (i + 1)) / 2) % table.length;
        }

    }


    /**
     * Compute the hash function
     * 
     * @param id
     *            ID number of table
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(int id, int length) {
        return id % length;
    }


    /**
     * Get length of table for testing purposes
     * 
     * @return
     *         length of table
     */
    public int getTableLength() {
        return table.length;
    }
}
