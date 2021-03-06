package main.tree;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        a.next = b;
        ListNode c = insertionSortList(a);
        ListNode head = c;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = dummyHead;
        ListNode sortedLast = dummyHead;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            ListNode sortedHead = dummyHead.next;
            if (sortedHead == dummyHead) {
                dummyHead.next = current;
                sortedLast = current;
                current.next = null;
                current = next;
                continue;
            }
            if (current.val >= sortedLast.val) {
                current.next = null;
                sortedLast.next = current;
                sortedLast = current;
            } else if (current.val < sortedHead.val) {
                dummyHead.next = current;
                current.next = sortedHead;
            } else {
                ListNode sortedPrevious = dummyHead;
                ListNode sortedCurrent = sortedHead;
                while (sortedCurrent != null) {
                    if (current.val < sortedCurrent.val) {
                        sortedPrevious.next = current;
                        current.next = sortedCurrent;
                        break;
                    } else {
                        sortedPrevious = sortedCurrent;
                        sortedCurrent = sortedCurrent.next;
                    }
                }
            }
            current = next;
        }
        return dummyHead.next;
    }

    public static void printIndex(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char firstChar = chars2[0];
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == firstChar) {
                boolean flag = true;
                for (int j = 1; j < chars2.length; j++) {
                    if (chars1[i + j] != chars2[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Index:" + i);
                }
            }
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        } else {
            int wordNum = words.length;
            int wordLength = words[0].length();
            int wordTotalLength = wordNum * wordLength;
            int stringLength = s.length();
            char[] chars = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (i + wordTotalLength - 1 > stringLength - 1) {
                    return result;
                }
                String subStr = s.substring(i, i + wordTotalLength);
                String[] subStrs = subStr.split("", wordLength);

            }
        }
        return null;
    }

    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }
        Set<Character> set = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        int a = 0;
        for (Character ch : s.toCharArray()) {
            if (!set.contains(ch)) {
                queue.add(ch);
                set.add(ch);
                result = Math.max(set.size(), result);
                a++;
            } else {
                result = Math.max(set.size(), result);
                boolean continueRemove = true;
                while (continueRemove) {
                    Character character = queue.element();
                    Character character1 = queue.remove();
                    set.remove(character);
                    if (ch.equals(character)) {
                        continueRemove = false;
                    }
                }
                queue.add(ch);
                set.add(ch);
            }
        }
        return result;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[] existsArray = new boolean[128];
        int maxResult = 0;
        char[] chars = s.toCharArray();
        int flag = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (existsArray[ch]) {
                while (true) {
                    char ch1 = chars[flag];
                    flag++;
                    if (ch1 == ch) {
                        break;
                    } else {
                        existsArray[ch1] = false;
                    }
                }
            } else {
                int result = i + 1 - flag;
                maxResult = maxResult > result ? maxResult : result;
                existsArray[ch] = true;
            }
        }
        return maxResult;
    }

    public static String reverseWords(String s) {
        char[] chars = new char[s.length()];
        int flag1 = -1;
        char[] chars1 = new char[s.length()];
        int flag2 = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                for (int i = flag1; i >= 0; i--) {
                    chars1[flag2] = chars[i];
                    flag2++;
                }
                chars1[flag2] = ' ';
                flag2++;
                flag1 = -1;
            } else {
                flag1++;
                chars[flag1] = ch;
            }
        }
        for (int i = flag1; i >= 0; i--) {
            chars1[flag2] = chars[i];
            flag2++;
        }
        return new String(chars1);
    }

    public static void test() {
        int[] ints = new int[10];
        System.out.println(ints[0]);
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int size = rooms.size();
        boolean[] gotKeys = new boolean[size];
        int[] visits = new int[size];
        int left = 0;
        int right = 0;
        while (left <= right) {
            List<Integer> keys = rooms.get(visits[left]);
            gotKeys[visits[left]] = true;
            for (Integer key : keys) {
                if (!gotKeys[key]) {
                    gotKeys[key] = true;
                    visits[++right] = key;
                }
            }
            left++;
        }
        return left == size;
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        int[] ints = new int[n];
        int columnNum = 0;
        getResolution(ints, columnNum, n, results);
        return results;
    }

    public static void getResolution(int[] ints, int columnNum, int n, List<List<String>> results) {
        int[] exits = getExits(ints, columnNum);
        if (columnNum == n - 1) {
            if (exits != null) {
                for (int exit : exits) {
                    ints[columnNum] = exit;
                    List<String> result = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        char[] chars = new char[n];
                        for (int c = 0; c < n; c++) {
                            if (c != ints[i]) {
                                chars[c] = ',';
                            } else {
                                chars[c] = 'Q';
                            }
                        }
                        result.add(new String(chars));
                    }
                    results.add(result);
                }
            }
        } else {
            if (exits != null) {
                for (int exit : exits) {
                    ints[columnNum] = exit;
                    getResolution(ints, columnNum + 1, n, results);
                }

            }
        }
    }

    public static int[] getExits(int[] ints, int columnNum) {
        int length = ints.length;
        if (columnNum == 0) {
            int[] exits = new int[length];
            for (int i = 0; i < length; i++) {
                exits[i] = i;
            }
            return exits;
        } else {
            boolean[] notExits = new boolean[length];
            for (int i = 0; i < columnNum; i++) {
                notExits[ints[i]] = true;
                int distance = columnNum - i;
                if (ints[i] - distance >= 0) {
                    notExits[ints[i] - distance] = true;
                }
                if (ints[i] + distance < length) {
                    notExits[ints[i] + distance] = true;
                }
            }
            int exitsNum = 0;
            for (boolean notExit : notExits) {
                if (!notExit) {
                    exitsNum++;
                }
            }
            if (exitsNum == 0) {
                return null;
            }
            int[] exits = new int[exitsNum];
            int flag = 0;
            for (int i = 0; i < notExits.length; i++) {
                if (!notExits[i]) {
                    exits[flag] = i;
                    flag++;
                }
            }
            return exits;
        }
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> stringList = new ArrayList<>();
        if (root == null) {
            return stringList;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        getChild(root, treeNodes, stringList);
        return stringList;
    }

    public static void getChild(TreeNode root, List<TreeNode> treeNodes, List<String> stringList) {
        if (root.left == null && root.right == null) {
            treeNodes.add(root);
            StringBuilder sb = new StringBuilder();
            for (TreeNode treeNode : treeNodes) {
                sb.append(treeNode.val).append("->");
            }
            sb.delete(sb.length() - 2, sb.length());
            stringList.add(sb.toString());
            treeNodes.remove(root);
            return;
        }
        if (root.left != null) {
            treeNodes.add(root);
            getChild(root.left, treeNodes, stringList);
            treeNodes.remove(root);
        }
        if (root.right != null) {
            treeNodes.add(root);
            getChild(root.right, treeNodes, stringList);
            treeNodes.remove(root);
        }
    }

}

