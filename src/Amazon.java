
public class Amazon {
	private int row;
	private int col;
	public Amazon(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public String toString() {
		return row + ", " + col;
	}
}
