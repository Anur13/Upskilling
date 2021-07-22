package com.andrew.DataStructures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private HashMap<Integer, String> hashMap = new HashMap<>();

    @Test
    void testPutNewKey() {
        assertNull(hashMap.put(100, "test"));
    }

    @Test
    void testGet() {
        hashMap.put(100, "100");
        assertEquals("100", hashMap.get(100));
        hashMap.put(135, "13");
        assertEquals("13", hashMap.get(135));
    }

    @Test
    void testGrowth()  {
        hashMap.put(15, "13");
        hashMap.put(125, "13");
        hashMap.put(135, "13");
        assertEquals(4, hashMap.capacity());

        hashMap.put(155, "13");
        assertEquals(6, hashMap.capacity());

        hashMap.put(1515, "13");
        hashMap.put(1115, "13");

        assertEquals(9, hashMap.capacity());

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
        hashMap.put(135, "13");
        hashMap.put(125, "13");
        assertEquals(2, hashMap.size());
        hashMap.put(125, "13");
        assertEquals(2, hashMap.size());
        hashMap.put(115, "13");
        assertEquals(3, hashMap.size());

    }
}