
public class BoxConstraint implements Constraint {

	@Override
	public boolean isValid(Amazon a, Amazon b) {
		int diffCol = a.getCol() - b.getCol();
		int diffRow = a.getRow() - b.getRow();
		if (Math.abs(diffCol) > 2  && Math.abs(diffRow) > 2) 
			return true;
		return false;			
	}

}
