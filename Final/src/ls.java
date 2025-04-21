import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * ls - list of files in directory command
 */
public class ls extends Thread {
    private boolean listmode = false;

    /**
     * Default Constructor for ls
     * 
     * @param args
     */

    public ls() {
        listmode = false;
    }

    /**
     * This public method lists files
     * 
     * @param arg[0] - switch statement for [-l] e.g. list formatted
     */
    public ls(String[] args) {
        // check for required arguments
        if (args.length > 1) {
            SysLib.cerr("Usage: ls [-l]\n");
            SysLib.exit();
        } else if (args.length == 1) {
            listmode = args[0].equals("-l");
        } else {
            listmode = false;
        }
    }

    /**
     * The public standard Thread run method
     */
    public void run() {
        // 1. Declare a List to hold FilenameDataSizePair objects
        // your code goes here

        // 2. get the data from kernel interrupt
        // your code goes here

        // 3. Print Data depending on listmode
        // your code goes here

        SysLib.exit();
        return;
    }

    /**
     * Print a long listing for ls -l command
     * 
     * @param fileTableData
     */
    private void printLongListing(List<Directory.FilenameDataSizePair> fileTableData) {
        // your code goes here
        SysLib.cout("\n");
    }

    /**
     * Print a short listing for ls command
     * 
     * @param fileTableData
     */
    private void printShortListing(List<Directory.FilenameDataSizePair> fileTableData) {
        // your code goes here
        SysLib.cout("\n");
    }
}