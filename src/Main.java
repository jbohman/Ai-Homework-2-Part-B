import java.util.Collections;

public class Main {
	
	private boolean DEBUG = false;
	
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
				for (int j = 0; j < 10; ++j) {
					CSP csp = new CSP(i);
					if (minConflicts(csp, 1000) == null)
						fails++;
				}
				System.out.println(i + ":\t" + fails + " took " + 
						(System.currentTimeMillis()-start));
			}
		}
		else {
			CSP csp = new CSP(300);
			System.out.println(minConflicts(csp, 1000));
		}
	}
	
	private CSP minConflicts(CSP csp, int maxSteps) {
		for (int i = 0; i < maxSteps; ++i) {
			if (csp.solution())
				return csp;
			Collections.shuffle(csp.variables);
			
			for (Amazon amazon : csp.variables) {
				if (csp.isConflicted(amazon)) { // TODO performance?
					amazon.setRow(csp.minConflicts(amazon));
					break;
				}
			}
//			Amazon var = csp.variables.get((int) (Math.random() * csp.variables.size()));
//			while(!csp.isConflicted(var)) {
//				var = csp.variables.get((int) (Math.random() * csp.variables.size()));
//			}
//			var.setRow(csp.minConflicts(var));
		}
		if (DEBUG) {
			System.out.println("Failed");
			
			for (int i = 0; i < csp.board; ++i) {
				if(csp.isConflicted(csp.variables.get(i))) {
					System.out.println(csp.variables.get(i));
				}
			}
//			System.out.println(csp);
		}
		return null;
	}
}
