package com.example;

import java.util.Stack;
import java.util.Vector;

/**
 * Class EnhancedSecondChance
 * Updated by S.Dame March 2025
 */
public class EnhancedSecondChance {
    private int numFrames, pageFaults, iteration;
    private final int MAX_FRAMES = 10;
    private final int MAX_PAGES = 100;
    private final int PAGE_MISS = -1;
    private int victim;

    private class Entry {
        public static final int INVALID = -1;
        public boolean reference;
        public boolean dirty;
        public int frame;

        public Entry() {
            reference = false;
            dirty = false;
            frame = INVALID;
        }
    }

    Vector<Entry> frameTable = new Vector<Entry>();
    Entry[][] EnhancedSecondChanceMap = new Entry[MAX_PAGES][MAX_FRAMES];

    private int[] pageStream = new int[MAX_PAGES];
    private int[] pageFault = new int[MAX_PAGES];

    /**
     * Constructor for EnhancedSecondChance class
     * 
     * @param frames       depth of our page table
     * @param pageRequests number of pages in our reference test string
     */
    public EnhancedSecondChance(int frames, int pageRequests[]) {
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
     * findPageHit search for a page in frame table and update the reference bit
     * 
     * @param page test page for presence in frame Table
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
     * The nextVictim() method determines victim page index based on Enahnced Second
     * Chance algorithm scan of the frame table.
     * Two Passes: The nextVictim() method performs two passes through the pageTable
     * in a circular manner.
     * Class-Based Prioritization:
     * Class Calculation: Inside the loops, it calculates the "class" of each page
     * based on the formula:
     * currentClass=(currentEntry.reference ? 2 : 0) + (currentEntry.dirty ? 1 : 0);
     * Class 0: (0, 0) - currentClass = 0
     * Class 1: (0, 1) - currentClass = 1
     * Class 2: (1, 0) - currentClass = 2
     * Class 3: (1, 1) - currentClass = 3
     * 
     * Best Class and Victim Tracking: It maintains bestClass (initially 4, higher
     * than any possible class) and bestVictim (initially -1). If a page with a
     * lower class is found, bestClass and bestVictim are updated.
     *
     * First Pass: The first pass tries to find a class 0 victim immediately and
     * returns it. If no class 0 victim is found, but a class 1 victim is found,
     * it will store the victim and its class.
     * It also gives second chance to class 2 & 3, so it will set their reference
     * bit to false in this pass.
     *
     * Second Pass: If a Class 0 victim was not found during the first pass, then it
     * will go through the frame table again, and select the lowest class based on
     * what it saw in the first pass.
     *
     * @return the best victim index into the frame table found (lowest class).
     */
    private int nextVictim() {
        int bestVictim = 0;

        // TASK 1: Implement the EnhancedSecondChance Scan here and return the
        // victim frame number.
        return bestVictim;
    }

    /**
     * inserts a page_reference into the frame table (if not already present)
     * 
     * @param page_reference
     */
    private void insert(int page_reference) {
        // TASK 2: Implement the page insert algorithm to input page references into the
        // frameTable

        // write the results of the page table to the display map
        for (int j = 0; j < frameTable.size(); j++) {
            Entry e = frameTable.elementAt(j);
            EnhancedSecondChanceMap[iteration][j].frame = e.frame;
            EnhancedSecondChanceMap[iteration][j].reference = e.reference;
            EnhancedSecondChanceMap[iteration][j].dirty = e.dirty;
        }
        ++iteration;
    }

    /**
     * Clear the 2nd Chance output display map
     */
    private void clearDisplayMap() {

        for (int i = 0; i < MAX_PAGES; i++) {
            for (int j = 0; j < MAX_FRAMES; j++) {
                EnhancedSecondChanceMap[i][j] = new Entry();
                EnhancedSecondChanceMap[i][j].frame = 0;
                EnhancedSecondChanceMap[i][j].reference = false;
                EnhancedSecondChanceMap[i][j].dirty = false;
            }
        }
    }

    /**
     * display the page table over all iterations of the 2nd Chance algorithm
     */
    public void display() {
        System.out.println(
                "\n------------------------------------------------------------------------------------------------");
        System.out.println("2nd Chance page faults:" + pageFaults);
        System.out.println(
                "------------------------------------------------------------------------------------------------");

        for (int i = 0; i < iteration; i++) {
            System.out.print(pageStream[i] + "      ");
        }
        System.out.println(
                "\n------------------------------------------------------------------------------------------------");
        System.out.println(
                "P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d--P-r-d");

        for (int j = 0; j < numFrames; j++) {
            for (int i = 0; i < iteration; i++) {

                int f = EnhancedSecondChanceMap[i][j].frame;
                int r = (EnhancedSecondChanceMap[i][j].reference ? 1 : 0);
                int d = (EnhancedSecondChanceMap[i][j].dirty ? 1 : 0);
                System.out.print(f + " " + r + " " + d + "  ");
            }
            System.out.println();
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------");
        for (int i = 0; i < iteration; i++) {
            System.out.print(pageFault[i] + "      ");
        }
        System.out.println(
                "\n------------------------------------------------------------------------------------------------");
    }
}
