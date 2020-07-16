import static org.junit.Assert.*;

import org.junit.Test;

public class TestAlgo {
    @Test
    public void test1() {
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
        assertTrue(TestUnit.compListNodeVal(rst, genRst));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{2, 5, 7, 12};
        int target = 9;
        int[] rst = new int[]{0, 2};
        assertArrayEquals(AlgoUtils.twoSum(nums, target), rst);
    }

    @Test
    public void test4() {
        String s1 = "abbabcbb";
        String s2 = "aaaaaaa";
        assertEquals(AlgoUtils.longest(s1), 3);
        assertEquals(AlgoUtils.longest(s2), 1);
    }

    @Test
    public void test5() {
        String s = "babad";
        assertEquals(AlgoUtils.longestPalindrome(s), "bab");

        String s2 = "cbbd";
        assertEquals(AlgoUtils.longestPalindrome(s2), "bb");

        String s3 = "aaa";
        assertEquals(AlgoUtils.longestPalindrome(s3), "aaa");
    }

    @Test
    public void test6() {
        assertEquals(AlgoUtils.convert("LEETCODEISHIRING", 3), "LCIRETOESIIGEDHN");
        assertEquals(AlgoUtils.convert("LEETCODEISHIRING", 4), "LDREOEIIECIHNTSG");
    }

    @Test
    public void test7() {
        assertEquals(AlgoUtils.reverse(-123), -321);
    }

    @Test
    public void test8() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(2);

        ListNode result = new ListNode(1);
        result.next = new ListNode(2);

        ListNode l2 = AlgoUtils.deleteDuplicates(l1);
        assertTrue(TestUnit.compListNodeVal(result, l2));
    }

    @Test
    public void test9() {
        /**
         *
         3
         / \
         9  20
         /  \
         15   7
         */
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        assertEquals(AlgoUtils.maxDepth(treeNode), 3);
    }

    @Test
    public void test10() {
        /*
        Input: 1->2->4, 1->3->4
        Output: 1->1->2->3->4->4
         */
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode expect = new ListNode(1);
        expect.next = new ListNode(1);
        expect.next.next = new ListNode(2);
        expect.next.next.next = new ListNode(3);
        expect.next.next.next.next = new ListNode(4);
        expect.next.next.next.next.next = new ListNode(4);

        assertTrue(TestUnit.compListNodeVal(AlgoUtils.mergeTwoLists(l1, l2), expect));
    }

    @Test
    public void test11() {
        /*
        reverse a listNode 1->2->3->4->null  => 4->3->2->1->null
         */
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(2);
        l2.next.next.next = new ListNode(1);

        assertTrue(TestUnit.compListNodeVal(AlgoUtils.reverseList_1(l1), l2));

        l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);

        l2 = new ListNode(4);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(2);
        l2.next.next.next = new ListNode(1);

        assertTrue(TestUnit.compListNodeVal(AlgoUtils.reverseList_2(l1), l2));
    }

    @Test
    public void test12() {
        int target = 6;
        int[] nums = new int[]{1, 3, 5, 6};
        assertEquals(AlgoUtils.searchInsert(nums, target), 3);

        target = 0;
        assertEquals(AlgoUtils.searchInsert(nums, target), 0 );
    }

}
