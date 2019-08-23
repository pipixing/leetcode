import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class LRU {
    // key -> DoubleListNode(key, val)
    private HashMap<Integer, DoubleListNode> map;
    // DoubleListNode(k1, v1) <-> DoubleListNode(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;

    public LRU(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        DoubleListNode x = new DoubleListNode(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个数据
                DoubleListNode last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }
}

class LruImplLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    LruImplLinkedHashMap(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

class LRUCache {
    private int cap;
    //LinkedHashMap新添加元素都是先放在队尾的
    private LinkedHashMap<Integer, Integer> lrucache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (lrucache.keySet().contains(key)) {
            int value = lrucache.get(key);
            lrucache.remove(key);
            //保证在最后
            put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (lrucache.keySet().contains(key)) {
            lrucache.remove(key);
        } else if (lrucache.size() == cap) {
            Iterator<Map.Entry<Integer, Integer>> iterator = lrucache.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        lrucache.put(key, value);
    }
}