import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class NodeTest extends TestCase {

    private Node n;

    /**
     * Setup method
     */
    public void setUp() {
        n = new Node(4);
    }


    /**
     * Test all methods for Node class
     */
    public void testMethods() {
        assertEquals(4, n.getIndex());
        n.setIndex(3);
        assertEquals(3, n.getIndex());
    }
}
