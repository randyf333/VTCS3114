/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class Controller {
    private Hash songTable;
    private Hash artistTable;
    private Graph graph;

    /**
     * Constructor for Controller
     * 
     * @param tableSize
     *            Size of the hash table
     * @throws FileNotFoundException
     */
    public Controller(int tableSize) {
        songTable = new Hash(tableSize, "Song");
        artistTable = new Hash(tableSize, "Artist");
        graph = new Graph(tableSize);
    }


    /**
     * Insert input into proper table
     * 
     * @param artistName
     *            Name of artist
     * @param songName
     *            Name of song
     */
    public void insert(String artistName, String songName) {
        // Check if edge exists between two nodes in graph
        if (artistTable.findRecord(artistName) == null) {
            int artistIndex = graph.insertNode();
            artistTable.insertRecord(artistName, artistIndex);
            System.out.println("|" + artistName
                + "| is added to the Artist database.");
        }
        if (songTable.findRecord(songName) == null) {
            int songIndex = graph.insertNode();
            songTable.insertRecord(songName, songIndex);
            System.out.println("|" + songName
                + "| is added to the Song database.");
        }

        int index1 = artistTable.findRecord(artistName).getNode().getIndex();
        int index2 = songTable.findRecord(songName).getNode().getIndex();
        if (!graph.hasEdge(index1, index2)) {
            graph.addEdge(index1, index2);
        }
        else {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");
        }

    }


    /**
     * remove from artist hash table
     * 
     * @param s
     *            name of artist
     */
    public void removeArtist(String s) {
        if (artistTable.findRecord(s) == null) {
            System.out.println("|" + s
                + "| does not exist in the Artist database.");
            return;
        }
        graph.removeNode(artistTable.findRecord(s).getNode().getIndex());
        artistTable.removeRecord(s);
        System.out.println("|" + s + "| is removed from the Artist database.");
    }


    /**
     * remove from song hash table
     * 
     * @param s
     *            name of song
     */
    public void removeSong(String s) {
        if (songTable.findRecord(s) == null) {
            System.out.println("|" + s
                + "| does not exist in the Song database.");
            return;
        }
        graph.removeNode(songTable.findRecord(s).getNode().getIndex());
        songTable.removeRecord(s);
        System.out.println("|" + s + "| is removed from the Song database.");
    }


    /**
     * Print out all artists
     */
    public void printArtists() {
        int count = artistTable.printRecords();
        System.out.println("total artists: " + count);
    }


    /**
     * Print out all songs
     */
    public void printSongs() {
        int count = songTable.printRecords();
        System.out.println("total songs: " + count);
    }


    /**
     * Print out the graphs connected components and size of largest component
     */
    public void printGraph() {
        int connectedComponents = graph.unionFind();
        int largestSize = graph.sizeLargestComponenet();
        System.out.println("There are " + connectedComponents
            + " connected components");
        System.out.println("The largest connected component has " + largestSize
            + " elements");
    }
}
