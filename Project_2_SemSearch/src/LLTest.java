import student.TestCase;

/**
 * @author Randy Fu randyf333
 * @version 10/14/24
 */
public class LLTest extends TestCase {
    private LinkedList list;

    /**
     * Setup method
     */
    public void setUp() {
        list = new LinkedList();
    }


    /**
     * Test insert method
     */
    public void testInsert() {
        Seminar s1 = new Seminar(1, null, null, 0, (short)0, (short)0, 0, null,
            null);
        LLNode n1 = new LLNode(s1);
        list.insertNode(n1);
        Seminar s2 = new Seminar(2, null, null, 0, (short)0, (short)0, 0, null,
            null);
        LLNode n2 = new LLNode(s2);
        list.insertNode(n2);
        assertEquals(list.getCount(), 2);
        assertEquals(1, list.getNode().getSem().id());
        LLNode temp = list.getNode().getNext();
        assertEquals(2, temp.getSem().id());
        LLNode n3 = new LLNode(new Seminar(3, null, null, 0, (short)0, (short)0,
            0, null, null));
        LLNode n4 = new LLNode(new Seminar(4, null, null, 0, (short)0, (short)0,
            0, null, null));
        list.insertNode(n4);
        list.insertNode(n3);
        assertNotNull(temp.getNext());
        assertEquals(temp.getNext().getSem().id(), 3);

        LinkedList l2 = new LinkedList();
        l2.insertNode(n4);
        l2.insertNode(n1);
        l2.insertNode(n3);
        l2.insertNode(n2);
        LLNode t2 = l2.getNode();
        assertEquals(t2.getSem().id(), 1);
        t2 = t2.getNext();
        assertEquals(t2.getSem().id(), 2);
        t2 = t2.getNext();
        assertEquals(t2.getSem().id(), 3);
        t2 = t2.getNext();
        assertEquals(t2.getSem().id(), 4);
        t2 = t2.getNext();
        assertNull(t2);
    }


    /**
     * Test remove method
     */
    public void testRemove() {
        Seminar s1 = new Seminar(1, null, null, 0, (short)0, (short)0, 0, null,
            null);
        Seminar s4 = new Seminar(4, null, null, 0, (short)0, (short)0, 0, null,
            null);
        Seminar s3 = new Seminar(3, null, null, 0, (short)0, (short)0, 0, null,
            null);
        Seminar s2 = new Seminar(2, null, null, 0, (short)0, (short)0, 0, null,
            null);
        Seminar s5 = new Seminar(5, null, null, 0, (short)0, (short)0, 0, null,
            null);
        LLNode n3 = new LLNode(s3);
        LLNode n4 = new LLNode(s4);
        LLNode n1 = new LLNode(s1);
        LLNode n2 = new LLNode(s2);
        LLNode n5 = new LLNode(s5);

        list.removeNode(s1);
        list.insertNode(n1);
        assertEquals(1, list.getNode().getSem().id());
        list.removeNode(s1);
        assertNull(list.getNode());
        list.insertNode(n2);
        list.insertNode(n3);
        list.removeNode(s4);
        assertEquals(2, list.getCount());
        list.removeNode(s2);
        assertEquals(1, list.getCount());
        assertEquals(3, list.getNode().getSem().id());
        list.insertNode(n4);
        list.removeNode(s4);
        assertEquals(1, list.getCount());
        assertEquals(3, list.getNode().getSem().id());
        list.insertNode(n2);
        list.insertNode(n5);
        assertEquals(2, list.getNode().getSem().id());
        list.removeNode(s3);
        assertEquals(2, list.getCount());
        list.removeNode(s2);
        assertEquals(5, list.getNode().getSem().id());
        list.removeNode(s5);
        assertEquals(0, list.getCount());
        list.removeNode(s1);
    }
}
