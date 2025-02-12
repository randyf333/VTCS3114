import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class ProblemSpecTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
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


    /**
     * Full parser test
     * 
     * @throws IOException
     */

    public void testparserfull() throws IOException {
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "P4Sample_input.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P4Sample_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Simple parser test (input only)
     * 
     * @throws IOException
     */
    public void testparserinput() throws IOException {
        String[] args = new String[3];
        args[0] = "2048";
        args[1] = "16";
        args[2] = "P4SimpSample_input.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("P4SimpSample_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test only hash methods
     * 
     * @throws IOException
     */
    public void testHashTable() throws IOException {
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "hashTestInputs.txt";
        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("hashTestOutputs.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Test insert for memManager
     * 
     * @throws IOException
     */
    public void testMemInsert() throws IOException {
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "memTestInputs.txt";
        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("memTestOutputs.txt");
        assertFuzzyEquals(referenceOutput, output);
    }
}
