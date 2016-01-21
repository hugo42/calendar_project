package calendar.dao;

public class RepositoryManager {

	public RepositoryManager(){
		
	}
	
	public GuestDao getGuestManager(){
		return new GuestDao();
	}
	
	public RepositoryManager getRepository(String repClass) throws Exception{
		
		RepositoryManager rm = null;
		switch (repClass) {
			case "Guest":
				rm = new GuestDao();
				break;
//			case "Day":
//				rm = new DayDao();
//				break;
//			case "Purchase":
//				rm = new PurchaseDao();
//				break;
//			case "Diction":
//				rm = new DictionDao();
//				break;
//			case "Picture":
//				rm = new PictureDao();
//				break;
			default:
				break;
		}
		
		if(rm == null){
			throw new Exception("Ripository "+repClass+" does not Exists");
		}
		return rm;
	}
}
