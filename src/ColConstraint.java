
public class ColConstraint implements Constraint {

	@Override
	public boolean isValid(Amazon a, Amazon b) {
		if (a.getCol() != b.getCol())
			return true;
		return false;
	}
	
}
