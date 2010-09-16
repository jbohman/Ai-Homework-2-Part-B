import java.util.Random;

public class Main {
    static Random rng = new Random();

    private boolean DEBUG = false;

    /**
     * @param args
     */
    public static void main(String[] args) {
	new Main();
    }

    /**
     * Run the actual code
     */
    public Main() {
	if (!DEBUG) {
	    for (short i = 50; i <= 1000; i += 50) {
		short fails = 0;
		long start = System.currentTimeMillis();
		// for (short j = 0; j < 1; ++j) {
		CSP csp = new CSP(i);
		short iterations = minConflicts(csp, (short) 1000);
		if (iterations == -1)
		    fails++;
		// }
		System.out.println(i + ":\t" + iterations
			+ " \titerations took \t "
			+ (System.currentTimeMillis() - start) + " ms");
	    }
	} else {
	    long start = System.currentTimeMillis();
	    CSP csp = new CSP((short) 1000);
	    System.out.println(minConflicts(csp, (short) 1000) + "\t took "
		    + (System.currentTimeMillis() - start));
	}
    }

    /**
     * The actual minConflicts algorithm
     * 
     * @param csp
     *            An CSP
     * @param maxSteps
     *            The maximum amount of steps to take before bailing
     * @return The number of steps actually taken, or -1 if we bailed.
     */
    private short minConflicts(CSP csp, short maxSteps) {

	short shuffleList[] = new short[csp.board];
	for (short i = 0; i < csp.board; ++i) {
	    shuffleList[i] = i;
	}

	for (short i = 0; i < maxSteps; ++i) {
	    if (csp.solution()) {
		return i;
	    }

	    shuffle(shuffleList);

	    for (short n = 0; n < csp.board; ++n) {
		if (csp.isConflicted(shuffleList[n])) {
		    short moveTo = csp.minConflicts(shuffleList[n]);

		    csp.matrix[shuffleList[n]] = moveTo;

		    break;
		}
	    }
	}
	return -1;
    }

    /**
     * A function for shuffling an array.
     * 
     * @param array
     *            The array to be shuffled.
     */
    public static void shuffle(short[] array) {
	// i is the number of items remaining to be shuffled.
	for (int i = array.length; i > 1; i--) {
	    // Pick a random element to swap with the i-th element.
	    short j = (short) rng.nextInt(i); // 0 <= j <= i-1 (0-based array)
	    // Swap array elements.
	    short tmp = array[j];
	    array[j] = array[i - 1];
	    array[i - 1] = tmp;
	}
    }

}
