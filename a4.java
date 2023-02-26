import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
    private int capacity;
    private int size;
    private Map<Integer, Integer> cache;
    private Map<Integer, Integer> frequency;
    private PriorityQueue<Integer> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.frequency = new HashMap<>();
        this.queue = new PriorityQueue<>((a, b) -> frequency.get(a) - frequency.get(b));
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // Update the frequency of the key-value pair and update the priority queue
        int freq = frequency.get(key);
        frequency.put(key, freq + 1);
        queue.remove(key);
        queue.offer(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }
        // Evict the least frequently used item from the cache
        if (size == capacity) {
            int leastFreqKey = queue.poll();
            cache.remove(leastFreqKey);
            frequency.remove(leastFreqKey);
            size--;
        }
        // Add the new item to the cache
        cache.put(key, value);
        frequency.put(key, 1);
        queue.offer(key);
        size++;
    }
}
