import java.util.Collections;
import java.util.Random;


public class Main {
	static Random rng = new Random();

	private boolean DEBUG = true;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		if (!DEBUG) {
			for (short i = 10; i <= 1000; i += 10) {
				short fails = 0;
				long start = System.currentTimeMillis();
				for (short j = 0; j < 1; ++j) {
					CSP csp = new CSP(i);
					if (minConflicts(csp, (short)1000) == -1)
						fails++;
				}
				System.out.println(i + ":\t" + fails + " took " + 
						(System.currentTimeMillis()-start));
			}
		}
		else {
			long start = System.currentTimeMillis();
			CSP csp = new CSP((short)1000);
			System.out.println(minConflicts(csp, (short)1000) + "\t took " + 
					(System.currentTimeMillis()-start));
		}
	}
	
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
					

//					break;
				}
			}	
		}
		return -1;
	}
	

	public static void shuffle(short[] array) {
	    // i is the number of items remaining to be shuffled.
	    for (int i = array.length; i > 1; i--) {
	        // Pick a random element to swap with the i-th element.
	        short j = (short) rng.nextInt(i);  // 0 <= j <= i-1 (0-based array)
	        // Swap array elements.
	        short tmp = array[j];
	        array[j] = array[i-1];
	        array[i-1] = tmp;
	    }
	}

}
