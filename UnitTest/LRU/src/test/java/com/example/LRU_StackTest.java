package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRU_StackTest {

    @Test
    public void testReferenceString12x3A() {
        int[] pageRequests = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int numFrames = 3;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(10, lru.getPageFaults());
        lru.display();
    }

    @Test
    public void testReferenceString12x3B() {
        int[] pageRequests = {1,8,1,2,8,6,2,3,2,6,4,3,8,3};
        int numFrames = 3;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(8, lru.getPageFaults());
        lru.display();
    }

    @Test
    public void testReferenceString12x3C() {
        int[] pageRequests = {7,6,1,4,5,4,2,7,4,1,3,7,6,6};
        int numFrames = 3;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(11, lru.getPageFaults());
        lru.display();
    }

    @Test
    public void testReferenceString12x4A() {
        int[] pageRequests = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        int numFrames = 4; 
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(6, lru.getPageFaults());
        lru.display();
    }
    
    @Test
    public void testReferenceString12x4B() {
        int[] pageRequests = {1,8,1,2,8,6,2,3,2,6,4,3,8,3};
        int numFrames = 4; // Example number of frames
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(7, lru.getPageFaults());
        lru.display();
    }

    @Test
    public void testReferenceString12x4C() {
        int[] pageRequests = {7,6,1,4,5,4,2,7,4,1,3,7,6,6};
        int numFrames = 4;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(10, lru.getPageFaults());
        lru.display();
    }    

    @Test
    public void testLRUWithShortReferenceStringSingleFrame() {
        int[] pageRequests = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int numFrames = 1;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(12, lru.getPageFaults());
        lru.display();
    }

    @Test
    public void testLRUWithDuplicateRequests(){
        int[] pageRequests = {1,1,1,1,1,1};
        int numFrames = 3;
        LRU lru = new LRU(numFrames, pageRequests);
        assertEquals(1, lru.getPageFaults());
        lru.display();
    }
}
