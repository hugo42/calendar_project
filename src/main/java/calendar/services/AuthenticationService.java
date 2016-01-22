package calendar.services;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.business.Guest;
import calendar.dao.EntityManager;
import calendar.dao.RepositoryManager;

/**
 * Service qui gère l'authentification (connexion et inscription)
 *
 */
public class AuthenticationService {

	/**
	 * Tente de connecter l'utilisateur
	 * Success : Connection l'utilisateur
	 * @param email Email de l'utilisateur
	 * @param password Mot de passe de l'utilisateur
	 * @param session session HTTP de l'utilisateur sur le site
	 * @return Success : null, Error : l'erreur
	 */
	public static String signin(String email, String password, HttpSession session){
		
		String error = null;
		
		if(email != null && password != null){
			
			Guest guest = RepositoryManager.getGuestManager().findOneByEmail(email);
			if(guest != null){
				
				if(guest.getPassword().trim().equals(password.trim())){
					session.setAttribute("guest", guest);
					session.setMaxInactiveInterval(100*60);
				}else{
					error = "Mot de passe incorrect...";
				}
			}else{
				error = "Cette adresse mail ne correspond à aucun utilisateur...";
			}
			
		}else{
			error = "Une erreur est survenue, veuillez réessayer...";
		}
		
		return error;
	}
	
	/**
	 * Tente de créer un nouvelle utilisateur
	 * Success : Connection l'utilisateur
	 * @param name Nom de l'utilisateur
	 * @param email Email de l'utilisateur
	 * @param password Mot de passe de l'utilisateur
	 * @param passwordConfirm Confirmation du mot de passe
	 * @param session session HTTP de l'utilisateur sur le site
	 * @return Success : null, Error : l'erreur
	 */
	public static String signup(String name, String email, String password, String passwordConfirm, HttpSession session){
		
		String error = null;		
		
		if(name != null && email != null && password != null && passwordConfirm != null){
			
			if(password.trim().equals(passwordConfirm.trim())){
				
				Guest g = RepositoryManager.getGuestManager().findOneByEmail(email);
				if(g == null){
					
					Guest guest = new Guest();
					guest.setName(name);
					guest.setEmail(email);
					guest.setPassword(password);
					EntityManager.persist(guest);
					EntityManager.flush();
					
					error = AuthenticationService.signin(email, passwordConfirm, session);
					
				}else{
					error = "Cette adresse mail est déjà utilisée par un autre utilisateur...";
				}
			}else{
				error = "La confirmation du mot de passe n'est pas correcte...";
			}
		}else{
			error = "Une erreur est survenue, veuillez réessayer...";
		}
		
		return error;
	}
	
	/**
	 * Déconnecte l'utilisateur
	 * @param session session HTTP de l'utilisateur sur le site
	 */
	public static void logout(HttpSession session){
		
		session.removeAttribute("guest");
	}
	
	/**
	 * Test si l'utilisateur est connecté
	 * @param session session HTTP de l'utilisateur sur le site
	 */
	public static boolean isConnected(HttpSession session){
		
		if(session.getAttribute("guest") != null){
			
			try {
				Guest guest = (Guest) session.getAttribute("guest");
				Guest guestExists = RepositoryManager.getGuestManager().find(guest.getId());
				if(guestExists != null){
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}
