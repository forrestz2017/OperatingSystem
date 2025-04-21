public class FileSystem {
    private SuperBlock superblock;
    private Directory directory;
    private FileTable filetable;

    public FileSystem(int diskBlocks) {
        // create superblock, and format disk with 64 inodes in default
        superblock = new SuperBlock(diskBlocks);

        // create directory, and register "/" in directory entry 0
        // ...
        // Implement your design
        // ...

        // file table is created, and store directory in the file table

        // ...
        // Implement your design
        // ...
    }

    void sync() {
        // directory synchronization
        // ...
        // Implement your design
        // ...

        // superblock synchronization
        superblock.sync();
    }

    boolean format(int files) {
        // wait until all filetable entries are destructed
        // ...
        // Implement your design
        // ...

        // format superblock, initialize inodes, and create a free list
        // ...
        // Implement your design
        // ...

        // create directory, and register "/" in directory entry 0
        // ...
        // Implement your design
        // ...

        // file table is created, and store directory in the file table
        // ...
        // Implement your design
        // ...

        return true;
    }

    FileTableEntry open(String filename, String mode) {
        // filetable entry is allocated
        // ...
        // Implement your design
        // ...
    }

    boolean close(FileTableEntry ftEnt) {
        // filetable entry is freed
        // ...
        // Implement your design
        // ...
        return true;
    }

    int fsize(FileTableEntry ftEnt) {
        // ...
        // Implement your design
        // ...
    }

    int read(FileTableEntry ftEnt, byte[] buffer) {
        if (ftEnt.mode == "w" || ftEnt.mode == "a")
            return -1;

        int offset = 0; // buffer offset
        int left = buffer.length; // the remaining data of this buffer

        synchronized (ftEnt) {
            // ...
            // Implement your design
            // ...
        }
        return offset;
    }

    int write(FileTableEntry ftEnt, byte[] buffer) {
        // at this point, ftEnt is only the one to modify the inode
        if (ftEnt.mode == "r")
            return -1;

        synchronized (ftEnt) {
            // ...
            // Implement your design
            // ...
            return offset;
        }
    }

    private boolean deallocAllBlocks(FileTableEntry ftEnt) {
        // busy wait until there are no threads accessing this inode
        // ...
        // Implement your design
        // ...
        return true;
    }

    boolean delete(String filename) {
        // busy wait until there are no threads accessing this inode
        // ...
        // Implement your design
        // ...
        return true;
    }

    private final int SEEK_SET = 0;
    private final int SEEK_CUR = 1;
    private final int SEEK_END = 2;

    int seek(FileTableEntry ftEnt, int offset, int whence) {
        synchronized (ftEnt) {
            // ...
            // Implement your design
            // ...
            return ftEnt.seekPtr;
        }
    }
}