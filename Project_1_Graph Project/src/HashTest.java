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

    /**
     * 
     * Sets up the tests that follow. In general, used for initialization
     * 
     */

    public void setUp() {
        testTable = new Hash(4, "Names");

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

        assertNull(testTable.findRecord(null));
        testTable.insertRecord("John", 0);
        assertNull(testTable.findRecord("Sam"));
        assertNotNull(testTable.findRecord("John"));
        testTable.insertRecord("Joe", 0);
        assertNotNull(testTable.findRecord("Joe"));
        testTable.removeRecord("Joe");
        testTable.insertRecord("Smith", 0);
        assertNotNull(testTable.findRecord("Smith"));
        testTable.removeRecord("Smith");
        assertNull(testTable.findRecord("Smith"));
        assertNotNull(testTable.findRecord("John"));
        assertNull(testTable.findRecord("Jose"));

    }


    /**
     * Test printRecord method
     * 
     */
    public void testPrintRecords() {
        testTable.insertRecord("John", 0);
        testTable.insertRecord("Sam", 0);
        testTable.insertRecord("Bob", 0);
        assertEquals(3, testTable.printRecords());

        testTable.removeRecord("Sam");

        assertEquals(2, testTable.printRecords());

    }


    /**
     * Test insertRecord method
     */
    public void testInsertRecord() {
        testTable.insertRecord("John", 0);
        testTable.insertRecord("John", 0);
        assertEquals(testTable.getNumRecords(), 1);
        testTable.insertRecord("Sam", 0);
        assertEquals(testTable.getNumRecords(), 2);
        testTable.insertRecord("Joe", 0);
        assertEquals(testTable.getNumRecords(), 3);
        testTable.insertRecord("Adam", 0);
        testTable.insertRecord("Steve", 0);
        testTable.insertRecord("James", 3);
        testTable.insertRecord("Perry", 5);
        assertEquals(testTable.getNumRecords(), 7);
        testTable.removeRecord("Perry");
        testTable.removeRecord("Adam");
        testTable.insertRecord("Adam", 4);
        testTable.insertRecord("Steve", 2);
        testTable.insertRecord("Terry", 2);
        assertEquals(testTable.getNumRecords(), 7);
    }


    /**
     * 
     * Test removeRecord method
     * 
     */

    public void testRemoveRecord() {

        testTable.insertRecord("Steve", 0);

        testTable.insertRecord("Joe", 0);

        testTable.removeRecord("Steve");

        testTable.removeRecord("Adam");

        testTable.insertRecord("John", 0);

        assertNull(testTable.findRecord("Steve"));

        assertNotNull(testTable.findRecord("Joe"));

        testTable.removeRecord("John");

        testTable.insertRecord("John", 0);

        assertNotNull(testTable.findRecord("John"));

        // assertNull(testTable.findRecord("John"));

    }


    /**
     * Check out the sfold method
     * 
     * @throws Exception
     * 
     *             either a IOException or FileNotFoundException
     * 
     */

    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }

}
