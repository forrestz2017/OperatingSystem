package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRU_SecondChanceTest {

    @Test
    public void testReferenceString12x4A() {
        int[] pageRequests = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 3 };
        int numFrames = 4;
        SecondChance lru2ndchance = new SecondChance(numFrames, pageRequests);
        // assertEquals(6, lru2ndchance.getPageFaults());
        assertEquals(0, lru2ndchance.getPageFaults());
        lru2ndchance.display();
    }

    @Test
    public void testReferenceString12x4B() {
        int[] pageRequests = { 1, 8, 1, 2, 8, 6, 2, 3, 2, 6, 4, 3, 8, 3 };
        int numFrames = 4;
        SecondChance lru2ndchance = new SecondChance(numFrames, pageRequests);
        // assertEquals(7, lru2ndchance.getPageFaults());
        assertEquals(0, lru2ndchance.getPageFaults());        
        lru2ndchance.display();
    }

    @Test
    public void testReferenceString12x4C() {
        int[] pageRequests = { 7, 6, 1, 4, 5, 4, 2, 7, 4, 1, 3, 7, 6, 6 };
        int numFrames = 4;
        SecondChance lru2ndchance = new SecondChance(numFrames, pageRequests);
        // assertEquals(10, lru2ndchance.getPageFaults());
        assertEquals(0, lru2ndchance.getPageFaults());
        lru2ndchance.display();
    }
}
