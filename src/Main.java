public class Main {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		CSP csp = new CSP(12);
		System.out.println(minConflicts(csp, 100000));
	}
	
	private CSP minConflicts(CSP csp, int maxSteps) {
		for (int i = 0; i < maxSteps; ++i) {
//			System.out.println("new Loop: " + i);
			if (csp.solution())
				return csp;
			
			Amazon conflict = csp.getConflicts().get((int) (Math.random()*csp.getConflicts().size()));
			int row = csp.minConflicts(conflict);
			csp.newRow(conflict, row);
		}
		System.out.println("fail");
		return csp;
	}
}
