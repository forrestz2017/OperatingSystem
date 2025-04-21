package com.example;

import java.util.Vector;
import java.util.*;

/**
 * Class LRU
 * Updated by S.Dame March 2025
 */
public class LRU {
    private int numFrames, pageFaults, iteration;
    private final int MAX_FRAMES = 10;
    private final int MAX_PAGES = 100;

    Vector<Integer> frameTable = new Vector<Integer>();
    Stack<Integer> lruStack = new Stack<Integer>();
    int[][] lruMap = new int[MAX_PAGES][MAX_FRAMES];

    private int[] pageStream = new int[MAX_PAGES];
    private int[] pageFault = new int[MAX_PAGES];

    /**
     * Constructor for LRU
     * 
     * @param frames       depth of page table
     * @param pageRequests array of page numbers to be requested
     */
    public LRU(int frames, int pageRequests[]) {
        numFrames = frames;
        pageFaults = 0;
        iteration = 0;

        // Clear the LRU Display Map
        for (int i = 0; i < MAX_PAGES; i++)
            for (int j = 0; j < MAX_FRAMES; j++)
                lruMap[i][j] = 0;

        // store the incoming page requests and process each one at a time with the
        // "insert" method
        for (int i = 0; i < pageRequests.length; i++) {
            pageStream[i] = pageRequests[i];
            this.insert(pageRequests[i]);
        }
    }

    /**
     * Gets the number of page faults.
     * @return The total number of page faults.
     */
    public final int getPageFaults() {
        return pageFaults;
    }

    /**
     * determines victim page based on least recently used pages
     * 
     * @return victim page
     */
    private int victim() {
        int victimPage = -1;

        // if the stack is not yet fully populated than just return the size as victim
        // index
        if (lruStack.size() < numFrames)
            return lruStack.size();

        // else it is fully initialized so return the bottom value as victim
        try {
            int ndx = lruStack.firstElement();
            victimPage = frameTable.indexOf(ndx);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
        return victimPage;
    }

    /**
     * inserts a page into the page table (if not already present)
     * 
     * @param page
     */
    private void insert(int page) {

        // if frameTable is not full, then just insert next page (if not duplicate)
        // if the pages is already in the page table, then just move it to the top of
        // the stack
        if (frameTable.size() < numFrames) {

            // if the pages is already in the page table, then just move it to the top of
            // the stack
            if (frameTable.contains(page)) {
                lruStack.removeElement(page);
                pageFault[iteration] = 0;
                // otherwise is a page fault and we need to find a victim LRU page and replace
            } else {
                pageFault[iteration] = 1;
                pageFaults++;
                int v = victim();
                frameTable.add(v, page);
            }
            lruStack.push(page);
            if (lruStack.size() > numFrames) {
                lruStack.remove(0);
            }

        } else {
            // if the pages is already in the page table, then just move it to the top of
            // the stack
            if (frameTable.contains(page)) {
                lruStack.removeElement(page);
                pageFault[iteration] = 0;
                // otherwise is a page fault and we need to find a victim LRU page and replace
            } else {
                pageFault[iteration] = 1;
                pageFaults++;
                int v = victim();
                frameTable.remove(v);
                frameTable.add(v, page);
            }
            lruStack.push(page);
            if (lruStack.size() > numFrames) {
                lruStack.remove(0);
            }
        }

        for (int j = 0; j < frameTable.size(); j++) {
            lruMap[iteration][j] = frameTable.get(j);
        }
        ++iteration;
    }

    /**
     * display the page table over all iterations of the LRU algorithm
     */
    public void display() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("LRU page faults:" + pageFaults);
        System.out.println("\n-----------------------------------------------------------");

        for (int i = 0; i < iteration; i++) {
            System.out.print(pageStream[i] + "  ");
        }
        System.out.println("\n-----------------------------------------------------------");

        for (int j = 0; j < numFrames; j++) {
            for (int i = 0; i < iteration; i++) {
                System.out.print(lruMap[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < iteration; i++) {
            System.out.print(pageFault[i] + "  ");
        }
        System.out.println("\n-----------------------------------------------------------");
    }
}
