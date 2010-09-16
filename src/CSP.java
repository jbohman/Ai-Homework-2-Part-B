import java.util.*;


public class CSP {
	public int board;

	int matrix[];

	public CSP(int board) {
		this.board = board;
		
		matrix = new int[board];
		
		for (int i = 0; i < board; ++i) {
			matrix[i] = (i * 3) % board;
//			matrix[i] = i;
		}
//		System.out.println(this);
	}
	
	public boolean solution() {
		for (int i = 0; i < board; ++i) {
			if (isConflicted(i))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public boolean isConflicted(int col) {
		for (int i = 0; i < board; ++i) {
			if (!equal(i, col)) {
				if (conflicts(col, matrix[col], i, matrix[i]))
					return true;
			}
		}
		return false;
	}
	
	public boolean equal(int aCol, int bCol) {
		if (aCol == bCol && matrix[aCol] == matrix[bCol])
			return true;
		return false;
	}

	/**
	 * 
	 * @param amazon
	 * @return The row at which there is the least amount of conflicts
	 */
	public int minConflicts(int col) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conflicts = Integer.MAX_VALUE;
		
		for (int i = 0; i < board; ++i) {
			if (i != matrix[col]) {
				
				int tmp = numConflicts(col, i);
//				System.out.println(tmp + " <= " + conflicts);
				if (tmp <= conflicts) {
					if (tmp != conflicts) {
						lista.clear();
					}
					lista.add(i);
					conflicts = tmp;
				}
			}
		}
//		lista.remove((Integer)matrix[col]);
//		for (Integer asdf : lista) {
//			System.out.print(asdf + ", ");
//		}
//		System.out.println();
		return lista.get((int)(Math.random()*lista.size()));
	}
	

	public int numConflicts(int col, int newRow) {
		int conflict = 0;
		
		for (int i = 0; i < board; ++i) {
			if (matrix[i] != matrix[col]) {
				conflict += numConflicts(col, newRow, i, matrix[i]);
			}
			
		}
		return conflict;
	}
	
	
	boolean conflicts(int aCol, int aRow, int bCol, int bRow) {
		// row
		if (aRow == bRow)
			return true;
		// col
		if (aCol == bCol)
			return true;
		// dia
		if (Math.abs(aRow - bRow ) == Math.abs(aCol - bCol))
			return true;
//		// box
		if (Math.abs(aCol - bCol) <= 2 && Math.abs(aRow - bRow) <= 2)
			return true;
		return false;
	}
	
	byte numConflicts(int aCol, int aRow, int bCol, int bRow) {
		byte tmp = 0;
		// row
		if (aRow == bRow)
			tmp++;
		// col
		if (aCol == bCol)
			tmp++;
		// dia
		if (Math.abs(aRow - bRow ) == Math.abs(aCol - bCol))
			tmp++;
//		// box
		if (Math.abs(aCol - bCol) <= 2 && Math.abs(aRow - bRow) <= 2)
			tmp++;
		return tmp;
	}
	
	public String toString() {
		String tmp = "";
		for (int i = 0; i < board; ++i) {
			for (int j = 0; j < board; ++j) {
				if (matrix[i] == j)
					tmp += "A ";
				else
					tmp += ". ";
			}
			tmp += "\n";
		}
		return tmp;
	}
}
