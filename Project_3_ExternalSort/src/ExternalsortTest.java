import java.io.IOException;
import student.TestCase;

/**
 * @author Randy Fu
 * @version 10/26
 */
public class ExternalsortTest extends TestCase {

    /**
     * set up for tests
     */
    public void setUp() {
        // nothing to set up.
    }

    /**
     * Test with 8 blocks
     * 
     * @throws IOException
     */

    /*
     * public void testExternalsort8() throws IOException {
     * ByteFile b = new ByteFile("testFile.bin", 8);
     * b.writeRandomRecords();
     * String[] args = { "testFile.bin" };
     * Externalsort.main(args);
     * ByteFile c = new ByteFile("testFile.bin", 8);
     * // ByteFile a = new ByteFile("tempFile.bin",8);
     * assertTrue(c.isSorted());
     * // assertTrue(a.isSorted());
     * }
     */

    /**
     * Test with 16 blocks
     * 
     * @throws IOException
     */

    /*
     * public void testExternalsort16() throws IOException {
     * ByteFile b = new ByteFile("testFile.bin", 16);
     * b.writeRandomRecords();
     * String[] args = { "testFile.bin" };
     * Externalsort.main(args);
     * // ByteFile c = new ByteFile("runFile.bin",16);
     * ByteFile a = new ByteFile("testFile.bin", 16);
     * // assertTrue(c.isSorted());
     * assertTrue(a.isSorted());
     * }
     */
    /**
     * Test with 24 blocks
     * 
     * @throws IOException
     */
    /*
     * public void testExternalsort24() throws IOException {
     * ByteFile b = new ByteFile("testFile.bin",24);
     * b.writeRandomRecords();
     * String[] args = { "testFile.bin" };
     * Externalsort.main(args);
     * // ByteFile c = new ByteFile("runFile.bin",16);
     * ByteFile a = new ByteFile("testFile.bin", 24);
     * // assertTrue(c.isSorted());
     * assertTrue(a.isSorted());
     * }
     */


    /**
     * Test with 32 blocks
     * 
     * @throws IOException
     */
    /*
     * public void testExternalsort32() throws IOException {
     * ByteFile b = new ByteFile("testFile.bin", 32);
     * b.writeRandomRecords();
     * String[] args = { "testFile.bin" };
     * Externalsort.main(args);
     * // ByteFile c = new ByteFile("runFile.bin",16);
     * ByteFile a = new ByteFile("testFile.bin", 32);
     * // assertTrue(c.isSorted());
     * assertTrue(a.isSorted());
     * }
     */
    
    /**
     * Test with 48 blocks
     * 
     * @throws IOException
     */
    public void testExternalsort48() throws IOException {
        //ByteFile b = new ByteFile("testFile.bin", 48);
        //b.writeRandomRecords();
        String[] args = { "testFile.bin" };
        Externalsort.main(args);
        // ByteFile c = new ByteFile("runFile.bin",16);
        ByteFile a = new ByteFile("testFile.bin", 48);
        // assertTrue(c.isSorted());
        assertTrue(a.isSorted());
    }

    /**
     * Test with 200 blocks
     * 
     * @throws IOException
     */
    /*
     * public void testExternalsort200() throws IOException {
     * ByteFile b = new ByteFile("testFile.bin", 200);
     * b.writeRandomRecords();
     * String[] args = { "testFile.bin" };
     * Externalsort.main(args);
     * // ByteFile c = new ByteFile("runFile.bin",16);
     * ByteFile a = new ByteFile("testFile.bin", 200);
     * assertTrue(a.isSorted());
     * // assertTrue(a.isSorted());
     * }
     */

}
