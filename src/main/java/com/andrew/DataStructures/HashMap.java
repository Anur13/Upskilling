package com.andrew.DataStructures;

import java.util.*;
import java.util.function.Consumer;

public class HashMap<K, V> implements Map<K, V>, Iterable {
    private int capacity = 4;
    private final double loadFactor = 0.75;
    private final double growthFactor = 1.5;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[capacity];
    private int size;

    @Override
    public V put(K key, V value) {
        V prevResult;
        ensureCapacity();
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (targetBucket.size() == 0) {
            targetBucket.add(newEntry);
            size++;
        } else {
            for (int i = 0; i < targetBucket.size(); i++) {
                if (targetBucket.get(i).key.equals(key)) {
                    prevResult = targetBucket.get(i).value;
                    targetBucket.set(i, newEntry);
                    return prevResult;
                } else {
                    targetBucket.add(newEntry);
                    size++;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "capacity=" + capacity +
                ", buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                '}';
    }

    @Override
    public V get(K key) {
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        for (Entry<K, V> Entry : targetBucket) {
            if (Entry.key.equals(key)) {
                return Entry.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V remove(K key) {
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        V prevResult;

        for (int i = 0; i < targetBucket.size(); i++) {
            if (targetBucket.get(i).key.equals(key)) {
                prevResult = targetBucket.get(i).value;
                targetBucket.remove(i);
                return prevResult;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    private void ensureCapacity() {
        if (capacity * loadFactor <= size) {
            capacity = (int) (capacity * growthFactor);
            buckets = Arrays.copyOf(buckets, (int) (capacity));
            reArrangeMaps();
        }
    }

    private void reArrangeMaps() {
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[capacity];
        for (ArrayList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> targetEntry : bucket) {
                    ArrayList<Entry<K, V>> targetBucket = getBucketByKey(targetEntry.key, newBuckets);
                    targetBucket.add(targetEntry);
                }
            }

        }
        buckets = newBuckets;
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key) {
        return getBucketByKey(key, buckets);
    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key, ArrayList<Entry<K, V>>[] bucketsArray) {
        if (bucketsArray[Math.abs(key.hashCode() % capacity)] == null) {
            bucketsArray[Math.abs(key.hashCode() % capacity)] = new ArrayList<>();
        }
        return bucketsArray[Math.abs(key.hashCode() % capacity)];
    }

    private static class Entry<K, V> {
        K key;
        V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}

