public class MagnitudeFilter implements Filter{
	private double minMag;
	private double maxMag;
	
	public MagnitudeFilter(double min, double max) {
		this.minMag = min;
		this.maxMag = max;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "magnitudeFilter";
	}
	
	
}
