
public class DiaConstraint implements Constraint {

	@Override
	public boolean isValid(Amazon a, Amazon b) {
		if (Math.abs(a.getRow() - b.getRow()) != Math.abs(a.getCol() - b.getCol()))
			return true;
		return false;
	}
	
}