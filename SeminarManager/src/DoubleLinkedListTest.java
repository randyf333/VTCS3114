import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 8/31/24
 */
public class DoubleLinkedListTest extends TestCase {
    private DoubleLinkedList<Integer> list;

    /**
     * Set up object for testing
     */
    public void setUp() {
        list = new DoubleLinkedList<Integer>();
    }


    /**
     * Test all methods
     */
    public void testMethods() {
        DLLNode<Integer> a = new DLLNode<Integer>(1);
        DLLNode<Integer> b = new DLLNode<Integer>(2);
        DLLNode<Integer> c = new DLLNode<Integer>(3);
        DLLNode<Integer> d = new DLLNode<Integer>(4);
        DLLNode<Integer> e = new DLLNode<Integer>(5);
        list.insertNode(a);
        assertEquals(a, list.getHead());
        list.insertNode(b);
        assertEquals(2, list.getCount());
        list.clear();
        assertEquals(0, list.getCount());
        assertNull(list.getHead());
        list.removeNode(a);
        list.insertNode(b);
        assertEquals(list.getHead(), b);
        list.insertNode(c);
        list.insertNode(d);
        list.insertNode(e);
        list.removeNode(d);
        assertEquals(c.getNext(), e);
        assertEquals(c.getPrev(), b);
        assertEquals(e.getPrev(), c);
        assertEquals(e.getNext(), null);
        list.removeNode(e);
        assertEquals(2, list.getCount());
        assertNull(c.getNext());
        list.clear();
        a = new DLLNode<Integer>(1);
        b = new DLLNode<Integer>(2);
        list.insertNode(a);
        list.removeNode(a);
        assertNull(list.getHead());
        list.insertNode(a);
        list.insertNode(b);
        list.removeNode(a);
        assertEquals(1, list.getCount());
        assertEquals(list.getHead(), b);
    }


    /**
     * Test insertNodeBefore method
     */
    public void testinsertNodeBefore() {
        DLLNode<Integer> a = new DLLNode<Integer>(1);
        DLLNode<Integer> b = new DLLNode<Integer>(2);
        DLLNode<Integer> c = new DLLNode<Integer>(3);
        DLLNode<Integer> d = new DLLNode<Integer>(4);

        list.insertNodeBefore(list.getHead(), a);
        assertEquals(list.getCount(), 1);
        assertEquals(list.getHead(), a);
        list.insertNodeBefore(a, b);
        assertEquals(list.getHead(), b);
        list.insertNodeBefore(a.getNext(), c);
        assertEquals(list.getCount(), 3);
        assertEquals(a.getNext(), c);
        list.insertNodeBefore(a, d);
        assertEquals(b.getNext(), d);
        assertEquals(d.getNext(), a);
    }

}
