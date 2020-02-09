import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter{
	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		this.filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		for(Filter f: this.filters) {
			if(!f.satisfies(qe)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		String result = "";
		
		for (Filter f : filters) {
			result += f.getName() + ", ";
		}
		
		return result.substring(0, result.length() - 2);
	}
}
