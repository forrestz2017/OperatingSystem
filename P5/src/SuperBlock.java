public class SuperBlock {
    private final int defaultInodeBlocks = Constants.DEFAULT_INODE_BLOCKS;
    public int totalBlocks;
    public int inodeBlocks;
    public int freeList;

    /**
     * SuperBlock constructor.
     * Initializes the superblock by reading its data from the disk. If the disk's
     * contents are valid (i.e., the total number of blocks, inode blocks, and
     * the free list are consistent), it uses the data from the disk.
     * Otherwise, it performs a default format of the disk. The SuperBlock is
     * always located at Block #0.
     *
     * @param diskBlocks The total number of blocks in the disk.
     *                   This parameter is used to validate the disk's content
     *                   and to format the disk if necessary.
     */
    public SuperBlock(int diskBlocks) {
        // read the superblock from disk
        // ...
        // Implement your design
        // ...
    }

    void sync() {
        // ...
        // Implement your design
        // ...
    }

    void format() {
        format(defaultInodeBlocks);
    }

    // initialize the superblock
    // initialize each inode and immediately write it back to disk
    void format(int inodeBlocks) {

        // ...
        // Implement your design
        // ...

        sync();
    }

    public int getFreeBlock() {
        // get a new free block from the freelist
        int freeBlockNumber = freeList;

        // ...
        // Implement your design
        // ...

        return freeBlockNumber;
    }

    public boolean returnBlock(int oldBlockNumber) {
        // ...
        // Implement your design
        // ...
        return true;
    }
}