package calendar.business;

public class Picture extends Feature {

	private String source;
	
	public Picture(){
		super.setPrice(2);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
