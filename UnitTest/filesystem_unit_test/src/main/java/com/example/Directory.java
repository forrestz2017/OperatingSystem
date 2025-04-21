package com.example;

import java.util.*;

/**
 * The {@code Directory} class represents the directory structure of the file
 * system.
 * It manages file names and their corresponding inode numbers.
 * This class provides methods to allocate and deallocate inode numbers,
 * convert the directory structure to and from byte arrays for disk storage,
 * and look up inode numbers by file name.
 */
public class Directory {
    /**
     * The maximum number of characters allowed in a file name.
     * This constant is defined in {@link Constants}.
     */
    // ...
    // Implement your design
    // ...
    /**
     * Represents that an inode index is not used or unallocated.
     * This is different from Inode.UNASSIGNED.
     */
    // ...
    // Implement your design
    // ...
    /**
     * An array storing the actual size of each file name.
     * The size corresponds to the number of characters in the file name.
     * This is used for maintaining data integrity when writing to disk.
     */
    // ...
    // Implement your design
    // ...

    /**
     * A list to store file names as strings. This provides easier string
     * manipulation.
     * Each element at index i represents a file, where i is its inode number.
     */
    // ...
    // Implement your design
    // ...

    /**
     * Constructs a {@code Directory} object with a maximum number of inodes.
     *
     * @param maxInumber The maximum number of inodes (files) that can be stored in
     *                   this directory.
     *                   This value determines the size of the {@code filenameSizes}
     *                   array.
     */
    public Directory(int maxInumber) {
        // ...
        // Implement your design
        // ...
    }

    /**
     * Initializes the directory from a byte array read from the disk.
     *
     * @param data The byte array containing the directory information read from the
     *             disk.
     *             The data is expected to be formatted as a sequence of file sizes
     *             followed by a sequence of file names.
     */
    public void bytes2directory(byte data[]) {
        // assumes data[] contains directory information retrieved from disk
        // initialize the directory filenameSizes[] and fileNames with this data[]
        // ...
        // Implement your design
        // ...
    }

    /**
     * Converts the directory information into a byte array for storage on disk.
     *
     * @return A byte array representing the directory data, ready to be written to
     *         the disk.
     *         The byte array is formatted as a sequence of file sizes followed by a
     *         sequence of file names.
     */
    public byte[] directory2bytes() {
        // converts and return directory information into a plain byte array
        // this byte array will be written back to disk
        // ...
        // Implement your design
        // ...
        return null;
    }

    /**
     * Allocates a new inode number for a given file name.
     *
     * @param filename The name of the file for which to allocate an inode number.
     * @return The allocated inode number if successful; otherwise, -1 if no inode
     *         numbers are available.
     */
    public short ialloc(String filename) {
        // filename is the name of a file to be created.
        // allocates a new inode number for this filename.
        // ...
        // Implement your design
        // ...
        return -1;
    }

    /**
     * Deallocates an inode number, effectively deleting the corresponding file.
     *
     * @param iNodeIndex The inode index number to deallocate.
     * @return {@code true} if the inode number was successfully deallocated;
     *         {@code false} if the inode number was not in use.
     */
    public boolean ifree(short iNodeIndex) {
        // validate the iNodeIndex
        // ...
        // Implement your design
        // ...
        return true;
    }

    /**
     * Retrieves the inode number associated with a given file name.
     *
     * @param filename The name of the file to look up.
     * @return The inode number corresponding to the file name if found; otherwise,
     *         -1.
     */
    public short namei(String filename) {
        // returns the inumber corresponding to this filename
        // ...
        // Implement your design
        // ...
        return -1;
    }
}