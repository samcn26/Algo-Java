import static org.junit.Assert.*;
import org.junit.Test;

public class TestAlgo {
    @Test
    public void test1(){
        assertEquals(AlgoUtils.sumOneToN(100), 5050);
    }

    @Test
    public void test2() {
        // 2->5->8
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(8);

        // 3->0->2
        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(2);

        // the result should be 5->5->0->1
        ListNode rst = new ListNode(5);
        rst.next = new ListNode(5);
        rst.next.next = new ListNode(0);
        rst.next.next.next = new ListNode(1);

        ListNode genRst = AlgoUtils.addTwoNumbers(l1, l2);
        assertEquals(TestUnit.compListNodeVal(rst,genRst),true);
    }
}
