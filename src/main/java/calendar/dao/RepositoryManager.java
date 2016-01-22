package calendar.dao;

/**
 * Centralise la r�cup�ration des helpers de requ�tes Hibernate
 * sp�cifiques � chaque entit�
 *
 */
public class RepositoryManager {
	
	/**
	 * retourne l'helper de requ�tes Hibernate pour les entit�s Guest
	 * @return GuestDao
	 */
	public static GuestDao getGuestManager(){
		return new GuestDao();
	}
	
	/**
	 * retourne l'helper de requ�tes Hibernate pour les entit�s Day
	 * @return DayDao
	 */
	public static DayDao getDayManager(){
		return new DayDao();
	}
	
	/**
	 * retourne l'helper de requ�tes Hibernate pour les entit�s Picture
	 * @return PictureDao
	 */
	public static PictureDao getPictureManager(){
		return new PictureDao();
	}
	
	/**
	 * retourne l'helper de requ�tes Hibernate pour les entit�s Diction
	 * @return DictionDao
	 */
	public static DictionDao getDictionManager(){
		return new DictionDao();
	}
}
