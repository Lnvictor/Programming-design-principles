
public class DistanceFilter implements Filter{
	private Location loc;
	private double maxDistance;
	
	public DistanceFilter(Location loc, double maxDistance) {
		this.loc = loc;
		this.maxDistance = maxDistance;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		
		return qe.getLocation().distanceTo(loc) < maxDistance;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DistanceFilter";
	}

}
