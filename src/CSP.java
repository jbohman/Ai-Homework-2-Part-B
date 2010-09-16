import java.util.*;

public class CSP {
    public short board;

    short matrix[];

    /**
     * Initialise the matrix with our amazons
     * 
     * @param board
     *            The board size
     */
    public CSP(short board) {
	this.board = board;

	matrix = new short[board];

	for (short i = 0; i < board; ++i) {
	    matrix[i] = (short) ((i * 3) % board);
	}
    }

    /**
     * Is the problem solved?
     * 
     * @return True if there is no conflicting amazons.
     */
    public boolean solution() {
	for (short i = 0; i < board; ++i) {
	    if (isConflicted(i))
		return false;
	}
	return true;
    }

    /**
     * Check if a amazon is in conflict.
     * 
     * @param col
     *            The column corresponding to the amazon
     * @return true if there is a conflict.
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

    /**
     * Used to check if two amazon columns are the same
     * 
     * @param aCol
     *            The ID of the first amazon
     * @param bCol
     *            The ID of the second amazon
     * @return true if the amazons are equal.
     */
    public boolean equal(short aCol, short bCol) {
	if (aCol == bCol && matrix[aCol] == matrix[bCol])
	    return true;
	return false;
    }

    /**
     * Gives the row with the smallest amount of conflicts for the amazon. The
     * return value is randomised against the rows with the same amount of
     * conflicts.
     * 
     * @param col
     *            The amazon
     * @return One of the rows with the smallest amount of conflicts
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
	return lista.get((short) (Math.random() * lista.size()));
    }

    /**
     * The number of conflicts for a given amazon at a given row.
     * 
     * @param col
     *            The ID of the amazon
     * @param newRow
     *            The row to try
     * @return The number of conflicts
     */
    public short numConflicts(short col, short newRow) {
	short conflict = 0;

	for (short i = 0; i < board; ++i) {
	    if (matrix[i] != matrix[col]) {
		conflict += numConflicts(col, newRow, i, matrix[i]);
	    }

	}
	return conflict;
    }

    /**
     * Returns true if two amazons conflict
     * 
     * @param aCol
     *            The column (ID) of the first amazon
     * @param aRow
     *            The row of the first amazon
     * @param bCol
     *            The column (ID) of the second amazon
     * @param bRow
     *            The row of the second amazon
     * @return true if they conflict in some way.
     */
    boolean conflicts(short aCol, short aRow, short bCol, short bRow) {
	// row
	if (aRow == bRow)
	    return true;
	// col
	if (aCol == bCol)
	    return true;
	// dia
	if (Math.abs(aRow - bRow) == Math.abs(aCol - bCol))
	    return true;
	// box
	if (Math.abs(aCol - bCol) <= 2 && Math.abs(aRow - bRow) <= 2)
	    return true;
	return false;
    }

    /**
     * Returns the number of times two amazons conflict
     * 
     * @param aCol
     *            The column (ID) of the first amazon
     * @param aRow
     *            The row of the first amazon
     * @param bCol
     *            The column (ID) of the second amazon
     * @param bRow
     *            The row of the second amazon
     * @return The number of conflicts between the amazons
     */
    byte numConflicts(short aCol, short aRow, short bCol, short bRow) {
	byte tmp = 0;
	// row
	if (aRow == bRow)
	    tmp++;
	// col
	if (aCol == bCol)
	    tmp++;
	// dia
	if (Math.abs(aRow - bRow) == Math.abs(aCol - bCol))
	    tmp++;
	// // box
	if (Math.abs(aCol - bCol) <= 2 && Math.abs(aRow - bRow) <= 2)
	    tmp++;
	return tmp;
    }

    /**
     * Format the output in an human readable way. NOTE that the rows and
     * columns might be swapped.
     */
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
