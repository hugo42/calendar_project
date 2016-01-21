package calendar.dao;

public class RepositoryManager {

	public RepositoryManager(){
		
	}
	
	public GuestDao getGuestManager(){
		return new GuestDao();
	}
	
	public DayDao getDayManager(){
		return new DayDao();
	}
}
