import java.util.*;

public class DFS {
    /**
     * @Author: PPX
     * @Description: 丑数II
     * @Date: 2019-08-19
     * @Param n:
     * @return: int
     **/
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int cnt2 = 0, cnt3 = 0, cnt5 = 0;
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.min(dp[cnt2] * 2, Math.min(dp[cnt3] * 3, dp[cnt5] * 5));
            if (dp[i] == dp[cnt2] * 2)
                cnt2++;
            if (dp[i] == dp[cnt3] * 3)
                cnt3++;
            if (dp[i] == dp[cnt5] * 5)
                cnt5++;
        }
        return dp[n - 1];
    }

    /**
     * @Author: PPX
     * @Description: 基本计算器
     * @Date: 2019-08-19
     * @Param s:
     * @return: int
     **/
    public int calculate(String s) {
        int curRes = 0;
        int number = 0;
        boolean add = true;
        Stack<Integer> stack = new Stack<>();
        Stack<Boolean> operate = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(curRes);
                operate.push(add);
                add = true;
                curRes = 0;
            } else if (c == ')') {
                if (operate.pop())
                    curRes = stack.pop() + curRes;
                else
                    curRes = stack.pop() - curRes;
            } else if (c == '+') {
                add = true;
            } else if (c == '-') {
                add = false;
            } else if (c == ' ') {
                continue;
            } else {
                number = number * 10 + c - '0';
                if (i < s.length() - 1 && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9'))
                    continue;
                else {
                    if (add)
                        curRes += number;
                    else
                        curRes -= number;
                    number = 0;
                }
            }
        }
        return curRes;
    }

    /**
     * @Author: PPX
     * @Description: 拆除括号
     * @Date: 2019-08-13
     * @Param s:
     * @return: java.lang.String
     **/
    public String stringRevert(String s) {
        //res既担当输出结果，又作为截断时的临时收集字符串
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<String> StackString = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                StackString.add(res.toString());
                res = new StringBuilder();
            } else if (c == ')') {
                //不做操作，因为‘）’后必跟数字
                continue;
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + c - '0';
                //此处为了得到需要扩展的字母完整倍数
                if ((i != s.length() - 1 && (s.charAt(i + 1) < '0' || s.charAt(i + 1) > '9')) || i == s.length() - 1) {
                    StringBuilder tmp = new StringBuilder();
                    //进行扩展
                    for (int j = 0; j < multi; ++j) tmp.append(res);
                    res = new StringBuilder(StackString.pop() + tmp);
                    multi = 0;
                }
            } else {
                //当存在A2B这种情况下，需要存储之前的字符串到栈，并将res转换为A
                if (i != s.length() - 1 && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) {
                    StackString.add(res.toString());
                    res = new StringBuilder(String.valueOf(s.charAt(i)));
                } else
                    res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    /**
     * @Author: PPX
     * @Description: 字符串解码
     * @Date: 2019-08-13
     * @Param s:
     * @return: java.lang.String
     **/
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> StackMulti = new Stack<>();
        Stack<String> StackString = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                StackMulti.add(multi);
                StackString.add(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int multiCnt = StackMulti.pop();
                for (int i = 0; i < multiCnt; ++i) tmp.append(res);
                res = new StringBuilder(StackString.pop() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + c - '0';
            } else res.append(c);
        }
        return res.toString();
    }

    /**
     * @Author: PPX
     * @Description: 求根到叶子节点数字之和
     * @Date: 2019-08-13
     * @Param root:
     * @return: int
     **/
    private List<Integer> sumNumbersList = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        sumNumbersBFS(root, 0);
        int ret = 0;
        for (int num : sumNumbersList)
            ret += num;
        return ret;
    }

    private void sumNumbersBFS(TreeNode root, int curNum) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            sumNumbersList.add(10 * curNum + root.val);
            return;
        }
        sumNumbersBFS(root.left, 10 * curNum + root.val);
        sumNumbersBFS(root.right, 10 * curNum + root.val);
    }

    /**
     * @Author: PPX
     * @Description: 打家劫舍III
     * @Date: 2019-08-12
     * @Param root:
     * @return: int
     **/

    public int rob(TreeNode node) {
        if (node == null)
            return 0;
        int include = node.val;
        int exclude = rob(node.left) + rob(node.right);
        if (node.left != null) {
            include += rob(node.left.left);
            include += rob(node.left.right);
        }
        if (node.right != null) {
            include += rob(node.right.left);
            include += rob(node.right.right);
        }
        return Math.max(include, exclude);
    }

    private TreeNode head = null;
    private TreeNode pre = null;

    public TreeNode revert2DoubleList(TreeNode root) {
        inorderTreeNode(root);
        return head;
    }

    private void inorderTreeNode(TreeNode root) {
        if (root == null)
            return;
        inorderTreeNode(root.left);
        root.left = pre;
        if (pre != null)
            pre.right = root;
        pre = root;
        if (head == null)
            head = root;
        inorderTreeNode(root.right);
    }

    /**
     * @Author: PPX
     * @Description: 从中序与后序遍历序列构造二叉树
     * @Date: 2019-08-11
     * @Param inorder:
     * @Param postorder:
     * @return: TreeNode
     **/
    Map<Integer, Integer> inOrderPostMap = new HashMap<>();

    public TreeNode buildTreeByInorderPost(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;
        for (int i = 0; i < inorder.length; ++i)
            inOrderPostMap.put(inorder[i], i);
        return buildTreeByInPost(postorder, 0, inorder.length - 1, 0);

    }

    private TreeNode buildTreeByInPost(int[] postorder, int postL, int postR, int inL) {
        if (postL > postR)
            return null;
        TreeNode root = new TreeNode(postorder[postR]);
        int cutIndex = inOrderPostMap.get(postorder[postR]) - inL;
        root.left = buildTreeByInPost(postorder, postL, postL + cutIndex - 1, inL);
        root.right = buildTreeByInPost(postorder, postL + cutIndex, postR - 1, inL + cutIndex + 1);
        return root;
    }

    /**
     * @Author: PPX
     * @Description: 从前序与中序遍历序列构造二叉树
     * @Date: 2019-08-11
     * @Param preorder:
     * @Param inorder:
     * @return: TreeNode
     **/
    Map<Integer, Integer> inOrderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        for (int i = 0; i < inorder.length; ++i)
            inOrderMap.put(inorder[i], i);
        return buildTreeByPreIn(preorder, 0, inorder.length - 1, 0);
    }

    private TreeNode buildTreeByPreIn(int[] preorder, int preL, int preR, int inL) {
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int cutIndex = inOrderMap.get(preorder[preL]) - inL;
        root.left = buildTreeByPreIn(preorder, preL + 1, preL + cutIndex, inL);
        root.right = buildTreeByPreIn(preorder, preL + cutIndex + 1, preR, inL + cutIndex + 1);
        return root;
    }
}
