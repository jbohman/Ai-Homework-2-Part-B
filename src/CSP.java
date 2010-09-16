import java.util.*;


public class CSP {
	public int board;
	
	public ArrayList<Amazon> variables;
	
	private ArrayList<Amazon> conflicts;

	private BoxConstraint box;
	private RowConstraint rowConst;
	private DiaConstraint dia;
	
	public ArrayList<Amazon> getConflicts() {
		return conflicts;
	}

	public CSP(int board) {
		this.board = board;
		variables = new ArrayList<Amazon>();
		
		for (int i = 0; i < board; ++i) {
			variables.add(new Amazon((i*3)%board, i));
		}
		
		
		box = new BoxConstraint();
		rowConst = new RowConstraint();
		dia = new DiaConstraint();
		
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
				if (!rowConst.isValid(amazon, other))
					return true;
				if (!box.isValid(amazon, other))
					return true;
				if (!dia.isValid(amazon, other))
					return true;		
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
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conflicts = board;
		for (int i = 0; i < board; ++i) {
			if (i != amazon.getRow()) {
				int tmp = numConflicts(amazon, i);
				if (tmp <= conflicts) {
					if (tmp != conflicts) {
						lista.clear();
					}
					lista.add(i);
					conflicts = tmp;
				}
			}
		}
		Collections.shuffle(lista);
		return lista.get(0);
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
				if (!box.isValid(amazon, other))
					conflict++;
				if (!dia.isValid(amazon, other))
					conflict++;
				if (!rowConst.isValid(amazon, other))
					conflict++;
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
		return tmp;
	}
}
