import java.util.*;

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int k) {
        this.val = k;
    }
}

class DoubleList {
    private DoubleListNode head, tail; // 头尾虚节点
    private int size; // 链表元素数

    public DoubleList() {
        head = new DoubleListNode(0, 0);
        tail = new DoubleListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表头部添加节点 x
    public void addFirst(DoubleListNode x) {
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    public void remove(DoubleListNode x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    // 删除链表中最后一个节点，并返回该节点
    public DoubleListNode removeLast() {
        if (tail.prev == head)
            return null;
        DoubleListNode last = tail.prev;
        remove(last);
        return last;
    }

    // 返回链表长度
    public int size() {
        return size;
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
