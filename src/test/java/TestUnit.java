public class TestUnit {

    static int getListNodeLayer(ListNode l){
        int r = 1;
        ListNode t = l;
        while (t.next != null) {
            r++;
            t = t.next;
        }
        return r;
    }

    static boolean compListNodeVal(ListNode l1, ListNode l2) {
        if(getListNodeLayer(l1) == getListNodeLayer(l2)){
            ListNode p = l1, q = l2;
            boolean b = true;
            while (p!=null && q!=null){
                 b = p.val == q.val;
                if (!b) {
                    break;
                }
                p = p.next;
                q = q.next;
            }
            return b;
        }else{
            return false;
        }
    }
}
