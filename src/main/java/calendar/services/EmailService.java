package calendar.services;

import javax.servlet.http.HttpServletResponse;

import calendar.business.Guest;
import calendar.dao.RepositoryManager;

/**
 * Service de test sur les email
 *
 */
public class EmailService {

	/**
	 * Test l'existance d'un guest par son email
	 * @param email Email du guest
	 * @return true si l'email existe sinon false
	 */
	public static boolean emailExists(String email){
		
		Guest guest = RepositoryManager.getGuestManager().findOneByEmail(email);
		if(guest == null) return false;
		
		return true;
	}
}
