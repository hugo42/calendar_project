package calendar.dao;

/**
 * Centralise la récupération des helpers de requêtes Hibernate
 * spécifiques à chaque entité
 *
 */
public class RepositoryManager {
	
	/**
	 * retourne l'helper de requêtes Hibernate pour les entités Guest
	 * @return GuestDao
	 */
	public static GuestDao getGuestManager(){
		return new GuestDao();
	}
	
	/**
	 * retourne l'helper de requêtes Hibernate pour les entités Day
	 * @return DayDao
	 */
	public static DayDao getDayManager(){
		return new DayDao();
	}
	
	/**
	 * retourne l'helper de requêtes Hibernate pour les entités Picture
	 * @return PictureDao
	 */
	public static PictureDao getPictureManager(){
		return new PictureDao();
	}
	
	/**
	 * retourne l'helper de requêtes Hibernate pour les entités Diction
	 * @return DictionDao
	 */
	public static DictionDao getDictionManager(){
		return new DictionDao();
	}
}
