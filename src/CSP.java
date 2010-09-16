import java.util.*;


public class CSP {
	private int board;
	private ArrayList<Constraint> constraints;
	private ArrayList<Amazon> variables;
	
	private ArrayList<Amazon> conflicts;
	
	public ArrayList<Amazon> getConflicts() {
		return conflicts;
	}

	public CSP(int board) {
		this.board = board;
		variables = new ArrayList<Amazon>();
		
		for (int i = 0; i < board; ++i) {
			variables.add(new Amazon((int)(Math.random()*board), i));
		}
		
		System.out.println(this);
		
		constraints = new ArrayList<Constraint>();
//		constraints.add(new BoxConstraint());
		constraints.add(new RowConstraint());
		constraints.add(new DiaConstraint());
		conflicts = new ArrayList<Amazon>();
		updateConflicts();
		
	}
	public boolean solution() {
		if (conflicts.size() > 0)
			return false;
		return true;
	}

	/**
	 * 
	 * @param amazon
	 * @return The row at which there is the least amount of conflicts
	 */
	public int minConflicts(Amazon amazon) {
		int conflicts = board;
		int row = -1;
		for (int i = 0; i < board; ++i) {
			if (i != amazon.getRow()) {
				int tmp = numConflicts(amazon, i);
				if (tmp <= conflicts) {
					conflicts = tmp;
					row = i;
				}
			}
		}
		return row;
	}
	
	/**
	 * @param amazon
	 * @param row The row we want to change
	 * @return
	 */
	private int numConflicts(Amazon amazon, int row) {
		int conflict = 0;
		int prevRow = amazon.getRow();
		amazon.setRow(row);
		
		for (Amazon other : variables) {
			if (other != amazon) {
				for (Constraint constraint : constraints) {
					if (!constraint.isValid(amazon, other)) {
						conflict++;
					}
				}
			}
		}
		amazon.setRow(prevRow);
		return conflict;
	}

	public void newRow(Amazon conflict, int row) {
		conflict.setRow(row);
		updateConflicts();
	}
	private void updateConflicts() {
		conflicts.clear();
		for (Amazon a : variables) {
			for (Amazon b : variables) {
				if (a != b) {
					for (Constraint constraint : constraints) {
						if (!constraint.isValid(a, b)) {
							if (!conflicts.contains(a))
								conflicts.add(a);
							if (!conflicts.contains(b))
								conflicts.add(b);
						}	
					}
				}
			}
		}
	}
	
	public String toString() {
		String tmp = "";
		for (int i = 0; i < board; ++i) {
			for (int j = 0; j < board; ++j) {
				if (variables.get(j).getRow() == i && variables.get(j).getCol() == j) {
					tmp += "A ";
				}
				else
					tmp += ". ";
			}
			tmp += "\t" + variables.get(i);
			tmp += "\n";
		}
//		for (Amazon a : variables) {
//			
//			tmp += a.toString() + "\n";
//		}
		return tmp;
	}
}
