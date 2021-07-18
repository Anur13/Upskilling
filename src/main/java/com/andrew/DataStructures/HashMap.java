package com.andrew.DataStructures;

import java.util.*;
import java.util.function.Consumer;

public class HashMap<K, V> implements Map<K, V>, Iterable {
    private int size = 5;
    private ArrayList<Entry<K, V>>[] buckets = new ArrayList[size];

    public HashMap() {
        for (int i = 0; i < size; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        V prevResult;
        ArrayList<Entry<K, V>> targetBucket = getBucketByKey(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (targetBucket.size() == 0) {
            targetBucket.add(newEntry);
        } else {
            for (int i = 0; i < targetBucket.size(); i++) {
                if (targetBucket.get(i).key.equals(key)) {
                    prevResult = targetBucket.get(i).value;
                    targetBucket.set(i, newEntry);
                    return prevResult;
                }
            }
        }
        return null;
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

    private ArrayList<Entry<K, V>> getBucketByKey(K key) {
        return buckets[key.hashCode() % size];
    }
//    @Override
//    public Iterator iterator() {
//return  Iterator;
//    }

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

