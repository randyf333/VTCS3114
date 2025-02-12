import student.TestCase;

/**
 * @author Randy Fu randyf333
 *         Graph implementation and methods based mostly on OpenDSA
 * @version 8/31/24
 */
public class GraphTest extends TestCase {

    private Graph graph;

    /**
     * Setup graph for testing
     */
    public void setUp() {
        graph = new Graph(4);
    }


    /**
     * test insertNode method
     */
    public void testInsertNode() {
        graph.insertNode();
        assertNotNull(graph.getNodes()[0]);
        graph.insertNode();
        assertNotNull(graph.getNodes()[1]);
        graph.insertNode();
        assertNotNull(graph.getNodes()[2]);
        assertNull(graph.getNodes()[3]);
        graph.insertNode();
        graph.insertNode();
    }


    /**
     * Test addEdge method
     */
    public void testAddEdge() {
        graph.insertNode();
        graph.insertNode();
        graph.addEdge(0, 1);
        assertNotNull(graph.getNodes()[0].getNext());
        graph.addEdge(0, 1);
        assertNull(graph.getNodes()[0].getNext().getNext());
        graph.insertNode();
        graph.addEdge(0, 2);
    }


    /**
     * Test removeEdge method
     */
    public void testRemoveEdge() {
        graph.insertNode();
        graph.insertNode();
        graph.insertNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.removeEdge(0, 1);
        assertEquals(graph.getNodes()[0].getNext().getIndex(), 2);
    }


    /**
     * Test removeNode method
     */
    public void testRemoveNode() {
        graph.insertNode();
        graph.insertNode();
        graph.insertNode();
        graph.addEdge(0, 1);
        graph.insertNode();
        graph.addEdge(1, 3);
        graph.addEdge(0, 3);
        graph.addEdge(2, 0);
        graph.removeNode(0);
        assertEquals(3, graph.getNodeCount());
        assertEquals(0, graph.insertNode());
        assertEquals(4, graph.insertNode());
        graph.removeNode(3);
        assertEquals(4, graph.getNodeCount());
        assertEquals(3, graph.insertNode());
        graph.removeNode(1);
        graph.removeNode(2);
        graph.removeNode(3);
        graph.removeNode(3);
        assertEquals(graph.getNodeCount(), 2);
    }


    /**
     * test unionFind method
     */
    public void testUnionFindAndLargestComponents() {
        graph.insertNode();
        graph.insertNode();
        graph.insertNode();
        graph.insertNode();
        graph.insertNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 4);
        graph.addEdge(2, 4);
        assertEquals(2, graph.unionFind());
        graph.removeNode(3);
        assertEquals(1, graph.unionFind());
        graph.insertNode();
        assertEquals(2, graph.unionFind());
        assertEquals(4, graph.sizeLargestComponenet());
        graph.removeNode(0);
        assertEquals(2, graph.unionFind());
        assertEquals(3, graph.sizeLargestComponenet());
        graph.insertNode();
        assertEquals(3, graph.unionFind());
        assertEquals(3, graph.sizeLargestComponenet());
        graph.addEdge(0, 1);
        assertEquals(2, graph.unionFind());
    }
}
