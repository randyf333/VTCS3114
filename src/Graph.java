/**
 * @author Randy Fu randyf333
 *         Graph implementation and methods based mostly on OpenDSA
 * @version 8/31/24
 */
public class Graph {

    private DLLNode[] vertex;
    private DLLNode freeList;
    private int counter;
    private int nodeCount;
    private int[] parents;
    private int[] countArray;

    /**
     * Create a new graph
     * 
     * @param initialSize
     *            initial size of array
     */
    public Graph(int initialSize) {
        vertex = new DLLNode[initialSize];
        counter = 0;
        freeList = new DLLNode(0);
        nodeCount = 0;
    }


    /**
     * get adjacency list
     * 
     * @return
     *         vertex
     */
    public DLLNode[] getNodes() {
        return vertex;
    }


    /**
     * Get number of nodes in graph
     * 
     * @return
     *         nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * get number of nodes in
     * 
     * @return
     *         how many free slots there are in the list
     */

    private int getFreeSlots() {
        return vertex.length - nodeCount;
    }


    /**
     * Add node to graph
     * 
     * @return
     *         index where the node is added
     */
    public int insertNode() {
        if (getFreeSlots() == 0) {
            expandGraph();
        }
        if (freeList.getNext() == null) {
            freeList.setNext(new DLLNode(counter + 1));
            counter++;
        }
        int inputIndex = freeList.getIndex();
        vertex[inputIndex] = new DLLNode(inputIndex);
        freeList = freeList.getNext();
        nodeCount++;
        return inputIndex;
    }


    /**
     * Add edge between two nodes
     * 
     * @param n1
     *            first node index
     * @param n2
     *            second node index
     */
    public void addEdge(int n1, int n2) {
        if (!hasEdge(n1, n2)) {
            DLLNode curr = vertex[n1];
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(new DLLNode(n2));
            curr.getNext().setPrev(curr);
            DLLNode curr2 = vertex[n2];
            while (curr2.getNext() != null) {
                curr2 = curr2.getNext();
            }
            curr2.setNext(new DLLNode(n1));
            curr2.getNext().setPrev(curr2);
        }
    }


    /**
     * Check if there exists an edge between two nodes
     * 
     * @param n1
     *            first node
     * @param n2
     *            second node
     * @return
     *         true if there is an edge, false if not
     */
    public boolean hasEdge(int n1, int n2) {
        DLLNode base = vertex[n1];
        while (base.getNext() != null) {
            if (base.getNext().getIndex() == n2) {
                return true;
            }
            base = base.getNext();
        }
        return false;
    }


    /**
     * remove the edge between two nodes
     * 
     * @param n1
     *            first node
     * @param n2
     *            second node
     */
    public void removeEdge(int n1, int n2) {
        DLLNode temp = vertex[n1];
        while (temp.getNext() != null) {
            if (temp.getNext().getIndex() == n2) {
                if (temp.getNext().getNext() != null) {
                    temp.getNext().getNext().setPrev(temp);
                    temp.setNext(temp.getNext().getNext());
                }
                else {
                    temp.setNext(null);
                }
                return;
            }
            temp = temp.getNext();
        }
    }


    /**
     * Double size of graph when it reaches full capacity
     */
    private void expandGraph() {
        DLLNode[] temp = vertex;
        int oldSize = vertex.length;
        vertex = new DLLNode[oldSize * 2];
        for (int i = 0; i < oldSize; i++) {
            vertex[i] = temp[i];
        }
        counter = oldSize;
    }


    /**
     * Remove node from graph and all edges. Add index to freeList
     * 
     * @param index
     *            index of the node to be removed
     */
    public void removeNode(int index) {
        if (vertex[index] != null) {
            DLLNode remove = vertex[index];
            // Remove the edge from all connecting nodes

            while (remove.getNext() != null) {
                removeEdge(remove.getNext().getIndex(), index);
                remove = remove.getNext();
            }
            DLLNode removedIndex = new DLLNode(index);
            removedIndex.setNext(freeList);
            freeList = removedIndex;
            nodeCount--;
            vertex[index] = null;
        }
    }


    /**
     * UNION/FIND method to find connected components
     * 
     * @return
     *         number of connected components
     */
    public int unionFind() {
        // Create array of parent indexes and fill with -1 if there is node at
        // that index and -2 for placeholder
        // make count array. Keep track of how many children does it have
        // including itself
        parents = new int[vertex.length];
        countArray = new int[parents.length];
        for (int i = 0; i < parents.length; i++) {
            if (vertex[i] != null) {
                parents[i] = -1;
                countArray[i] = 1;
            }
            else {
                countArray[i] = 0;
                parents[i] = -2;
            }
        }
        for (int j = 0; j < vertex.length; j++) {
            if (vertex[j] != null) {
                DLLNode curr = vertex[j];
                while (curr.getNext() != null) {
                    union(curr.getIndex(), curr.getNext().getIndex());
                    curr = curr.getNext();
                }
            }
        }
        int connectedComponents = 0;
        for (int k = 0; k < parents.length; k++) {
            if (parents[k] == -1) {
                connectedComponents++;
                //
            }
        }
        return connectedComponents;
    }


    /**
     * Get size of largest connected component
     * 
     * @return
     *         size of largest connected component
     */
    public int sizeLargestComponenet() {
        int max = 0;
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > max) {
                max = countArray[i];
            }
        }
        // Add one for the root node
        return max;
    }


    /**
     * Merge two subtrees if they are different
     * 
     * @param a
     *            index of first subtree
     * @param b
     *            index of second subtree
     */
    private void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        // if count of root 2 > 1, count array of 1 and add to 2, else do
        // opposite
        if (root1 != root2) {
            if (countArray[root1] > countArray[root2]) {
                parents[root2] = root1;
                countArray[root1] += countArray[root2];
            }
            else {
                parents[root1] = root2;
                countArray[root2] += countArray[root1];
            }

        }
    }


    /**
     * Find root of current index
     * 
     * @param currentIndex
     * @return
     *         root node
     */
    private int find(int currentIndex) {
        while (parents[currentIndex] != -1) {
            currentIndex = parents[currentIndex];
        }
        return currentIndex;
    }
}
