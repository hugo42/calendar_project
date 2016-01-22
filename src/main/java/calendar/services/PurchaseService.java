package calendar.services;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.business.Day;
import calendar.business.Diction;
import calendar.business.Guest;
import calendar.business.Purchase;
import calendar.dao.EntityManager;
import calendar.dao.RepositoryManager;

public class PurchaseService {

	public static String purchaseDiction(String sDiction, String sDay, HttpSession session){
		
		String error = null;
		
		if( sDiction != null && sDay != null){
			
			Integer day = Integer.parseInt(sDay);
			Day pDay = RepositoryManager.getDayManager().find(day);
			
			Guest pGuest = (Guest) session.getAttribute("guest");
			if( pDay.getPurchase() == null){
				
				if(pGuest.getBalance() >= 2 ){

					Diction diction= new Diction();
					diction.setContent(sDiction);
					
					Purchase purchase = new Purchase();
					purchase.setDay(pDay);
					purchase.setFeature(diction);
					purchase.setGuest(pGuest);
					
					pGuest.setBalance(pGuest.getBalance() - 2);
					
					EntityManager.persist(diction);
					EntityManager.persist(purchase);
					
					EntityManager.flush();
				}else{
					error = "Vous n'avez pas les moyens d'acheter ce jour...";
				}
			}else{
				error = "Vous n'avez pas été assez rapide, un autre utilisateur vient d'acheter ce jour...";
			}
		}else{
			error = "Une erreur est survenue, veuillez réessayer plus tard...";
		}
		
		
		return error;
	}
}
