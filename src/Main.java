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
			if (csp.solution())
				return csp;
			
			Amazon var = csp.variables.get((int) (Math.random() * csp.variables.size()));
			while(!csp.isConflicted(var)) {
				var = csp.variables.get((int) (Math.random() * csp.variables.size()));
			}
			var.setRow(csp.minConflicts(var));
		}
		System.out.println("Failed");
		return csp;
	}
}
