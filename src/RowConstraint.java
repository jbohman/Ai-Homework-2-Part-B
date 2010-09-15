
public class RowConstraint implements Constraint {

	@Override
	public boolean isValid(Amazon a, Amazon b) {
		if (a.getRow() != b.getRow())
			return true;
		return false;
	}
	
}
