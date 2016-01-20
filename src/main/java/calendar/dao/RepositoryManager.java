package calendar.dao;

public class RepositoryManager {

	public RepositoryManager(){
		
	}
	
	public Object getRepository(String repClass) throws Exception{
		
		Object object = null;
		switch (repClass) {
			case "Guest":
				new GuestDao();
				break;
			case "Day":
				new DayDao();
				break;
			case "Purchase":
				new PurchaseDao();
				break;
			case "Diction":
				new DictionDao();
				break;
			case "Picture":
				new PictureDao();
				break;
			default:
				break;
		}
		
		if(object == null){
			throw new Exception("Ripository "+repClass+" does not Exists");
		}
		return object;
	}
}
