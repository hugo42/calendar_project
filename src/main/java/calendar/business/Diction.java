package calendar.business;

public class Diction extends Feature {

	private String content;

	public Diction(){
		super.setPrice(2);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
