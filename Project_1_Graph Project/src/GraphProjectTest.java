import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 * /**
 * 
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class GraphProjectTest extends TestCase {
    // ----------------------------------------------------------
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
     * Set up the tests that follow.
     */
    public void setUp() { // Nothing needed yet

    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }


    /**
     * Example 2: This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     *
     * @throws Exception
     */
    public void testSampleIO() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "solutionTestData/P1_sampleInput.txt";

        // Invoke main method of our Graph Project
        GraphProject.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P1_sampleOutput.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

    }


    /**
     * Run test with empty text file
     * 
     * @throws Exception
     */
    public void testSampeIO2() throws Exception {
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "solutionTestData/emptyFileTest.txt";
        GraphProject.main(args);
        String actualOutput = systemOut().getHistory();
        String expectedOutput = readFile(
            "solutionTestData/emptyFileOutput.txt");
        assertFuzzyEquals(expectedOutput, actualOutput);
        args[1] = "solutionTestData/testInput.txt";
        GraphProject.main(args);
        actualOutput = systemOut().getHistory();
        expectedOutput = readFile("solutionTestData/testOutput.txt");
        assertFuzzyEquals(expectedOutput, actualOutput);
        Exception e;
        try {
            args[1] = "FileNoExist.txt";
            GraphProject.main(args);

        }
        catch (Exception exception) {
            e = exception;
            assertTrue(e instanceof FileNotFoundException);
        }
    }
}
