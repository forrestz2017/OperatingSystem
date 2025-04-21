import java.util.Date;
import java.util.Random;

class Test4 extends Thread {
	private boolean started = false;
	private boolean enabled;
	private int testcase;
	private long startTime;
	private long endTime;
	private byte[] wbytes;
	private byte[] rbytes;
	private Random rand;
	final int PAGE_SIZE = Disk.blockSize;
	final int MAX_PAGES = 200;

	/**
	 * println
	 * 
	 * @param s - string to print out
	 */
	private void println(String s) {
		SysLib.cout(s + "\n");
	}

	private void getPerformance(String msg) {
		if (enabled == true)
		println("Test " + msg + "(cache enabled): "
					+ (endTime - startTime) + " milliseconds\n");
		else
		println("Test " + msg + "(cache disabled): "
					+ (endTime - startTime) + " milliseconds\n");
	}

	private void read(int blk, byte[] bytes) {
		if (enabled == true)
			SysLib.cread(blk, bytes);
		else
			SysLib.rawread(blk, bytes);
	}

	private void write(int blk, byte[] bytes) {
		if (enabled == true)
			SysLib.cwrite(blk, bytes);
		else
			SysLib.rawwrite(blk, bytes);
	}

	private void randomAccess() {
		int[] accesses = new int[MAX_PAGES];
		for (int i = 0; i < MAX_PAGES; i++) {
			accesses[i] = Math.abs(rand.nextInt() % PAGE_SIZE);
			// SysLib.cout( accesses[i] + " " );
		}
		// SysLib.cout( "\n" );
		for (int i = 0; i < MAX_PAGES; i++) {
			for (int j = 0; j < PAGE_SIZE; j++)
				wbytes[j] = (byte) (j);
			write(accesses[i], wbytes);
		}
		for (int i = 0; i < MAX_PAGES; i++) {
			read(accesses[i], rbytes);
			for (int k = 0; k < PAGE_SIZE; k++) {
				if (rbytes[k] != wbytes[k]) {
					SysLib.cerr("ERROR\n");
					SysLib.exit();
				}
			}
		}
	}

	private void localizedAccess() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < PAGE_SIZE; j++)
				wbytes[j] = (byte) (i + j);
			for (int j = 0; j < 1000; j += 100)
				write(j, wbytes);
			for (int j = 0; j < 1000; j += 100) {
				read(j, rbytes);
				for (int k = 0; k < PAGE_SIZE; k++) {
					if (rbytes[k] != wbytes[k]) {
						SysLib.cerr("ERROR\n");
						SysLib.exit();
					}
				}
			}
		}
	}

	private void mixedAccess() {
		int[] accesses = new int[MAX_PAGES];
		for (int i = 0; i < MAX_PAGES; i++) {
			if (Math.abs(rand.nextInt() % 10) > 8) {
				// random
				accesses[i] = Math.abs(rand.nextInt() % PAGE_SIZE);
			} else {
				// localized
				accesses[i] = Math.abs(rand.nextInt() % 10);
			}
		}
		for (int i = 0; i < MAX_PAGES; i++) {
			for (int j = 0; j < PAGE_SIZE; j++)
				wbytes[j] = (byte) (j);
			write(accesses[i], wbytes);
		}
		for (int i = 0; i < MAX_PAGES; i++) {
			read(accesses[i], rbytes);
			for (int k = 0; k < PAGE_SIZE; k++) {
				if (rbytes[k] != wbytes[k]) {
					SysLib.cerr("ERROR\n");
					SysLib.exit();
				}
			}
		}
	}

	private void adversaryAccess() {
		int[] accesses = new int[MAX_PAGES];
		for (int i = 0; i < MAX_PAGES; i++)
			accesses[i] = (i % 2 == 0) ? i : i + 256;

		for (int i = 0; i < MAX_PAGES; i++) {
			for (int j = 0; j < PAGE_SIZE; j++)
				wbytes[j] = (byte) (j);
			write(accesses[i], wbytes);
		}
		for (int i = 0; i < MAX_PAGES; i++) {
			read(accesses[i], rbytes);
			for (int k = 0; k < PAGE_SIZE; k++) {
				if (rbytes[k] != wbytes[k]) {
					SysLib.cerr("ERROR\n");
					SysLib.exit();
				}
			}
		}
	}

	/**
	 * null argument constructor only prints required argument Usage
	 */
	public Test4() {
		// check for required arguments
		SysLib.cerr("Usage: l Test4 [-enabled | -disabled] <testcase (1-5)>\n");
		SysLib.exit();
	}

	/**
	 * constructor checks for required arguments before starting test
	 */
	public Test4(String[] args) {
		// check for required arguments
		if (args.length < 2) {
			SysLib.cerr("Usage: l Test4 [-enabled | -disabled] <testcase (1-5)>\n");
			SysLib.exit();
		} else {
			enabled = args[0].equals("-enabled") ? true : false;
			testcase = Integer.parseInt(args[1]);
			println("creating write byte buffer, blocksize: " + Disk.blockSize);
			wbytes = new byte[Disk.blockSize];
			println("creating read byte buffer, blocksize: " + Disk.blockSize);
			rbytes = new byte[Disk.blockSize];
			rand = new Random();
			started = true;	
		}
	}

	public void run() {
		if (started) {
			SysLib.flush();
			startTime = new Date().getTime();
			println("Starting Test: " + testcase + " with Cache Enabled: " + enabled + "\n");
			switch (testcase) {
				case 1:
					randomAccess();
					endTime = new Date().getTime();
					getPerformance("random accesses");
					break;
				case 2:
					localizedAccess();
					endTime = new Date().getTime();
					getPerformance("localized accesses");
					break;
				case 3:
					mixedAccess();
					endTime = new Date().getTime();
					getPerformance("mixed accesses");
					break;
				case 4:
					adversaryAccess();
					endTime = new Date().getTime();
					getPerformance("adversary accesses");
					break;
				case 5:
					randomAccess();
					endTime = new Date().getTime();
					getPerformance("random accesses");

					startTime = new Date().getTime();
					localizedAccess();
					endTime = new Date().getTime();
					getPerformance("localized accesses");

					startTime = new Date().getTime();
					mixedAccess();
					endTime = new Date().getTime();
					getPerformance("mixed accesses");

					startTime = new Date().getTime();
					adversaryAccess();
					endTime = new Date().getTime();
					getPerformance("adversary accesses");
					break;
			}
		}
		System.err.println("Exiting Test");
		SysLib.exit();
	}
}
