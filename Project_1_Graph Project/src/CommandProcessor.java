import java.io.File;
import java.util.Scanner;

/**
 * @author Randy Fu randyf333
 *         readLines based mostly on OpenDSA
 * @version 8/31/24
 */
public class CommandProcessor {

    /**
     * @param inFile
     *            input file name
     * @param tableSize
     *            Size of hash table
     */
    public static void readLines(String inFile, int tableSize) {
        try {
            Controller c = new Controller(tableSize);
            Scanner sc = new Scanner(new File(inFile));
            Scanner scancmd;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                scancmd = new Scanner(line);
                String cmd = scancmd.next();
                String type;
                switch (cmd) {
                    case "insert":
                        scancmd.useDelimiter("<SEP>");
                        // Get original input to print out if duplicate

                        String artist = scancmd.next().trim();
                        String song = scancmd.next().trim();
                        c.insert(artist, song);
                        break;
                    case "remove":
                        type = scancmd.next();
                        String name = scancmd.nextLine().trim();
                        switch (type) {
                            case "artist":
                                c.removeArtist(name);
                                break;
                            case "song":
                                c.removeSong(name);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "print":
                        type = scancmd.next();
                        switch (type) {
                            case "artist":
                                c.printArtists();
                                break;
                            case "song":
                                c.printSongs();
                                break;
                            case "graph":
                                // print out number of connected components and
                                // the number of nodes in largest
                                c.printGraph();
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
