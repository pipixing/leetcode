import java.util.*;
import java.util.Stack;

class Solution {
    /**
     * @Author: PPX
     * @Description: 找到字符串中所有字母异位词
     * @Date: 2019-07-31
     * @Param s:
     * @Param p:
     * @return: java.util.List<java.lang.Integer>
     **/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int i = 0;
        while (i <= s.length() - p.length()) {
            if (isAnagram(s.substring(i, i + p.length()), p))
                ret.add(i);
            ++i;
        }
        return ret;
    }

    /**
     * @Author: PPX
     * @Description: 字符串中的单词数
     * @Date: 2019-07-31
     * @Param s:
     * @return: int
     **/
    public int countSegments(String s) {
        s = s.trim();
        if (s.length() == 0)
            return 0;
        String[] strs = s.split(" +");
        return strs.length;
    }

    /**
     * @Author: PPX
     * @Description:
     * @Date: 2019-07-31
     * @Param root:
     * @return: java.util.List<java.util.List                                                                                                                               <                                                                                                                               java.lang.Integer>>
     **/
    public List<List<Integer>> levelOrder(MultiNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            List<Integer> list = new ArrayList<>();
            while (cnt-- > 0) {
                MultiNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    for (MultiNode n : node.children) {
                        queue.offer(n);
                    }
                }
            }
            if (list.size() > 0)
                res.add(list);
        }
        return res;
    }

    /**
     * @Author: PPX
     * @Description: 字符串相加
     * @Date: 2019-07-30
     * @Param num1:
     * @Param num2:
     * @return: java.lang.String
     **/
    public String addStrings(String num1, String num2) {
        if (num1.length() == 0)
            return num2;
        if (num2.length() == 0)
            return num1;
        String res = "";
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1, sum, n1, n2;
        while (i >= 0 || j >= 0) {
            n1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            n2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            sum = n1 + n2 + carry;
            carry = sum / 10;
            res = String.valueOf(sum % 10) + res;
        }
        if (carry == 1)
            return 1 + res;
        return res;
    }

    /**
     * @Author: PPX
     * @Description: 第三大的数
     * @Date: 2019-07-30
     * @Param nums:
     * @return: int
     **/
    public int thirdMax(int[] nums) {
        int n = nums.length;
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] < max1 && nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (nums[i] < max2 && nums[i] > max3)
                max3 = nums[i];
        }
        if (max3 != Long.MIN_VALUE)
            return (int) max3;
        else
            return (int) max1;
    }

    /**
     * @Author: PPX
     * @Description: Fizz Buzz
     * @Date: 2019-07-30
     * @Param n:
     * @return: java.util.List<java.lang.String>
     **/
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            String s = "";
            if (i % 3 == 0) {
                s += "Fizz";
                if (i % 5 == 0)
                    s += "Buzz";
            } else if (i % 5 == 0) {
                s += "Buzz";
            } else
                s += i;
            res.add(s);
        }
        return res;
    }

    /**
     * @Author: PPX
     * @Description: 最长回文串
     * @Date: 2019-07-30
     * @Param s:
     * @return: int
     **/
    public int longestPalindrome(String s) {
        int[] chars = new int[64];
        int i = 0;
        while (i < s.length()) {
            chars[s.charAt(i++) - 'A']++;
        }
        int res = 0, odd = 0;
        for (int l : chars) {
            res += l / 2;
            if (l % 2 == 1)
                odd = 1;
        }
        return res * 2 + odd;
    }

    /**
     * @Author: PPX
     * @Description: 数字转换为十六进制
     * @Date: 2019-07-30
     * @Param num:
     * @return: java.lang.String
     **/
    public String toHex(int num) {
        if (num == 0)
            return "0";
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 15]);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }

    /**
     * @Author: PPX
     * @Description: 左叶子之和, 当root为单个元素时不会返回root.val
     * @Date: 2019-07-30
     * @Param root:
     * @return: int
     **/
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;
        return sumOfLeftLeavesCore(root);
    }

    /**
     * @Author: PPX
     * @Description: 左叶子之和, 当root为单个元素时会返回root.val
     * @Date: 2019-07-30
     * @Param root:
     * @return: int
     **/
    private int sumOfLeftLeavesCore(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;
        int cnt = sumOfLeftLeavesCore(root.left);
        if (root.right != null)
            //两个函数互相调用
            cnt += sumOfLeftLeaves(root.right);
        return cnt;
    }


    /**
     * @Author: PPX
     * @Description: 二进制手表
     * @Date: 2019-07-30
     * @Param num:
     * @return: java.util.List<java.lang.String>
     **/
    public List<String> readBinaryWatch(int num) {
        List<String> res = new LinkedList<>();
        //直接遍历  0:00 -> 12:00   每个时间有多少1
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (count1(i) + count1(j) == num)
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
            }
        }
        return res;
    }

    int count1(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    List<String> ret = new ArrayList<>();

    /**
     * @Author: PPX
     * @Description:
     * @Date: 2019-07-30
     * @Param s:
     * @return: java.util.List<java.lang.String>
     **/
    public List<String> restoreIpAddresses(String s) {
        backTrackinig(s, 0, 4, "");
        return ret;
    }

    private void backTrackinig(String s, int i, int flag, String temp) {
        if (i == s.length() && flag == 0) {
            ret.add(temp.substring(0, temp.length() - 1));
            return;
        }
        if (flag > 0) return;
        for (int j = i; j < i + 3; ++j) {
            if (j < s.length()) {
                if (i == j && s.charAt(j) == '0') {
                    backTrackinig(s, j + 1, flag - 1, temp + s.charAt(j) + '.');
                    break;
                }
                if (Integer.parseInt(s.substring(i, j + 1)) <= 255) {
                    backTrackinig(s, j + 1, flag, temp + s.substring(i, j + 1) + '.');
                }
            }
        }
    }

    /**
     * @Author: PPX
     * @Description: 找不同
     * @Date: 2019-07-30
     * @Param s:
     * @Param t:
     * @return: char
     **/
    public char findTheDifference(String s, String t) {
        if (s.length() == 0)
            return t.charAt(0);
        int[] c = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            c[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            if (c[t.charAt(i) - 'a'] == 0)
                return t.charAt(i);
        }
        return ' ';
    }

    //字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        int[] c = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            c[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (c[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    //赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int lenr = ransomNote.length();
        int lenm = magazine.length();
        if (lenr == 0)
            return true;
        if (lenm == 0)
            return false;
        int[] c = new int[26];
        for (int i = 0; i < lenr; ++i) {
            c[ransomNote.charAt(i) - 'a']++;
        }
        for (int j = 0; j < lenm; ++j) {
            if (c[magazine.charAt(j) - 'a'] != 0)
                c[magazine.charAt(j) - 'a']--;
        }
        for (int zero : c) {
            if (zero != 0)
                return false;
        }
        return true;
    }

    //猜数字大小
    public int guessNumber(int n) {
        int i = 1, j = n;
        while (i < j) {
            int m = i + (j - i) / 2;
            int guess = guess(m);
            if (guess == -1)
                i = m + 1;
            if (guess == 1)
                j = m - 1;
            if (guess == 0)
                j = m;
        }
        return j;
    }

    private int guess(int n) {
        if (n == 6)
            return 0;
        else if (n > 6)
            return 1;
        return -1;
    }

    //不使用+-做加法
    public int getSum(int a, int b) {
        int and = a & b;
        int xor = a ^ b;
        if (and != 0)
            and = and << 1;
        while ((and & xor) != 0) {
            int newAnd = and & xor;
            xor ^= and;
            and = newAnd << 1;
        }
        return and ^ xor;
    }

    //两个数组的交集2
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return new int[0];
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num1 : nums1) {
            if (map1.containsKey(num1))
                map1.put(num1, map1.get(num1) + 1);
            else
                map1.put(num1, 1);
        }
        for (int num2 : nums2) {
            if (map2.containsKey(num2))
                map2.put(num2, map2.get(num2) + 1);
            else
                map2.put(num2, 1);
        }
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                int cnt = Math.min(entry.getValue(), map2.get(entry.getKey()));
                for (int i = 0; i < cnt; ++i) {
                    list.add(entry.getKey());
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    //两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return new int[0];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int num1 : nums1)
            set1.add(num1);
        for (int num2 : nums2)
            set2.add(num2);
        for (int num : set1) {
            if (set2.contains(num))
                list.add(num);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    //4的幂
    public boolean isPowerOfFour(int num) {
        if (num == 1)
            return true;
        if (num == 0 || (num & (num - 1)) != 0)
            return false;
        int base = 1;
        while (base < num) {
            base *= 4;
        }
        return base == num;
    }

    //3的幂
    public boolean isPowerOfThree(int n) {
        if (n == 0)
            return false;
        if (n % 3 != 0)
            return false;
        int base = 1;
        while (base < n) {
            base *= 3;
        }
        return base == n;
    }

    //Nim游戏
    public boolean canWinNim(int n) {
        return n % 4 == 0;
    }

    //单词规律
    public boolean wordPattern(String pattern, String str) {
        char[] cs = pattern.toCharArray();
        String[] ss = str.split(" ");
        if (cs.length != ss.length)
            return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < cs.length; ++i) {
            if (!map.containsKey(cs[i])) {
                if (map.containsValue(ss[i]))
                    return false;
                map.put(cs[i], ss[i]);
            } else {
                if (!map.get(cs[i]).equals(ss[i]))
                    return false;
            }
        }
        return true;
    }

    //移动0
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (Integer num : nums) {
            if (num != 0) {
                nums[i++] = num;
            }
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }

    //缺失数字
    public int missingNumber(int[] nums) {
        int len = nums.length, xor = 0;
        for (int i = 0; i < len; ++i) {
            xor ^= nums[i];
            xor ^= i;
        }
        return xor ^ (len);
    }

    //丑数
    public boolean isUgly(int num) {
        if (num == Integer.MAX_VALUE)
            return false;
        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        int t2 = 0, t3 = 0, t5 = 0;
        int curNum = 1;
        while (curNum < num) {
            curNum = min(dp.get(t2) * 2, dp.get(t3) * 3, dp.get(t5) * 5);
            dp.add(curNum);
            if (curNum == dp.get(t2) * 2) t2++;
            if (curNum == dp.get(t3) * 3) t3++;
            if (curNum == dp.get(t5) * 5) t5++;
        }
        if (curNum == num)
            return true;
        return false;
    }

    private int min(int a, int b, int c) {
        int res = a < b ? a : b;
        return c < res ? c : res;
    }

    //各位相加
    public int addDigits(int num) {
        if (num < 10)
            return num;
        int cnt = 0;
        while (num != 0) {
            cnt += num % 10;
            num = num / 10;
        }
        return addDigits(cnt);
    }

    //螺旋矩阵
//    public List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> res = new ArrayList<>();
//        if (matrix.length == 0)
//            return res;
//        int row = matrix.length, col = matrix[0].length, i = 0, j = 0;
//        while (i < row && j < col) {
//            while (j < col)
//                res.add(matrix[i][j++]);
//            while (i < row)
//                res.add(matrix[++i][col]);
//            if (i < row)
//        }
//
//    }

    //荷兰国旗
    public void sortColors(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2)
                swap(nums, --two, one);
            else
                ++one;
        }
    }

    //快速选择
    public int quickSelect(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k)
                break;
            else if (j < k)
                l = j + 1;
            else
                h = j - 1;
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h) ;
            while (nums[--j] > nums[l] && l < j) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length || nums.length == 0)
            return 0;
        //大顶堆，默认情况下是小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > nums.length - k + 1)
                minHeap.poll();
        }
        return minHeap.isEmpty() ? 0 : minHeap.peek();
    }

    //通过删除字母匹配到字典里最长单词
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length(), l2 = target.length();
            if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0))
                continue;
            if (isSubString(s, target))
                longestWord = target;
        }
        return longestWord;
    }

    private boolean isSubString(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }

    //反转字符串中的元音字母
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            while (i < j && !vowels.contains(chars[i])) {
                i++;
            }
            while (i < j && !vowels.contains(chars[j])) {
                j--;
            }
            if (i < j) {
                swap(chars, i, j);
            }
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    //平方数之和
    public boolean judgeSquareSum(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = (int) (Math.pow(i, 2) + Math.pow(j, 2));
            if (sum == c)
                return true;
            if (sum > c)
                j--;
            else
                i++;
        }
        return false;
    }

    //二叉树所有路径
