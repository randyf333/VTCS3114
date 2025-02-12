/**
 * @author Randy Fu randyf333
 * @version 11/13/24
 */
public class Controller {
    private Hash semTable;
    private MemManager m;

    /**
     * Constructor for Controller
     * 
     * @param memSize
     *            Size of memory
     * @param tableSize
     *            Size of the hash table
     * @throws FileNotFoundException
     */
    public Controller(int memSize, int tableSize) {
        semTable = new Hash(tableSize);
        m = new MemManager(memSize);

    }


    /**
     * Insert input into table
     * 
     * @param s
     *            Seminar
     * @throws Exception
     */
    public void insert(Seminar s) throws Exception {
        // Check if edge exists between two nodes in graph
        if (semTable.findRecord(s.getID()) == null) {
            byte[] semBytes = s.serialize();
            Handle h = m.insert(semBytes);
            semTable.insertRecord(s.getID(), h);
            System.out.println("Successfully inserted record with ID " + s
                .getID());
            System.out.println(s.toString());
            System.out.println("Size: " + semBytes.length);
            // also print size
        }
        else {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + s
                    .getID());
        }

    }


    /**
     * Search for seminar with given id
     * 
     * @param id
     *            id to search for
     * @throws Exception
     */
    public void search(int id) throws Exception {
        Record found = semTable.findRecord(id);
        if (found == null) {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Found record with ID " + id + ": ");
            Seminar s = m.searchMemory(found.getHandle().getStart(), found
                .getHandle().getLength());
            System.out.println(s.toString());
        }
    }


    /**
     * Delete seminar with given id
     * 
     * @param id
     *            id of seminar
     */
    public void delete(int id) {
        Record r = semTable.findRecord(id);
        if (r != null) {
            Handle h = r.getHandle();
            m.remove(h);
            semTable.removeRecord(id);
            System.out.println("Record with ID " + id
                + " successfully deleted from the database");
        }
        else {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
        }

    }


    /**
     * Print hashtable
     */
    public void printTable() {
        int count = semTable.printRecords();
        System.out.println("total records: " + count);
    }


    /**
     * Print freelist
     */
    public void printFreeList() {
        m.print();
    }

}
