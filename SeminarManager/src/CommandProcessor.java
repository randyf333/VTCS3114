import java.io.File;
import java.util.Scanner;

/**
 * @author Randy Fu randyf333
 *         readLines based mostly on OpenDSA
 * @version 9/25/24
 */
public class CommandProcessor {

    /**
     * @param inFile
     *            input file name
     * @param memorySize
     *            Size of world
     * @param tableSize
     *            Size of table
     */
    public static void readLines(String inFile, int memorySize, int tableSize) {
        try {
            Controller controller = new Controller(memorySize, tableSize);
            Scanner sc = new Scanner(new File(inFile));
            Scanner scancmd;
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.length() == 0) {
                    continue;
                }
                scancmd = new Scanner(line);
                String cmd = scancmd.next();
                String type;
                switch (cmd) {
                    case "insert":
                        int semID = scancmd.nextInt();
                        // Get original input to print out if duplicate
                        String title = sc.nextLine().trim();
                        String numberLine = sc.nextLine();
                        scancmd = new Scanner(numberLine);

                        String date = scancmd.next();
                        int length = scancmd.nextInt();
                        short xcoord = scancmd.nextShort();
                        short ycoord = scancmd.nextShort();
                        int cost = scancmd.nextInt();
                        String[] keywords = sc.nextLine().trim().split("\\s+");
                        String description = sc.nextLine().replaceAll("\\s+",
                            " ").trim();

                        Seminar s = new Seminar(semID, title, date, length,
                            xcoord, ycoord, cost, keywords, description);
                        // insert seminar into table
                        controller.insert(s);
                        break;
                    case "delete":
                        controller.delete(scancmd.nextInt());
                        break;
                    case "search":
                        controller.search(scancmd.nextInt());
                        break;
                    case "print":
                        type = scancmd.next();
                        switch (type) {
                            case "hashtable":
                                controller.printTable();
                                break;
                            case "blocks":
                                controller.printFreeList();
                                break;
                            default:
                                System.out.println("Bad print type" + type);
                                break;
                        }
                        break;
                    default:
                        System.out.println("Unrecognized input");
                        break;
                }
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
