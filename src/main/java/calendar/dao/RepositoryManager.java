package calendar.dao;

public class RepositoryManager {
	
	public static GuestDao getGuestManager(){
		return new GuestDao();
	}
	
	public static DayDao getDayManager(){
		return new DayDao();
	}
	
	public static PictureDao getPictureManager(){
		return new PictureDao();
	}
	
	public static DictionDao getDictionManager(){
		return new DictionDao();
	}
}
