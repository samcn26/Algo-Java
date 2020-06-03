public class AlgoUtils {
    /**
     * e.g. if x = 100 -> return 1+2+...100
     * @param x a specific number
     * @return accumulated value
     */
    static int sumOneToN(int x) {
        boolean b = x > 0 && (x += sumOneToN(x - 1)) > 0;
        return x;
    }

    /**
     * e.g. l1 2->5->8, l2 3->0->2,  return 5->5->0->1
     * @param l1
     * @param l2
     * @return combined list node
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // to check the sum of each node, if bigger than 0, will move to next
        int carry = 0;
        ListNode dummyNode = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyNode;
        while ( p !=null || q != null ) {
            // to avoid figure length not match each other
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            // update carry
            carry = sum / 10; // e.g. 19 / 10 = 1, move to next node
            // update current node
            curr.next = new ListNode(sum % 10);  // e.g. 19 % 10 = 9 only has one digit
            curr = curr.next;
            // continue process
            if (p!=null) p = p.next;
            if (q!=null) q = q.next;
        }

        // consider about edge case
        if(carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyNode.next;
    }
}
