package com.andrew.DataStructures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
//        assertEquals("100", hashMap.get(100));
        hashMap.put(135, "13");
//        assertEquals("13", hashMap.get(13));
        hashMap.put(1, "13");

        hashMap.put(16, "13");
        hashMap.put(13, "13");
        hashMap.put(134, "13");
//
//        hashMap.put(18, "13");
//        hashMap.put(15, "13");        hashMap.put(14, "13");
//        hashMap.put(184, "13");
//        hashMap.put(1664, "13");



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
    void testSize(){
        assertEquals(16,hashMap.size());
    }
}