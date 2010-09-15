
public class DiaConstraint implements Constraint {

	@Override
	public boolean isValid(Amazon a, Amazon b) {
		double diaA = (a.getRow()+1) / (a.getCol()+1);
		double diaB = (b.getRow()+1) / (b.getCol()+1);
		if (diaA != diaB)
			return true;
		return false;
	}
	
}