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
     * @param worldSize
     *            Size of world
     */
    public static void readLines(String inFile, int worldSize) {
        try {
            Controller c = new Controller(worldSize);
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
                        String description = sc.nextLine().trim();

                        Seminar s = new Seminar(semID, title, date, length,
                            xcoord, ycoord, cost, keywords, description);
                        c.insert(s);
                        break;
                    case "delete":
                        c.delete(scancmd.nextInt());
                        break;
                    case "search":
                        type = scancmd.next();
                        switch (type) {
                            case "ID":
                                int id = scancmd.nextInt();
                                c.idSearch(id);
                                break;
                            case "cost":
                                int low = scancmd.nextInt();
                                int high = scancmd.nextInt();
                                c.costSearch(low, high);
                                break;
                            case "date":
                                String startDate = scancmd.next();
                                String endDate = scancmd.next();
                                c.dateSearch(startDate, endDate);
                                break;
                            case "keyword":
                                String keyword = scancmd.next();
                                c.keywordSearch(keyword);
                                break;
                            case "location":
                                int centerx = scancmd.nextInt();
                                int centery = scancmd.nextInt();
                                int radius = scancmd.nextInt();
                                c.locationSearch(centerx, centery, radius);
                                break;

                        }
                        break;
                    case "print":
                        type = scancmd.next();
                        switch (type) {
                            case "ID":
                                System.out.println("ID Tree:");
                                c.printID();
                                break;
                            case "cost":
                                System.out.println("Cost Tree:");
                                c.printCost();
                                break;
                            case "date":
                                System.out.println("Date Tree:");
                                c.printDate();
                                // print out number of connected components and
                                break;
                            case "keyword":
                                System.out.println("Keyword Tree:");
                                c.printKeywords();
                                break;
                            case "location":
                                System.out.println("Location Tree:");
                                c.printLocation();
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
