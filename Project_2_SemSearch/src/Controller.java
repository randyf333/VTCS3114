/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class Controller {
    private BST<Integer> idTree;
    private BST<String> dateTree;
    private BST<Integer> costTree;
    private BST<String> keywordTree;
    private BinTree locationTree;
    private int worldSize;

    /**
     * Constructor for Controller
     * 
     * @param world
     *            size of world
     */
    public Controller(int world) {
        idTree = new BST<Integer>();
        dateTree = new BST<String>();
        costTree = new BST<Integer>();
        keywordTree = new BST<String>();
        locationTree = new BinTree();
        worldSize = world;
    }


    /**
     * Insert seminar data into trees
     * 
     * @param s
     *            Seminar to be inserted
     */
    public void insert(Seminar s) {
        if (s.x() < 0 || s.y() < 0 || s.x() > worldSize - 1 || s.y() > worldSize
            - 1) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + s.x()
                + ", " + s.y());
            return;
        }
        if (idTree.search(s.id()) == null) {
            idTree.insert(new BSTNode<Integer>(s.id(), s));
            dateTree.insert(new BSTNode<String>(s.date(), s));
            costTree.insert(new BSTNode<Integer>(s.cost(), s));
            for (String keyword : s.keywords()) {
                keywordTree.insert(new BSTNode<String>(keyword, s));
            }
            locationTree.insert(s, s.x(), s.y(), worldSize, worldSize);
            System.out.println("Successfully inserted record with ID " + s
                .id());
            System.out.println(s.toString());
        }
        else {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + s.id());
        }
    }


    /**
     * Print ID tree
     */
    public void printID() {
        idTree.printTree();
    }


    /**
     * Print date tree
     */
    public void printDate() {
        dateTree.printTree();
    }


    /**
     * Print cost tree
     */
    public void printCost() {
        costTree.printTree();
    }


    /**
     * Print keyword tree
     */
    public void printKeywords() {
        keywordTree.printTree();
    }


    /**
     * Search in ID tree
     * 
     * @param id
     *            id to search for
     */
    public void idSearch(int id) {
        Seminar temp = idTree.search(id);
        if (temp == null) {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Found record with ID " + id + ":");
            System.out.println(temp.toString());
        }
    }


    /**
     * Search date tree
     * 
     * @param startDate
     *            lower bound
     * @param endDate
     *            upper bound
     */
    public void dateSearch(String startDate, String endDate) {
        System.out.println("Seminars with dates in range " + startDate + " to "
            + endDate + ":");
        int nodeCount = dateTree.rangeSearch(startDate, endDate);
        System.out.println(nodeCount + " nodes visited in this search");
    }


    /**
     * Search in cost tree
     * 
     * @param low
     *            lower bound
     * @param high
     *            upper bound
     */
    public void costSearch(int low, int high) {
        System.out.println("Seminars with costs in range " + low + " to " + high
            + ":");
        int nodeCount = costTree.rangeSearch(low, high);
        System.out.println(nodeCount + " nodes visited in this search");
    }


    /**
     * Search in keyword tree
     * 
     * @param keyword
     *            Keyword to search for
     */
    public void keywordSearch(String keyword) {
        System.out.println("Seminars matching keyword " + keyword + ":");
        keywordTree.searchAll(keyword);
    }


    /**
     * Search in location tree
     * 
     * @param x
     *            x coord of search
     * @param y
     *            y coord of search
     * @param radius
     *            radius of search circle
     */
    public void locationSearch(int x, int y, int radius) {
        System.out.println("Seminars within " + radius + " units of " + x + ", "
            + y + ":");
        int nodesVisited = locationTree.search(x, y, radius, worldSize,
            worldSize);
        System.out.println(nodesVisited + " nodes visited in this search");
    }


    /**
     * Delete from all BSTs
     * 
     * @param id
     *            Seminar id to be deleted
     */
    public void delete(int id) {
        Seminar removed = idTree.search(id);
        if (removed == null) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
            return;
        }
        idTree.delete(id, id);
        dateTree.delete(removed.date(), id);
        costTree.delete(removed.cost(), id);
        for (String s : removed.keywords()) {
            keywordTree.delete(s, id);
        }
        locationTree.delete(removed, removed.x(), removed.y(), worldSize,
            worldSize);
        System.out.println("Record with ID " + id
            + " successfully deleted from the database");
    }


    /**
     * Print location tree
     */
    public void printLocation() {
        locationTree.printTree();
    }

}
