import java.util.*;


public class CSP {
	private int board;
	private ArrayList<Constraint> constraints;
	public ArrayList<Amazon> variables;
	
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
		constraints.add(new BoxConstraint());
		constraints.add(new RowConstraint());
		constraints.add(new DiaConstraint());
		conflicts = new ArrayList<Amazon>();
	}
	public boolean solution() {
		for (int i = 0; i < board; ++i) {
			if(isConflicted(variables.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 */
	public boolean isConflicted(Amazon amazon) {
		for (Amazon other : variables) {
			if (other != amazon) {
				for (Constraint constraint : constraints) {
					if (!constraint.isValid(amazon, other)) {
						return true;
					}
				}			
			}
		}
		return false;
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
	public int numConflicts(Amazon amazon, int row) {
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
