import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 11/13/24
 */
public class MemManagerTest extends TestCase {

    private MemManager m;

    /**
     * Setup data
     */
    public void setUp() {
        m = new MemManager(512);
    }


    /**
     * Test handle methods
     */
    public void testHandle() {
        Handle h = new Handle(0, 500);
        assertEquals(0, h.getStart());
        assertEquals(500, h.getLength());
    }

}