//    private List<String> ret = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null)
            return ret;
        binaryTreePathsCore(root, new ArrayList<>());
        return ret;
    }

    public void binaryTreePathsCore(TreeNode root, List<Integer> path) {
        if (root != null) {
            path.add(root.val);
            if (root.left == null && root.right == null) {
                int cnt = path.size();
                String s = null;
                for (int i = 0; i < cnt; ++i) {
                    if (i == 0)
                        s = Integer.toString(path.get(0));
                    else
                        s += Integer.toString(path.get(i));
                    if (i != cnt - 1) {
                        s += "->";
                    }
                }
                ret.add(s);
                path.remove(path.size() - 1);
                return;
            } else {
                binaryTreePathsCore(root.left, path);
                binaryTreePathsCore(root.right, path);
                path.remove(root.val);
            }
        }
    }

    //有效字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            chars[s.charAt(i) - 'a']++;
            chars[t.charAt(i) - 'a']--;
        }
        for (Integer c : chars) {
            if (c > 0)
                return false;
        }
        return true;
    }

    //删除链表中的节点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //二叉搜索树找公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else
            return root;
    }

    //是否为回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode reverseHead = reverseList(head);
        while (head != null) {
            if (head.val != reverseHead.val)
                return false;
            head = head.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }

    //反转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //存在重复元素2
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i; j < len && j <= i + k; ++j) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    //存在重复元素
    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    //反转链表
    private ListNode pre = null;

    public ListNode reverseList(ListNode head) {
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //计数质数

    /**
     * 如果不好找质数，那么找非质数
     */
    public int countPrimes(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = 1;
        }
        for (int i = 2; i < n; i++) {
            //如果当前数为质数
            if (nums[i] == 1) {
                //将对应数的倍数变为0
                for (int j = 2; i * j < n; j++) {
                    nums[i * j] = 0;
                }
            }
        }
        //遍历数组，统计值为1的元素个数
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] == 1)
                res++;
        }
        return res;
    }

    //移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        if (head.val == val) {
            return removeElements(head.next, val);
        } else
            head.next = removeElements(head.next, val);
        return head;
    }

    //阶乘后的0的个数
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt = n / 5;
            n /= 5;
        }
        return cnt;
    }

    //Excel表序列号
    public int titleToNumber(String s) {
        if (s.isEmpty())
            return 0;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt *= 26;
            cnt += s.charAt(i) - 'A' + 1;
        }
        return cnt;
    }

    //众数
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int majority = nums[0];
        for (int num : nums) {
            cnt = num == majority ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = num;
                cnt = 1;
            }
        }
        return majority;
    }

    //Excel表格位置
    public String convertToTitle(int n) {
        int reminder;
        StringBuilder ret = new StringBuilder();
        while (n > 0) {
            reminder = n % 26;
            n /= 26;
            if (reminder != 0)
                ret.append((char) ('A' + reminder - 1));
            else {
                ret.append('Z');
                n -= 1;
            }
        }
        return ret.reverse().toString();
    }

    //两数之和
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        int l = 0, h = numbers.length - 1;
        while (l < h) {
            int sum = numbers[l] + numbers[h];
            if (sum < target)
                l++;
            else if (sum > target)
                h--;
            else {
                ret[0] = l + 1;
                ret[1] = h + 1;
                return ret;
            }
        }
        return ret;
