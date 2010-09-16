import java.util.*;


public class CSP {
	public short board;

	short matrix[];

	public CSP(short board) {
		this.board = board;
		
		matrix = new short[board];
		
		for (short i = 0; i < board; ++i) {
			matrix[i] = (short)((i * 3) % board);
		}
	}
	
	public boolean solution() {
		for (short i = 0; i < board; ++i) {
			if (isConflicted(i))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public boolean isConflicted(short col) {
		for (short i = 0; i < board; ++i) {
			if (!equal(i, col)) {
				if (conflicts(col, matrix[col], i, matrix[i]))
					return true;
			}
		}
		return false;
	}
	
	public boolean equal(short aCol, short bCol) {
		if (aCol == bCol && matrix[aCol] == matrix[bCol])
			return true;
		return false;
	}

	/**
	 * 
	 * @param amazon
	 * @return The row at which there is the least amount of conflicts
	 */
	public short minConflicts(short col) {
		ArrayList<Short> lista = new ArrayList<Short>();
		short conflicts = Short.MAX_VALUE;
		
		for (short i = 0; i < board; ++i) {
			if (i != matrix[col]) {
				
				short tmp = numConflicts(col, i);
				if (tmp <= conflicts) {
					if (tmp != conflicts) {
						lista.clear();
					}
					lista.add(i);
					conflicts = tmp;
				}
			}
		}
		return lista.get((short)(Math.random()*lista.size()));
	}
	

	public short numConflicts(short col, short newRow) {
		short conflict = 0;
		
		for (short i = 0; i < board; ++i) {
			if (matrix[i] != matrix[col]) {
				conflict += numConflicts(col, newRow, i, matrix[i]);
			}
			
		}
		return conflict;
	}
	
	
	boolean conflicts(short aCol, short aRow, short bCol, short bRow) {
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
	
	byte numConflicts(short aCol, short aRow, short bCol, short bRow) {
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
		for (short i = 0; i < board; ++i) {
			for (short j = 0; j < board; ++j) {
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
