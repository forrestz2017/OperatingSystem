
//*****************************************************************************
// File: Cache.java
// Date: December 2024
// Author: Stephen Dame (refactoring and documenting)
//
// Description: Implements the OS cache logic for paging from memory to DISK
//
// Changes made to update to the latest Java:
// Parameterized Vector<byte[]>:
//
// Updated pages to Vector<byte[]> to specify that it stores arrays of bytes.
// Removed all casts from pages.elementAt() calls, as the type is now explicit.
//
// Simplified Initialization:
// 
// Used new Vector<>() with the diamond operator to infer the type during initialization.
// Code Clarity:
// 
// Removed unnecessary type casts and ensured consistent generic usage.
//*****************************************************************************
import java.util.*;

public class Cache {
   private int blockSize;
   private Vector<byte[]> pages; // Parameterized Vector
   private int victim;
   final int PAGE_MISS = -1;

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

   private Entry[] frameTable = null;

   private int findFreeFrame() {
      for (int i = 0; i < frameTable.length; i++) {
         if (frameTable[i].frame == Entry.INVALID)
            return i;
      }
      return -1;
   }

   /**
    * implements the enhanced second chance algorithm scan
    */
   private int nextVictim() {
      // TO DO:
      // scanning logic for the enhanced second chance algorithm
      //
      return victim;
   }

   /**
    * implements the enhanced second chance algorithm scan
    */
   private int isPageHit() {
      int pageNumber = PAGE_MISS;
      // TO DO:
      // scanning logic to detect for a PageHit or PAGE_MISS
      //
      return pageNumber;
   }

   /*
    * Write any valid and dirty frames to the backing store (e.g. DISK)
    */
   private void writeBack(int victimFrame) {
      // System.out.println( "writeBack: " + victimFrame );
      if (frameTable[victimFrame].frame != Entry.INVALID && frameTable[victimFrame].dirty) {
         byte[] p = pages.elementAt(victimFrame);
         SysLib.rawwrite(frameTable[victimFrame].frame, p);
         frameTable[victimFrame].dirty = false;
      }
   }

   public Cache(int blockSize, int cacheBlocks) {
      this.blockSize = blockSize;
      pages = new Vector<>();
      for (int i = 0; i < cacheBlocks; i++) {
         byte[] p = new byte[blockSize];
         pages.addElement(p);
      }
      victim = cacheBlocks - 1; // set the last frame as a previous victim
      frameTable = new Entry[cacheBlocks];
      for (int i = 0; i < cacheBlocks; i++)
         frameTable[i] = new Entry();
   }

   public synchronized boolean read(int blockId, byte[] buffer) {
      if (blockId < 0) {
         SysLib.cerr("threadOS: a wrong blockId for cread\n");
         return false;
      }

      // locate a valid page
      for (int i = 0; i < frameTable.length; i++) {
         if (frameTable[i].frame == blockId) {
            byte[] p = pages.elementAt(i); // No cast needed
            System.arraycopy(p, 0, buffer, 0, blockSize);
            frameTable[i].reference = true;
            return true;
         }
      }

      int victimFrame = 0;
      // TO DO:
      // call isPageHit()
      // IF page miss
      // ..find an invalid page

      // write back a dirty copy
      writeBack(victimFrame);

      // read a requested block from disk
      SysLib.rawread(blockId, buffer);

      // cache it
      byte[] p = new byte[blockSize];
      System.arraycopy(buffer, 0, p, 0, blockSize);
      pages.set(victimFrame, p);
      frameTable[victimFrame].frame = blockId;
      frameTable[victimFrame].reference = true;
      return true;
   }

   public synchronized boolean write(int blockId, byte[] buffer) {
      if (blockId < 0) {
         SysLib.cerr("threadOS: a wrong blockId for cwrite\n");
         return false;
      }

      // locate a valid page
      for (int i = 0; i < frameTable.length; i++) {
         if (frameTable[i].frame == blockId) {
            byte[] p = new byte[blockSize];
            System.arraycopy(buffer, 0, p, 0, blockSize);
            pages.set(i, p);
            frameTable[i].reference = true;
            frameTable[i].dirty = true;
            return true;
         }
      }

      int victimFrame = 0;
      // TO DO:
      // call isPageHit()
      // IF page miss
      // ..find an invalid page

      // write back a dirty copy
      writeBack(victimFrame);

      // cache it but not write through.
      byte[] p = new byte[blockSize];
      System.arraycopy(buffer, 0, p, 0, blockSize);
      pages.set(victimFrame, p);
      frameTable[victimFrame].frame = blockId;
      frameTable[victimFrame].reference = true;
      frameTable[victimFrame].dirty = true;
      return true;
   }

   public synchronized void sync() {
      for (int i = 0; i < frameTable.length; i++)
         writeBack(i);
      SysLib.sync();
   }

   /**
    * flush all frames in the frameTable back to DISK and
    */
   public synchronized void flush() {
      for (int i = 0; i < frameTable.length; i++) {
         writeBack(i);
         frameTable[i].reference = false;
         frameTable[i].dirty = false;
         frameTable[i].frame = Entry.INVALID;
      }
      SysLib.sync();
   }
}