import student.TestCase;

/**
 * 
 * @author Randy Fu randyf333
 * 
 * @version 8/31/24
 * 
 */

public class HashTest extends TestCase {

    private Hash testTable;
    private Handle h;

    /**
     * 
     * Sets up the tests that follow. In general, used for initialization
     * 
     */

    public void setUp() {
        testTable = new Hash(4);
        h = new Handle(0, 0);
    }


    /**
     * 
     * Test getTableLength method
     * 
     */

    public void testGetNumRecords() {

        assertEquals(testTable.getNumRecords(), 0);

    }


    /**
     * 
     * Test findRecord method
     * 
     */

    public void testFindRecord() {
        assertNull(testTable.findRecord(0));
        testTable.insertRecord(0, h);
        assertNull(testTable.findRecord(1));
        assertNotNull(testTable.findRecord(0));
        testTable.insertRecord(2, h);
        assertNotNull(testTable.findRecord(2));
        testTable.insertRecord(5, h);
        assertNull(testTable.findRecord(4));
        assertNotNull(testTable.findRecord(5));
        testTable.insertRecord(6, h);
        assertNotNull(testTable.findRecord(6));
        testTable.removeRecord(2);
        assertNull(testTable.findRecord(2));
        assertNotNull(testTable.findRecord(0));
        testTable.insertRecord(1, h);
        testTable.insertRecord(9, h);
        assertNotNull(testTable.findRecord(9));
    }


    /**
     * Test printRecord method
     * 
     */
    public void testPrintRecords() {
        testTable.insertRecord(0, h);
        testTable.insertRecord(3, h);
        testTable.insertRecord(2, h);
        assertEquals(testTable.printRecords(), 3);
        testTable.removeRecord(2);
        assertEquals(testTable.printRecords(), 2);

    }


    /**
     * Test insertRecord method
     */
    public void testInsertRecord() {
        testTable.insertRecord(0, h);
        testTable.insertRecord(0, h);
        assertEquals(testTable.getNumRecords(), 1);
        testTable.insertRecord(1, h);
        assertEquals(4, testTable.getTableLength());
        testTable.insertRecord(2, h);
        assertEquals(testTable.getNumRecords(), 3);
        assertEquals(8, testTable.getTableLength());
        testTable.removeRecord(2);
        assertEquals(testTable.getNumRecords(), 2);
        testTable.insertRecord(2, h);
        assertEquals(testTable.getNumRecords(), 3);
        testTable.insertRecord(5, h);
        assertEquals(testTable.getNumRecords(), 4);
        testTable.insertRecord(15, h);
        testTable.insertRecord(12, h);
        assertEquals(testTable.getNumRecords(), 6);
        testTable.insertRecord(9, h);
        assertEquals(testTable.getNumRecords(), 7);
    }


    /**
     * 
     * Test removeRecord method
     * 
     */

    public void testRemoveRecord() {
        testTable.insertRecord(0, h);
        testTable.insertRecord(1, h);
        testTable.removeRecord(0);
        testTable.removeRecord(3);
        testTable.insertRecord(2, h);
        assertEquals(2, testTable.getNumRecords());
        assertNull(testTable.findRecord(0));
        assertNotNull(testTable.findRecord(1));
        testTable.removeRecord(1);
        testTable.insertRecord(1, h);
        assertNotNull(testTable.findRecord(1));
        testTable.insertRecord(12, h);
        testTable.removeRecord(12);
        testTable.insertRecord(13, h);
        testTable.insertRecord(9, h);
        testTable.removeRecord(13);
        assertEquals(3, testTable.getNumRecords());
        assertNotNull(testTable.findRecord(9));
        testTable.removeRecord(9);
        assertNull(testTable.findRecord(9));
    }


    /**
     * Check out the hash method
     * 
     * @throws Exception
     * 
     *             either a IOException or FileNotFoundException
     * 
     */

    public void testHashing() throws Exception {
        assertEquals(Hash.h(0, 10), 0);
        assertEquals(Hash.h(1, 10), 1);
        assertEquals(Hash.h(2, 10), 2);
        assertEquals(Hash.h(3, 10), 3);
        assertEquals(Hash.h(4, 10), 4);
        assertEquals(Hash.h(5, 10), 5);
        assertEquals(Hash.h(10, 10), 0);
        assertEquals(Hash.h(11, 10), 1);
    }

}
