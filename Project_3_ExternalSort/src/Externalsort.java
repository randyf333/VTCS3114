import java.io.File;
import java.io.IOException;

/**
 * The class containing the main method.
 *
 * @author Randy Fu
 * @version 10/26
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Externalsort {

    /**
     * @param args
     *            Command line parameters
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        File inputFile = new File(fileName);
        File runFile = new File("runFile.bin");
        ProcessFile p = new ProcessFile();
        p.createRuns(inputFile, runFile);
        // System.out.println(inputFile.length());
        // p.printRecords(runFile);
        p.loopMerges(runFile, inputFile, inputFile);
        // System.out.println(inputFile.length());
        // p.mergeRuns(runFile, inputFile);
        // p.printRecords(inputFile);
        p.printRecords(inputFile);

    }

}
