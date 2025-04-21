package com.example;

import java.util.Stack;
import java.util.Vector;

/**
 * Class SecondChance
 * Updated by S.Dame March 2025
 */
public class SecondChance {
    private int numFrames, pageFaults, iteration;
    private final int MAX_FRAMES = 10;
    private final int MAX_PAGES = 100;
    private final int PAGE_MISS = -1;
    private int victim;

    private class Entry {
        public static final int INVALID = -1;
        public boolean reference;
        public int frame;

        public Entry() {
            reference = false;
            frame = INVALID;
        }
    }

    Vector<Entry> frameTable = new Vector<Entry>();
    Entry[][] SecondChanceMap = new Entry[MAX_PAGES][MAX_FRAMES];

    private int[] pageStream = new int[MAX_PAGES];
    private int[] pageFault = new int[MAX_PAGES];

    /**
     * Constructor for SecondChance
     * 
     * @param frames       depth of our page table
     * @param pageRequests number of pages in our reference test string
     */
    public SecondChance(int frames, int pageRequests[]) {
        // initialization of SecondChance variables
        numFrames = frames;
        pageFaults = 0;
        iteration = 0;
        victim = numFrames - 1; // Initialize to the end of the page table

        clearDisplayMap();

        // store the incoming page requests and process each one at a time with the
        // "insert" method
        for (int i = 0; i < pageRequests.length; i++) {
            pageStream[i] = pageRequests[i];
            this.insert(pageRequests[i]);
        }
    }

    /**
     * Gets the number of page faults.
     * 
     * @return The total number of page faults.
     */
    public final int getPageFaults() {
        return pageFaults;
    }

    /**
     * findPageHit search for a page in page table and update the reference bit
     * 
     * @param page test page for presence in page Table
     * @return index of page hit or -1 if page miss
     */
    private int isPageHit(int page) {
        for (int i = 0; i < frameTable.size(); i++) {
            Entry e = frameTable.elementAt(i);
            if (e.frame == page) {
                e.reference = true;
                return i;
            }
        }
        return PAGE_MISS;
    }

    /**
     * determines victim page based on 2nd chance scan
     * 
     * @return
     */
    private int nextVictim() {
        while (true) {
            // TASK 1: Implement the SecondChance Scan here and return the
            // victim frame number.
        }
    }

    /**
     * inserts a page into the frame table (if not already present)
     * 
     * @param page
     */
    private void insert(int page_reference) {
        
        // TASK 2: Implement the page insert algorithm to input page references into the
        // frameTable

        // write the results of the frame table to the display map
        for (int j = 0; j < frameTable.size(); j++) {
            Entry e = frameTable.elementAt(j);
            SecondChanceMap[iteration][j].frame = e.frame;
            SecondChanceMap[iteration][j].reference = e.reference;
        }
        ++iteration;
    }

    /**
     * Clear the 2nd Chance output display map
     */
    private void clearDisplayMap() {

        for (int i = 0; i < MAX_PAGES; i++) {
            for (int j = 0; j < MAX_FRAMES; j++) {
                SecondChanceMap[i][j] = new Entry();
                SecondChanceMap[i][j].frame = 0;
                SecondChanceMap[i][j].reference = false;
            }
        }
    }

    /**
     * display the page table over all iterations of the 2nd Chance algorithm
     */
    public void display() {
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("2nd Chance page faults:" + pageFaults);
        System.out.println("--------------------------------------------------------------------");

        for (int i = 0; i < iteration; i++) {
            System.out.print(pageStream[i] + "    ");
        }
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r--P-r");
        for (int j = 0; j < numFrames; j++) {
            for (int i = 0; i < iteration; i++) {
                int f = SecondChanceMap[i][j].frame;
                int r = (SecondChanceMap[i][j].reference ? 1 : 0);
                System.out.print(SecondChanceMap[i][j].frame + " " + r + "  ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < iteration; i++) {
            System.out.print(pageFault[i] + "    ");
        }
        System.out.println("\n--------------------------------------------------------------------");
    }
}