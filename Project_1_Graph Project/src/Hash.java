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
    private Record tomb = new Record(null, null);
    private String type;

    /**
     * Create a new hash table
     * 
     * @param tableLength
     *            Initial length of the hash table
     * @param tableType
     *            type of table, artist or song
     * 
     */

    public Hash(int tableLength, String tableType) {
        table = new Record[tableLength];
        numOfRecords = 0;
        type = tableType;
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
     * @param name
     *            name of the record
     * @return
     *         record found
     */
    public Record findRecord(String name) {
        if (name != null) {
            int home = h(name, table.length);
            int pos = home;
            int start = pos;
            for (int i = 1; table[pos] != null && !name.equals(table[pos]
                .getKey()); i++) {
                pos = (home + i * i) % table.length;
                // went back to beginning of table, which means its not there
                if (pos == start) {
                    return null;
                }
            }
            if (table[pos] != null && name.equals(table[pos].getKey())) {
                return table[pos];
            }
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
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].equals(tomb)) {
                    System.out.println(i + ": TOMBSTONE");
                }
                else {
                    System.out.println(i + ": |" + table[i].getKey() + "|");
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
        System.out.println(type + " hash table size doubled.");
        Record[] oldTable = table;
        int oldSize = table.length;
        table = new Record[oldTable.length * 2];
        numOfRecords = 0;
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != null && !oldTable[i].equals(tomb)) {
                insertRecord(oldTable[i].getKey(), oldTable[i].getNode()
                    .getIndex());
            }
        }
    }


    /**
     * Insert record into table
     * 
     * @param key
     *            Record name to be inserted
     * @param graphIndex
     *            Where the Node is stored in the graph
     */
    public void insertRecord(String key, int graphIndex) {
        // is stored in graph
        if (numOfRecords + 1 > table.length / 2) {
            expandTable();
        }
        int home = h(key, table.length);
        int pos = home;
        for (int i = 1; table[pos] != null && !table[pos].equals(tomb); i++) {
            //
            if (key.equals(table[pos].getKey())) {
                // Duplicate so return
                return;
            }
            pos = (home + i * i) % table.length;
        }
        table[pos] = new Record(key, new Node(graphIndex));
        numOfRecords++;
    }


    /**
     * Remove record based on key
     * 
     * @param key
     *            The key of the record to remove
     */
    public void removeRecord(String key) {
        int home = h(key, table.length);
        int pos = home;
        for (int i = 1; table[pos] != null; i++) {
            if (key.equals(table[pos].getKey())) {
                table[pos] = tomb;
                numOfRecords--;
                return;
            }
            pos = (home + i * i) % table.length;
        }
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
