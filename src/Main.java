public class Main {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		CSP csp = new CSP(12);
		System.out.println(minConflicts(csp, 1000));
	}
	
	private CSP minConflicts(CSP csp, int maxSteps) {
		for (int i = 0; i < maxSteps; ++i) {
//			System.out.println("new Loop: " + i);
			if (csp.solution())
				return csp;
			
			int amazonIndex = (int)(Math.random() * csp.getConflicts().size());
			
			System.out.println("AmazonIndex: " + amazonIndex + " out of " + csp.getConflicts().size());
			for (int n = 0; n < csp.getConflicts().size(); ++n) {
				System.out.println(n + "\t:" + csp.getConflicts().get(n));
			}
			
			Amazon conflict = csp.getConflicts().get(amazonIndex);
			int row = csp.minConflicts(conflict);
			
			
			System.out.println("Moving " + conflict + " to row " + row);
			csp.newRow(conflict, row);
			System.out.println(csp);
		}
		System.out.println("fail");
		return csp;
	}
}
