import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 9/25/24
 */
public class BSTTest extends TestCase {
    private BST<Integer> tree;

    /**
     * Setup objects for testing
     */
    public void setUp() {
        tree = new BST<Integer>();
    }


    /**
     * Test BST insert method
     */
    public void testInsert() {
        tree.insert(new BSTNode<Integer>(0, new Seminar()));
        assertEquals(tree.getNodeCount(), 1);
        tree.insert(new BSTNode<Integer>(-1, new Seminar()));
        assertEquals(tree.getNodeCount(), 2);
        assertNotNull(tree.getRoot().getLeft());
        tree.insert(new BSTNode<Integer>(1, new Seminar()));
        assertNotNull(tree.getRoot().getRight());
        tree.insert(new BSTNode<Integer>(-3, new Seminar()));
        tree.insert(new BSTNode<Integer>(-2, new Seminar()));
        int value = tree.getRoot().getLeft().getLeft().key();
        assertEquals(-3, value);
        tree.insert(new BSTNode<Integer>(1, new Seminar()));
        value = tree.getRoot().getRight().getLeft().key();
        assertEquals(1, value);
    }


    /**
     * Test search method
     */
    public void testSearch() {
        assertNull(tree.search(0));
        tree.insert(new BSTNode<Integer>(-3, new Seminar()));
        tree.insert(new BSTNode<Integer>(-2, new Seminar()));
        tree.insert(new BSTNode<Integer>(1, new Seminar()));
        tree.insert(new BSTNode<Integer>(-1, new Seminar()));
        tree.insert(new BSTNode<Integer>(0, new Seminar()));
        assertNotNull(tree.search(0));
        assertNotNull(tree.search(1));
        assertNotNull(tree.search(-1));
        assertNull(tree.search(5));
        assertNotNull(tree.search(-3));
        assertNotNull(tree.search(-2));
        assertNull(tree.search(-8));
        String[] temp = { "temp" };
        tree.insert(new BSTNode<Integer>(25, new Seminar(1, "0", "1", 0,
            (short)0, (short)0, 0, temp, "test")));
        tree.searchAll(25);
    }


    /**
     * Test delete methods
     */
    public void testDelete2() {
        tree.insert(new BSTNode<Integer>(50, new Seminar(1, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(30, new Seminar(2, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.delete(50, 1);
        assertEquals(tree.getNodeCount(), 1);
        tree.delete(30, 2);
        tree.insert(new BSTNode<Integer>(50, new Seminar(1, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(60, new Seminar(2, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.delete(60, 2);
        assertEquals(tree.getNodeCount(), 1);
    }


    /**
     * Test delete methods
     */
    public void testDelete() {
        tree.insert(new BSTNode<Integer>(50, new Seminar(1, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(30, new Seminar(2, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(70, new Seminar(3, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(10, new Seminar(4, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(40, new Seminar(5, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(30, new Seminar(6, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(50, new Seminar(7, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(70, new Seminar(8, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(900, new Seminar(9, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(0, new Seminar(10, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(130, new Seminar(11, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        tree.insert(new BSTNode<Integer>(-4, new Seminar(12, null, null, 0,
            (short)0, (short)0, 0, null, null)));

        tree.delete(50, 1);
        assertNotNull(tree.search(50));
        tree.delete(10, 4);
        assertNull(tree.search(10));
        tree.delete(30, 2);
        tree.printTree();
        assertEquals(tree.search(30).id(), 6);
        tree.delete(30, 6);
        tree.delete(50, 7);
        assertNull(tree.search(50));
        tree.delete(70, 99);
        assertNotNull(tree.search(70));
        tree.delete(70, 3);
        assertNotNull(tree.search(70));
        assertEquals(tree.search(70).id(), 8);
        tree.delete(70, 8);
        assertNull(tree.search(70));
        tree.delete(null, 0);
        tree.delete(130, 11);
        assertNull(tree.search(130));
        tree.delete(200, 14);

        BST<Integer> t2 = new BST<Integer>();
        t2.insert(new BSTNode<Integer>(1, new Seminar(1, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.insert(new BSTNode<Integer>(3, new Seminar(3, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.insert(new BSTNode<Integer>(5, new Seminar(5, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.insert(new BSTNode<Integer>(7, new Seminar(7, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.insert(new BSTNode<Integer>(9, new Seminar(9, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.delete(7, 7);
        assertNull(t2.search(7));
        assertNotNull(t2.search(1));
        assertNotNull(t2.search(3));
        t2.insert(new BSTNode<Integer>(6, new Seminar(6, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.delete(5, 5);
        assertNull(t2.search(5));
        assertNotNull(t2.search(6));
        t2.insert(new BSTNode<Integer>(4, new Seminar(4, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.insert(new BSTNode<Integer>(2, new Seminar(2, null, null, 0,
            (short)0, (short)0, 0, null, null)));
        t2.delete(3, 3);
        assertNotNull(t2.search(4));
        assertNotNull(t2.search(2));
        t2.delete(1, 1);
        assertNull(t2.search(1));
        assertNotNull(t2.getRoot());
        t2.delete(10, 10);
        assertNotNull(t2.search(9));
        assertNotNull(t2.search(6));
        BST<Integer> empty = new BST<Integer>();
        empty.delete(1, 1);
        assertNull(empty.getRoot());

        t2.delete(null, 0);
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
    public void testPrintAndSearch() throws IOException {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "testInput.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("testOutput.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test combination of commands
     * 
     * @throws IOException
     */
    public void testPrintAndSearch2() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "testInput2.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("testOutput2.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test combination of commands
     * 
     * @throws IOException
     */
    public void testPrintAndSearch3() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "P2_sampleInput.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("P2_sampleOutput.txt");

        assertFuzzyEquals(referenceOutput, output);
    }
}
