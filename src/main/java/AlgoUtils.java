import java.util.*;

public class AlgoUtils {
    /**
     * e.g. if x = 100 -> return 1+2+...100
     *
     * @param x a specific number
     * @return accumulated value
     */
    static int sumOneToN(int x) {
        boolean b = x > 0 && (x += sumOneToN(x - 1)) > 0;
        return x;
    }

    /**
     * e.g. l1 2->5->8, l2 3->0->2,  return 5->5->0->1
     *
     * @param l1 ListNode l1
     * @param l2 ListNode l2
     * @return combined list node
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // to check the sum of each node, if bigger than 0, will move to next
        int carry = 0;
        ListNode dummyNode = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyNode;
        while (p != null || q != null) {
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
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        // consider about edge case
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyNode.next;
    }

    /**
     * target is two number of an array, return the index, assume no duplicate item
     * every input has one result
     * https://leetcode-cn.com/problems/two-sum/
     */
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff) && map.get(diff) != i) {
                return new int[]{i, map.get(diff)};
            }
        }
        throw new IllegalArgumentException("illegal input");
    }


    static int longest(String s) {
        int r = -1, n = s.length(), ans = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0; l < n; l++) {
            if (l != 0) {
                set.remove(s.charAt(l - 1));
            }
            while (r + 1 < n && !set.contains(s.charAt(r + 1))) {
                set.add(s.charAt(r + 1));
                r++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    static String longestPalindrome_self(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            Character left = s.charAt(i);
            String toCheck = s.substring(i, s.lastIndexOf(Character.toString(left)) + 1);
            int lp = 0, rp = toCheck.length() - 1;
            while (lp < rp) {

                if (toCheck.charAt(lp + 1) == toCheck.charAt(rp - 1)) {
                    lp++;
                    rp--;
                } else {
                    toCheck = toCheck.substring(i, toCheck.lastIndexOf(Character.toString(left)) + 1);
                    rp = toCheck.length() - 1;
                }
            }

            if (toCheck.length() > ans.length()) {
                ans = toCheck;
            }
        }
        return ans;
    }

    static String longestPalindrome(String s) {
        char[] charArr = s.toCharArray();
//        max is dynamic
        int max = 1, begin = 0;
        for (int i = 0; i < charArr.length - 1; i++) {
            for (int j = i + 1; j < charArr.length; j++) {
                if (j - i + 1 > max && isPalindrome(charArr, i, j)) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    static boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end && arr[start] == arr[end]) {
            start++;
            end--;
        }
        if (start >= end) {
            return true;
        } else {
            return false;
        }
    }

    static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        char[] charArr = s.toCharArray();
//        currRow has two directions down and up, down up related to row
        int currRow = 0, maxRow = Math.min(s.length(), numRows);
        boolean godown = false;
        List<StringBuilder> rows = new ArrayList<>();
//      init rows data
        for (int i = 0; i < maxRow; i++) {
            rows.add(new StringBuilder());
        }

        for (char c : charArr) {
//            horizontal append character
            rows.get(currRow).append(c);
            if (currRow == 0 || currRow == maxRow - 1) {
                godown = !godown;
            }
            currRow += godown ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : rows) {
            ret.append(sb);
        }
        return ret.toString();
    }

    static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
//            if bigger enough continue add value
            pre = Math.max(pre + x, x);
            maxAns = Math.max(pre, maxAns);
        }
        return maxAns;
    }

    static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }

            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    static ListNode deleteDuplicates_self(ListNode head) {
        ListNode dumyNode = new ListNode(0);
        ListNode curNode = dumyNode;
        Set<Integer> set = new HashSet<>();
        while (head != null) {
            int cur = head.val;
            if (set.add(cur)) {
                curNode.next = new ListNode(cur);
                curNode = curNode.next;
            }
            head = head.next;
        }
        return dumyNode.next;
    }

    // sorted listNode
    static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        // @edu merge together
        /**
         * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         *
         * src − This is the source array.
         *
         * srcPos − This is the starting position in the source array.
         *
         * dest − This is the destination array.
         *
         * destPos − This is the starting position in the destination data.
         *
         * length − This is the number of array elements to be copied.
         */
        System.arraycopy(nums2, 0, nums1, m, n);
        // sort
        Arrays.sort(nums1);
    }

    static boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    static boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * Given a binary tree, find its maximum depth.
     * <p>
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * <p>
     * Note: A leaf is a node with no children.
     * <p>
     * Example:
     * <p>
     * Given binary tree [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param root
     * @return
     */
    static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    /**
     * longest prefix
     *
     * @param strs
     * @return
     */
    static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            // every string
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    /**
     * reverse a listNode 1->2->3->4->null  => 4->3->2->1->null
     *
     * @param head
     * @return
     */
    static ListNode reverseList_1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;

            // prev join follow by the curr's first node
            curr.next = prev;

            // dynamically change prev
            prev = curr;

            curr = nextTemp;
        }
        return prev;
    }

    static ListNode reverseList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList_2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * @param nums
     * @return
     */
    static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * like java function S.indexOf('sth')
     *
     * @param haystack
     * @param needle
     * @return
     */
    static int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();

        for (int start = 0; start < n - L + 1; ++start) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * binary search
     *
     * @param nums
     * @param target
     * @return
     */
    static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            // @edu >> 1 means / 2, << 1 means * 2
            int mid = ((right - left) / 2) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}

