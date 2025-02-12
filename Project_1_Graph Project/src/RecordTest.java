import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class RecordTest extends TestCase {
    private Record record;
    private Node n;

    /**
     * Setup following tests
     */
    public void setUp() {
        n = new Node(3);
        record = new Record("joe", n);
    }


    /**
     * Test get methods
     */
    public void testGets() {
        assertEquals(record.getKey(), "joe");
        assertEquals(record.getNode(), n);
    }
}
