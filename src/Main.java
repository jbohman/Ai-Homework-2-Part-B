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
			for (int i = 10; i <= 1000; i += 10) {
				int fails = 0;
				long start = System.currentTimeMillis();
				for (int j = 0; j < 1; ++j) {
					CSP csp = new CSP(i);
					if (minConflicts(csp, 1000) == -1)
						fails++;
				}
				System.out.println(i + ":\t" + fails + " took " + 
						(System.currentTimeMillis()-start));
			}
		}
		else {
			long start = System.currentTimeMillis();
			CSP csp = new CSP(1000);
			System.out.println(minConflicts(csp, 1000) + "\t took " + 
					(System.currentTimeMillis()-start));
		}
	}
	
	private int minConflicts(CSP csp, int maxSteps) {
		
		int shuffleList[] = new int[csp.board];
		for (int i = 0; i < csp.board; ++i) {
			shuffleList[i] = i;
		}
		
		for (int i = 0; i < maxSteps; ++i) {
			if (csp.solution()) {
//				System.out.println("found a sollution");
				return i;
			}
			
			shuffle(shuffleList);
			
			for (int n = 0; n < csp.board; ++n) {
//				System.out.println("shuffleList[" + n + "] = " + shuffleList[n]);
				if (csp.isConflicted(shuffleList[n])) {
					int moveTo = csp.minConflicts(shuffleList[n]);
					
//					System.out.println("csp.matrix[" + shuffleList[n] + "]  = "
//							+ moveTo + " (prev: " + csp.matrix[shuffleList[n]]
//							+ ")");
					
					csp.matrix[shuffleList[n]] = moveTo;
					
//					System.out.println(csp);
//					System.out.println("break");
//					break;
				}
			}	
		}
//		System.out.println(csp);
		return -1;
	}
	

	public static void shuffle(int[] array) {
	    // i is the number of items remaining to be shuffled.
	    for (int i = array.length; i > 1; i--) {
	        // Pick a random element to swap with the i-th element.
	        int j = rng.nextInt(i);  // 0 <= j <= i-1 (0-based array)
	        // Swap array elements.
	        int tmp = array[j];
	        array[j] = array[i-1];
	        array[i-1] = tmp;
	    }
	}

}
