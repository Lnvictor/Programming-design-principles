
public class PhraseFilter implements Filter{
	private String phrase;
	private String where;
	
	public PhraseFilter(String phrase, String where) {
		this.phrase = phrase;
		this.where = where;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		if (this.where.equals("start") && qe.getInfo().indexOf(phrase) == 0) {
			return true;
		}
		if(this.where.equals("end") && qe.getInfo().indexOf(phrase) ==
				qe.getInfo().length() - phrase.length()) {
			
			return true;
		}
		if(this.where.equals("any") && qe.getInfo().indexOf(phrase) != -1) {
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "PhraseFilter";
	}

}
