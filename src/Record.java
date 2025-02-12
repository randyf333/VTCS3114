/**
 * @author Randy Fu randyf333
 *         readLines based mostly on OpenDSA
 * @version 8/31/24
 */

public class Record {
    private String key;
    private Node node;

    /**
     * Create new Record
     * 
     * @param keyName
     *            name of record
     * @param n
     *            node to put in Record
     * 
     */
    public Record(String keyName, Node n) {
        key = keyName;
        node = n;
    }


    /**
     * get record key
     * 
     * @return
     *         key
     */
    public String getKey() {
        return key;
    }


    /**
     * get record node
     * 
     * @return
     *         node
     */
    public Node getNode() {
        return node;
    }


    /**
     * Set node value
     * 
     * @param data
     *            value new node contains
     */
    public void setNode(int data) {
        node = new Node(data);
    }
}