//        int[] res = new int[2];
//        for (int i = 0; i < numbers.length; ++i) {
//            if (target - numbers[i] >= numbers[i]) {
//                int l = i+1, h = numbers.length - 1;
//                while (l <= h) {
//                    int m = (l + h) / 2;
//                    if (numbers[m] == target - numbers[i]) {
//                        res[0] = i;
//                        res[1] = m;
//                        return res;
//                    } else if (numbers[m] > target - numbers[i])
//                        h = m - 1;
//                    else
//                        l = m + 1;
//                }
//            }
//        }
//        return res;
    }

    //相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha = headA, hb = headB;
        while (ha != hb) {
            //是否会存在ha长度等于hb，但是这两个链表没有共同节点
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
    }

    //链表有没有环
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
    //只出现一次的数字
//    public int singleNumber(int[] nums) {
//        if (nums.length == 0)
//            return 0;
//        int ret = 0;
//        for (int num : nums) {
//            ret ^= num;
//        }
//        return ret;
//    }

    //是否回文
    public boolean isPalindrome(String s) {
        if (s == null)
            return true;
        int i = 0;
        while (i++ < s.length()) {
            if ((s.charAt(i) - 'A' >= 0 && s.charAt(i) - 'z' <= 0) || (s.charAt(i) - '0' >= 0 && s.charAt(i) - '9' <= 0) || s.charAt(i) == ' ')
                continue;
            else
                return true;
        }
        return false;
    }

    //可以完成多次交易，没有次数上限
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int maxProfit = 0, profit;
        for (int i = 0; i < len - 1; ++i) {
            profit = prices[i + 1] - prices[i];
            maxProfit += profit > 0 ? profit : 0;
        }
        return maxProfit;
    }

    /*
    买卖股票最大利润,算法复杂度O(n2)
    public int maxProfit(int[] prices) {
        int length = prices.length, maxProfit = Integer.MIN_VALUE;
        if (length < 2) return 0;
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                maxProfit=Math.max(maxProfit,prices[j]-prices[i]);
            }
        }
        return maxProfit;
    }
     */
    //复杂度降一个数量级
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int maxProfit = 0, minPrice = prices[0];
        for (Integer price : prices) {
            if (price < minPrice)
                minPrice = price;
            if (price - minPrice > maxProfit)
                maxProfit = price - minPrice;
        }
        return maxProfit;
    }


    //杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= numRows; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                if (j == 0 || j == i - 1) {
                    list.add(1);
                } else {
                    list.add(lists.get(i - 2).get(j - 1) + lists.get(i - 2).get(j));
                }
            }
            lists.add(list);
        }
        return lists;
    }

    //树上路径总和
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        int need = sum - root.val;
        if (need == 0 && (root.left == null && root.right == null))
            return true;
        return hasPathSum(root.left, need) || hasPathSum(root.right, need);
    }

    //树的最低深度
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int ret;
        if (root.left != null && root.right != null)
            ret = 1 + Math.min(minDepth(root.left), minDepth(root.right));
        else
            ret = 1 + Math.max(minDepth(root.left), minDepth(root.right));
        return ret;
    }

    //判断二叉树是否平衡
    private boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        getHeight(root);
        return flag;
    }

    private int getHeight(TreeNode root) {
        if (root == null || !flag)
            return 0;
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (Math.abs(l - r) > 1) {
            flag = false;
        }
        return Math.max(l, r) + 1;
    }

    //排序列表转二叉平衡树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return sortedArrayToBSTCore(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTCore(int[] nums, int l, int h) {
        if (l > h)
            return null;
        int m = (l + h) / 2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = sortedArrayToBSTCore(nums, l, m - 1);
        node.right = sortedArrayToBSTCore(nums, m + 1, h);
        return node;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //层次遍历
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null)
            return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0) {
            int cnt = queue.size();
            List<Integer> list = new ArrayList<>();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            if (list.size() != 0)
                //直接在首部添加，就不用反转数组了
                ret.add(0, list);
        }
        return ret;
    }

    //二叉树最大深度
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int ret = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        return ret;
    }

    public boolean isSymmetric(TreeNode root) {
        TreeNode copy = root;
        return isSymmetricCore(root, copy);
    }

    private boolean isSymmetricCore(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return isSymmetricCore(p.left, q.right) && isSameTree(p.right, q.left);
        else
            return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        else
            return false;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0)
            return;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < m; ++i) {
            minHeap.add(nums1[i]);
        }
        for (int j = 0; j < n; ++j) {
            minHeap.add(nums2[j]);
        }
        for (int k = 0; k < m + n; ++k) {
            nums1[k] = minHeap.poll();
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            while (next != null && node.val == next.val) {
                next = next.next;
            }
            node.next = next;
            node = next;
        }
        return head;
    }

    public int climbStairs(int n) {
        //斐波那契会超时
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return (int) dp[n];
    }

    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        //精度也会产生严重影响
        double m, n = x;
        while (true) {
            m = x / n;
            n = (m + n) / 2;
            if (n - m < 1)
                break;
        }
        //这得记住吗？你说
        return (int) n;
    }

    public String addBinary(String a, String b) {
        int m = a.length(), n = b.length();
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
        int i = m - 1, j = n - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            char temp1 = i >= 0 ? a.charAt(i) : '0';
            char temp2 = j >= 0 ? b.charAt(j) : '0';
            int ret = (temp1 - '0') + (temp2 - '0') + carry;
            if (ret >= 2) {
                stack.push(ret % 2);
                carry = 1;
            } else {
                stack.push(ret);
                carry = 0;
            }
            i--;
            j--;
        }
        if (carry == 1)
            stack.push(1);
        int s = stack.size();
        for (int k = 0; k < s; ++k) {
            sb.append(stack.peek());
            stack.pop();
        }
        return sb.toString();
    }

    public void reverseArray(int[] digits) {
        if (digits.length < 2)
            return;
        int i = 0, j = digits.length - 1;
        while (i < j) {
            int temp = digits[i];
            digits[i] = digits[j];
            digits[j] = temp;
            i++;
            j--;
        }
        return;
    }

    public int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else
                digits[i] = 0;
        }
        int[] res = new int[length + 1];
        res[0] = 1;
        return res;
    }

    public void inc(int i) {
        i++;
    }
}
