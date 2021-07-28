package com.andrew.dataStructures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private HashMap<Integer, String> hashMap = new HashMap<>();

    @Test
    void testPutNewKey() {
        assertNull(hashMap.put(100, "test"));
    }

    @Test
    @DisplayName("Test Put On Existing Key")
    void testPutOnExistingKey() {
        hashMap.put(100, "test");
        assertEquals("test", hashMap.put(100, "test2"));
    }

    @Test
    void testGet() {
        hashMap.put(100, "100");
        assertEquals("100", hashMap.get(100));
        hashMap.put(135, "13");
        hashMap.put(1, "13");
        hashMap.put(16, "13");
        assertEquals("13", hashMap.get(16));
    }

    @Test
    void testContainsKey() {
        hashMap.put(100, "100");
        assertTrue(hashMap.containsKey(100));
        assertFalse(hashMap.containsKey(95));
    }

    @Test
    void testRemove() {
        hashMap.put(100, "100");
        assertEquals("100", hashMap.remove(100));
        assertNull(hashMap.remove(100));
    }

    @Test
    void testSize() {
        assertEquals(0, hashMap.size());
        hashMap.put(100, "100");
        assertEquals(1, hashMap.size());
        hashMap.remove(100);
        assertEquals(0, hashMap.size());
    }


    @Test
    void testIterator() {
        Iterator iterator = hashMap.iterator();
        assertFalse(iterator.hasNext());
        hashMap.put(100, "100");
        hashMap.put(135, "13");
        hashMap.put(1, "25");
        hashMap.put(16, "30");
        hashMap.put(13, "35");
        System.out.println(hashMap);
        Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iterator.next();
        assertEquals("25", entry.getValue());

        entry = (Map.Entry<Integer, String>) iterator.next();
        assertEquals("35", entry.getValue());

        entry = (Map.Entry<Integer, String>) iterator.next();
        assertEquals("13", entry.getValue());

        entry = (Map.Entry<Integer, String>) iterator.next();
        assertEquals("13", entry.getValue());
        entry = (Map.Entry<Integer, String>) iterator.next();
        assertEquals("13", entry.getValue());
    }
}