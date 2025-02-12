/**
 * @author Randy Fu
 *         implementation based off OpenDSA
 * @version 9/24/2024
 * @param <T>
 */
public class BST<T extends Comparable<T>> {
    private BSTNode<T> root;
    private int nodeCount;

    /**
     * Constructor for binary search tree
     */
    public BST() {
        root = null;
        nodeCount = 0;
    }


    /**
     * Get entire tree starting from root
     * 
     * @return
     *         root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }


    /**
     * Get the number of nodes in tree
     * 
     * @return
     *         number of nodes in tree
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * Insert node into tree
     * 
     * @param node
     *            Node to be inserted
     */
    public void insert(BSTNode<T> node) {
        root = insertHelper(root, node);
        nodeCount++;
    }


    private BSTNode<T> insertHelper(BSTNode<T> rootNode, BSTNode<T> node) {
        if (rootNode == null) {
            return new BSTNode<T>(node.key(), node.value());
        }
        if (rootNode.key().compareTo(node.key()) >= 0) {
            rootNode.setLeft(insertHelper(rootNode.getLeft(), node));
        }
        else {
            rootNode.setRight(insertHelper(rootNode.getRight(), node));
        }
        return rootNode;
    }


    /**
     * Search for the record in tree
     * 
     * @param value
     *            value to search for
     * @return
     *         found record or null if not found
     */
    public Seminar search(T value) {
        return searchHelper(root, value);
    }


    private Seminar searchHelper(BSTNode<T> rootNode, T value) {
        if (rootNode == null) {
            return null;
        }
        if (rootNode.key().compareTo(value) > 0) {
            return searchHelper(rootNode.getLeft(), value);
        }
        else if (rootNode.key().compareTo(value) == 0) {
            return rootNode.value();
        }
        else {
            return searchHelper(rootNode.getRight(), value);
        }
    }


    /**
     * Find all records that match with value
     * 
     * @param value
     *            Value to search for
     */
    public void searchAll(T value) {
        searchAllHelper(root, value);
    }


    private void searchAllHelper(BSTNode<T> rootNode, T value) {
        if (rootNode == null) {
            return;
        }
        if (rootNode.key().compareTo(value) >= 0) {
            searchAllHelper(rootNode.getLeft(), value);
        }
        if (rootNode.key().compareTo(value) == 0) {
            System.out.println(rootNode.value().toString());
        }
        else {
            searchAllHelper(rootNode.getRight(), value);
        }
    }


    /**
     * Search tree for items within range and print them
     * 
     * @param low
     *            minimum value
     * @param high
     *            maximum value
     * @return count
     *         number of nodes visited in the search
     */
    public int rangeSearch(T low, T high) {
        return rangeSearchHelper(low, high, root);
    }


    private int rangeSearchHelper(T low, T high, BSTNode<T> rootNode) {
        if (rootNode == null) {
            return 1;
        }
        int visited = 1;
        if (rootNode.key().compareTo(low) >= 0) {
            visited += rangeSearchHelper(low, high, rootNode.getLeft());
        }
        if (rootNode.key().compareTo(low) >= 0 && rootNode.key().compareTo(
            high) <= 0) {
            System.out.println(rootNode.value().toString());
        }
        if (rootNode.key().compareTo(high) < 0) {
            visited += rangeSearchHelper(low, high, rootNode.getRight());
        }
        return visited;
    }


    /**
     * Inorder traversal of tree to print
     */
    public void printTree() {
        if (root == null) {
            System.out.println("This tree is empty");
            return;
        }
        int treeHeight = findHeight(root);
        printHelper(root, 0, treeHeight);
        System.out.println("Number of records: " + nodeCount);
    }


    private int findHeight(BSTNode<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }
        int lheight = findHeight(rootNode.getLeft());
        int rheight = findHeight(rootNode.getRight());
        return Math.max(lheight, rheight) + 1;
    }


    private void printHelper(BSTNode<T> rootNode, int level, int height) {
        String indents = "    ".repeat(height - level);
        if (rootNode == null) {
            System.out.println(indents + "(null)");
            return;
        }
        printHelper(rootNode.getLeft(), level + 1, height);
        System.out.println(indents + "\\");
        System.out.println(indents + "(" + rootNode.key() + ")");
        System.out.println(indents + "/");
        printHelper(rootNode.getRight(), level + 1, height);
    }


    /**
     * Delete node from the tree
     * 
     * @param value
     *            key to search for in tree and delete
     * @param semID
     *            Seminar ID to check deletion
     */
    public void delete(T value, int semID) {
        root = deleteHelper(root, value, semID);
    }


    private BSTNode<T> deleteHelper(BSTNode<T> rootNode, T value, int semID) {
        if (rootNode == null || value == null) {
            return null;
        }
        if (rootNode.key().compareTo(value) >= 0) {
            rootNode.setLeft(deleteHelper(rootNode.getLeft(), value, semID));
        }
        else if (rootNode.key().compareTo(value) < 0) {
            rootNode.setRight(deleteHelper(rootNode.getRight(), value, semID));
        }

        if (rootNode.value().id() == semID) {
            nodeCount--;
            if (rootNode.getLeft() == null) {
                return rootNode.getRight();
            }
            else if (rootNode.getRight() == null) {
                return rootNode.getLeft();
            }
            BSTNode<T> temp = getMax(rootNode.getLeft());
            rootNode.setKey(temp.key());
            rootNode.setValue(temp.value());
            rootNode.setLeft(deleteMax(rootNode.getLeft()));
        }
        return rootNode;
    }


    private BSTNode<T> getMax(BSTNode<T> rt) {
        if (rt.getRight() == null) {
            return rt;
        }
        return getMax(rt.getRight());
    }


    private BSTNode<T> deleteMax(BSTNode<T> rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
    }

}
