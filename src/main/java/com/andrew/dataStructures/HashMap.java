package com.andrew.dataStructures;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private final double LOAD_FACTOR = 0.75;
    private final double GROWTH_FACTOR = 1.5;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[4];
    private int capacity = buckets.length;
    private int size;

    @Override
    public V put(K key, V value) {
        V prevResult;
        ensureCapacity();
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        for (int i = 0; i < targetBucket.size(); i++) {
            if (targetBucket.get(i) != null && targetBucket.get(i).key.equals(key)) {
                prevResult = targetBucket.get(i).value;
                targetBucket.set(i, newEntry);
                return prevResult;
            }
        }
        targetBucket.add(newEntry);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        for (Entry<K, V> entry : targetBucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        for (Entry<K, V> entry : targetBucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        V prevResult;
        for (int i = 0; i < targetBucket.size(); i++) {
            if (targetBucket.get(i) != null && targetBucket.get(i).key.equals(key)) {
                prevResult = targetBucket.get(i).value;
                targetBucket.remove(i);
                size--;
                return prevResult;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    private void ensureCapacity() {
        if (capacity * LOAD_FACTOR <= size) {
            buckets = Arrays.copyOf(buckets, (int) (capacity * GROWTH_FACTOR));
            capacity = (int) (capacity * GROWTH_FACTOR);
            reArrangeBuckets();
        }
    }

    private void reArrangeBuckets() {
        Iterator<Map.Entry<K, V>> iterator = iterator();
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[capacity];
        while (iterator.hasNext()) {
            Entry<K, V> targetEntry = (Entry<K, V>) iterator.next();
            ArrayList<Entry<K, V>> targetBucket = getBucketByKey(targetEntry.key, newBuckets);
            targetBucket.add(targetEntry);
        }

        buckets = newBuckets;
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key) {
        return getBucketByKey(key, buckets);
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key, ArrayList<Entry<K, V>>[] bucketsArray) {
        int indexFromHash = Math.abs(key.hashCode() % capacity);
        if (bucketsArray[indexFromHash] == null) {
            bucketsArray[indexFromHash] = new ArrayList<>();
        }
        return bucketsArray[indexFromHash];
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int currentBucket;
            private int currentEntry;

            @Override
            public boolean hasNext() {
                if (currentBucket == buckets.length - 1) {
                    currentBucket = 0;
                    return false;
                }
                if (buckets[currentBucket] != null && buckets[currentBucket].size() != 0) {
                    if (buckets[currentBucket].size() > currentEntry
                            && buckets[currentBucket].get(currentEntry) != null) {
                        return true;
                    } else {
                        currentEntry = 0;
                        currentBucket++;
                        return hasNext();
                    }
                }
                currentBucket++;
                return hasNext();
            }

            @Override
            public Entry<K, V> next() {
                if (hasNext()) {
                    return buckets[currentBucket].get(currentEntry++);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if (hasNext()) {
                    buckets[currentBucket].remove(currentEntry);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    @Override
    public String toString() {
        return "HashMap{" +
                ", " + Arrays.toString(buckets) +
                '}';
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}


