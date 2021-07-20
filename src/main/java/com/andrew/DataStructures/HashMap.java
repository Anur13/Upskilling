package com.andrew.DataStructures;

import java.util.*;
import java.util.function.Consumer;

public class HashMap<K, V> implements Map<K, V>, Iterable {
    private int capacity = 4;
    private final double loadFactor = 0.75;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[capacity];
    private int size;

    public HashMap() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

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


    private void ensureCapacity() {
        if (capacity * loadFactor <= size) {
            buckets = Arrays.copyOf(buckets, (int) (capacity * 1.5));
            capacity = (int) (capacity * 1.5);
            for (int i = size + 1; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            reArrangeMaps();
        }
    }

    private void reArrangeMaps() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();

        for (ArrayList<Entry<K, V>> bucket : buckets) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> targetEntry = bucket.get(i);
                ArrayList<Entry<K, V>> targetBucket = getBucketByKey(targetEntry.key);
                buffer.add(targetEntry);
                targetBucket.add(targetEntry);
                if (buffer.contains(targetEntry)) {
                   targetBucket.remove(targetEntry);
                }
            }
        }
    }

//    @Override
//    public Iterator iterator() {
//        int currentBucket = 0;
//        int currentEntry = 0;
//        int currentElement = 0;
//
//        return new Iterator() {
//
//            private int currentBucket = 0;
//            private int currentEntry = 0;
//
//            @Override
//            public boolean hasNext() {
//                return currentElement < size;
//            }
//
//            @Override
//            public V next() {
//            }
//
//            @Override
//            public void remove() {
//
//            }
//        };
//    }

    private ArrayList<Entry<K, V>> getBucketByKey(K key) {
        return buckets[key.hashCode() % capacity];
    }


    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
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


