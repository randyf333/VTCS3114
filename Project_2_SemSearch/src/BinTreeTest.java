import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 9/25/24
 */
public class BinTreeTest extends TestCase {

    private BinTree t;

    /**
     * Setup objects
     */
    public void setUp() {
        t = new BinTree();
    }


    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

    // ----------------------------------------------------------


    /**
     * Example 1: This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     * 
     * @throws IOException
     */
    public void testMethods() throws IOException {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "1insert.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("1output.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test inserting into BinTree
     */
    public void testInsert() {
        t.insert(new Seminar(1, null, null, 0, (short)0, (short)0, 0, null,
            null), 0, 0, 0, 0);
        assertEquals(1, t.getCount());
    }


    /**
     * Test leaf node insert
     */
    public void testLeafNodeInsert() {
        LeafNode node = new LeafNode();
        Seminar t1 = new Seminar(1, null, null, 0, (short)0, (short)0, 0, null,
            null);
        node.insert(t1, 0, 0, 0, 0, 0, 0, 0);
    }


    /**
     * Test delete method
     */
    public void testDelete() {
        Seminar s = new Seminar(1, null, null, 0, (short)0, (short)0, 0, null,
            null);
        t.insert(s, 0, 0, 10, 10);
        assertTrue(t.getRoot() instanceof LeafNode);
        t.delete(s, s.x(), s.y(), 0, 0);
        assertTrue(t.getRoot() instanceof EmptyNode);
    }


    /**
     * Test indenting method
     */
    public void testNodeIndents() {
        LeafNode lnode = new LeafNode();
        EmptyNode enode = new EmptyNode();
        InternalNode inode = new InternalNode();

        String indent1 = lnode.calculateIndents(0, 0);
        String indent2 = enode.calculateIndents(0, 0);
        String indent3 = inode.calculateIndents(0, 0);
        assertEquals("", indent1);
        assertEquals("", indent2);
        assertEquals("", indent3);
        indent1 = lnode.calculateIndents(0, 1);
        assertEquals("    ", indent1);
        indent2 = enode.calculateIndents(0, 1);
        indent3 = inode.calculateIndents(0, 1);
        assertEquals("    ", indent2);
        assertEquals("    ", indent3);

        Exception error = null;
        try {
            indent1 = lnode.calculateIndents(1, 0);
        }
        catch (Exception e) {
            error = e;
            assertTrue(error instanceof IllegalArgumentException);
        }

        try {
            indent2 = enode.calculateIndents(1, 0);
        }
        catch (Exception e) {
            error = e;
            assertTrue(error instanceof IllegalArgumentException);
        }

        try {
            indent3 = inode.calculateIndents(1, 0);
        }
        catch (Exception e) {
            error = e;
            assertTrue(error instanceof IllegalArgumentException);
        }

        indent1 = lnode.calculateIndents(1, 3);
        assertEquals("        ", indent1);
        indent2 = enode.calculateIndents(1, 3);
        indent3 = inode.calculateIndents(1, 3);
        assertEquals("        ", indent2);
        assertEquals("        ", indent3);
    }


    /**
     * Initial test for searching
     */
    public void testSearching() {
        Seminar t1 = new Seminar(1, null, null, 0, (short)1, (short)0, 0, null,
            null);
        Seminar t2 = new Seminar(2, null, null, 0, (short)1, (short)2, 0, null,
            null);
        Seminar t3 = new Seminar(3, null, null, 0, (short)2, (short)1, 0, null,
            null);
        t.insert(t1, 1, 0, 4, 4);
        t.insert(t2, 1, 2, 4, 4);
        t.insert(t3, 2, 1, 4, 4);
        t.search(1, 1, 1, 4, 4);

    }


    /**
     * Test combination of commands
     * 
     * @throws IOException
     */
    public void testSearch() throws IOException {
        String[] args = new String[2];
        args[0] = "64";
        args[1] = "inputTest.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("outputTest.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test search command
     * 
     * @throws IOException
     */
    public void testSearch2() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "BinTreeTestSearch.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("BinTreeTestSearchOutput.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test boundary calculation
     */
    public void testCalculateBoundary() {
        InternalNode n = new InternalNode();
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 2, 2, 8, 8));
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 10, 0, 20, 10));
        assertFalse(n.calculateBoundary(0, 0, 10, 10, 11, 0, 20, 10));
        assertFalse(n.calculateBoundary(0, 0, 10, 10, 20, 0, 30, 10));
        assertFalse(n.calculateBoundary(0, 0, 10, 10, 0, 11, 10, 20));
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 5, 5, 15, 15));
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 0, 0, 10, 10));
        assertTrue(n.calculateBoundary(5, 5, 5, 5, 0, 0, 10, 10));

        assertTrue(n.calculateBoundary(0, 0, 10, 10, 5, 5, 15, 15));
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 10, 0, 20, 10));
        assertTrue(n.calculateBoundary(10, 10, 20, 20, 0, 0, 10, 10));
        assertFalse(n.calculateBoundary(0, 0, 10, 10, 11, 0, 20, 10));
        assertFalse(n.calculateBoundary(0, 0, 5, 5, 6, 0, 10, 10));
        assertTrue(n.calculateBoundary(3, 0, 7, 10, 0, 0, 10, 10));
        assertTrue(n.calculateBoundary(0, 0, 10, 10, 3, 3, 7, 7));
        assertTrue(n.calculateBoundary(5, 5, 5, 5, 4, 4, 6, 6));
        assertTrue(n.calculateBoundary(5, 5, 5, 5, 5, 5, 5, 5));
        assertFalse(n.calculateBoundary(5, 5, 5, 5, 6, 6, 7, 7));
    }
}
