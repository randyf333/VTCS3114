/**
 * /**
 * @author Randy Fu randyf333
 * @version 10/14/24
 * @param <T>
 */
public class BSTNode<T extends Comparable<T>> {
    private T key;
    private Seminar value;
    private BSTNode<T> left;
    private BSTNode<T> right;

    /**
     * Constructor of TreeNode
     * 
     * @param k
     *            Generic key value
     * @param s
     *            Seminar
     */
    public BSTNode(T k, Seminar s) {
        key = k;
        value = s;
    }


    /**
     * Get key
     * 
     * @return
     *         generic key
     */
    public T key() {
        return key;
    }


    /**
     * Get the seminar stored in Node
     * 
     * @return
     *         seminar
     */
    public Seminar value() {
        return value;
    }


    /**
     * Set the left child
     * 
     * @param t
     *            new left treeNode
     */
    public void setLeft(BSTNode<T> t) {
        left = t;
    }


    /**
     * Set right child
     * 
     * @param t
     *            new right treeNode
     */
    public void setRight(BSTNode<T> t) {
        right = t;
    }


    /**
     * Get left child of node
     * 
     * @return
     *         left treeNode
     */
    public BSTNode<T> getLeft() {
        return left;
    }


    /**
     * Get right child of node
     * 
     * @return
     *         right treeNode
     */
    public BSTNode<T> getRight() {
        return right;
    }


    /**
     * Set new value for key
     * 
     * @param newKey
     *            new key
     */
    public void setKey(T newKey) {
        key = newKey;
    }


    /**
     * Set new value for seminar
     * 
     * @param s
     *            new seminar value
     */
    public void setValue(Seminar s) {
        value = s;
    }
}
