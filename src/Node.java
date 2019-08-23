import java.util.*;

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int k) {
        this.val = k;
    }
}

class DoubleListNode {
    public int key, val;
    public DoubleListNode next, prev;

    public DoubleListNode(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

class MultiNode {
    public int val;
    public List<MultiNode> children;

    public MultiNode() {
    }

    public MultiNode(int _val, List<MultiNode> _children) {
        val = _val;
        children = _children;
    }
}

class BuildList {
    private ListNode head = null;

    public ListNode buildList(int[] dights) {
        int len = dights.length;
        if (len == 0)
            return null;
        ListNode node = new ListNode(dights[0]);
        head = node;
        for (int i = 1; i < len; ++i) {
            ListNode next = new ListNode(dights[i]);
            node.next = next;
            node = next;
        }
        return head;
    }
}
