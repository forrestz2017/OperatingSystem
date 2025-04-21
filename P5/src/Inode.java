public class Inode {
    private SysLib sysLib;

    public final static int iNodeSize = Constants.INODE_SIZE;
    public final static int directSize = Constants.DIRECT_SIZE;
    public final static int iNodesPerBlock = Disk.blockSize / iNodeSize;

    public final static short NoError = 0;
    public final static short ErrorBlockRegistered = -1;
    public final static short ErrorPrecBlockUnused = -2;
    public final static short ErrorIndirectNull = -3;
    public final static short UNASSIGNED = -1;

    public int length; // file size in bytes
    public short count; // # file-table entries pointing to this
    public short flag; // 0 = unused, 1 = used, ...
    public short[] direct; // direct pointers
    public short indirect; // an indirect pointer

    public Inode() {
        length = 0;
        count = 0;
        flag = 1;
        direct = new short[directSize];
        for (int i = 0; i < directSize; i++) {
            direct[i] = UNASSIGNED;
        }
        indirect = UNASSIGNED;
    }

    /**
     * Inode constructor to create an Inode object from disk.
     * Reads the inode data from the disk based on the given iNumber.
     *
     * @param iNumber The inode number to retrieve from the disk.
     *                It's used to calculate the block number and offset where the
     *                inode data is stored.
     *
     *                The inode structure is laid out in the block as follows:
     * 
     *                offset + 0 (4 bytes): length - The file size in bytes.
     *                offset + 4 (2 bytes): count - The number of file-table
     *                entries pointing to this inode.
     *                offset + 6 (2 bytes): flag - The inode status flag.
     *                offset + 8 to offset + 31 (22 bytes): direct[0-10] - The
     *                direct pointers, each of which is a 2-byte short.
     *                offset + 30 (2 bytes): indirect - The indirect pointer.
     * 
     */
    public Inode(short iNumber) {
        // Calculate the block number where the inode is stored.
        // Inodes start from block #1, and multiple inodes can be stored in a single
        // block.
        // ...
        // Implement your design
        // ...
    }

    /**
     * Writes the current inode data to disk.
     * This method takes the current state of the inode (length, count, flag,
     * direct pointers, and indirect pointer) and writes it back to the
     * appropriate location on the disk based on the provided inode number.
     *
     * @param iNumber The inode number that determines where on the disk the
     *                inode data should be written.
     *
     *                The inode structure is laid out in the block as follows:
     * 
     *                offset + 0 (4 bytes): length - The file size in bytes
     *                offset + 4 (2 bytes): count - The number of file-table entries
     *                pointing to this inode
     *                offset + 6 (2 bytes): flag - The inode status flag
     *                offset + 8 to offset + 31 (22 bytes): direct[0-10] - The
     *                direct pointers, each of which is a 2-byte short.
     *                offset + 30 (2 bytes): indirect - The indirect pointer
     */
    public void toDisk(short iNumber) {
        // ...
        // Implement your design
        // ...
    }

    /**
     * unregisterIndexBlock: unregister the IndexBlock and clear the content in disk
     *
     * @return the former content in the IndexBlock
     */
    public byte[] unregisterIndexBlock() {
        // ...
        // Implement your design
        // ...
    }

    /**
     * registerIndexBlock: Registers an indirect block for the inode.
     * This method assigns a given block number as the indirect block for the
     * inode, if all direct blocks have been assigned and the indirect block
     * is not already in use. It also initializes the newly assigned indirect
     * block with UNASSIGNED values.
     *
     * @param indexBlockNumber The block number to be registered as the indirect
     *                         block.
     * @return True if the indirect block was successfully registered, false
     *         otherwise.
     *         Conditions for failure:
     *         1. Not all direct pointers are assigned.
     *         2. The indirect pointer is already assigned.
     */
    boolean registerIndexBlock(short indexBlockNumber) {
        // ...
        // Implement your design
        // ...
    }

    /**
     * registerTargetBlock: register a new targetBlock
     *
     * @param offset            the offset of the target block
     * @param targetBlockNumber the index of new targetBlock
     * @return Inode.NoError when the targetBlock is registered
     */
    public short registerTargetBlock(int offset, short targetBlockNumber) {
        // ...
        // Implement your design
        // ...
    }

    /**
     * findIndexBlock: find the IndexBlock
     *
     * @return the index of the IndexBlock
     */
    public int findIndexBlock() {
        return indirect;
    }

    /**
     * findTargetBlock: Finds the disk block number associated with a given file
     * offset.
     * This method determines whether the requested offset falls within the range of
     * direct pointers or requires accessing the indirect pointer. It then returns
     * the
     * corresponding disk block number where the data for that offset is stored.
     *
     * @param offset The byte offset within the file for which to find the
     *               corresponding
     *               disk block number.
     * @return The disk block number (a short) where the data for the given offset
     *         is
     *         stored. Returns UNASSIGNED if the offset is out of range or if the
     *         necessary pointers are unassigned.
     *         The logic is as follows:
     *         1. if offset < directSize * Disk.blockSize:
     *         The offset is within the range of direct pointers.
     *         Calculate the index of the direct pointer: offset / Disk.blockSize.
     *         If the direct pointer at the calculated index is valid (>= 0),
     *         return the block number stored in that direct pointer.
     *         2. else:
     *         The offset is beyond the range of direct pointers, so we need to use
     *         the
     *         indirect pointer.
     *         If the indirect pointer is valid (not UNASSIGNED):
     *         Read the indirect block from disk (newIndexBlock).
     *         Calculate the index within the indirect block: (offset - directSize *
     *         Disk.blockSize) / Disk.blockSize.
     *         This formula subtracts the size covered by direct pointers to get the
     *         relative offset within the indirect block's range.
     *         Retrieve the target block number from the indirect block using
     *         SysLib.bytes2short(newIndexBlock, index * 2).
     *         Each entry in the indirect block is a short (2 bytes), so we multiply
     *         the index by 2 to get the byte offset within the indirect block.
     *         Return the target block number.
     *         3. If the indirect pointer is UNASSIGNED or if the direct pointer
     *         is not valid, return UNASSIGNED.
     */
    public short findTargetBlock(int offset) {
        short target = UNASSIGNED;
        // ...
        // Implement your design
        // ...
        return target;
    }

    /**
     * print: Print the entire inode structure
     */
    public void printInode() {
        SysLib.cerr("Inode Debug Information:\n");
        SysLib.cerr("  Length: " + length + "\n");
        SysLib.cerr("  Count: " + count + "\n");
        SysLib.cerr("  Flag: " + flag + "\n");

        SysLib.cerr("  Direct Pointers:\n");
        for (int i = 0; i < directSize; i++) {
            SysLib.cerr("    direct[" + i + "]: " + direct[i] + "\n");
        }

        SysLib.cerr("  Indirect Pointer: " + indirect + "\n");
        SysLib.cerr("End Inode Debug Information.\n");
    }
}