package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import calendar.business.Guest;

/**
 * Helper Hibernate pour les requetes de r�cup�ration des entit�s Guest
 */
public class GuestDao {
	
	public GuestDao(){
	}

	/**
	 * R�cup�re une entit� Guest en base par son id
	 * @param id L'id du guest recherch�
	 * @return Guest
	 */
	public Guest find(Integer id){
		
		Guest guest = null;
		try {
            guest = (Guest) HibernateFactory.getSession().get(Guest.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		return guest;
	}

	/**
	 * R�cup�re une entit� Guest en base par son email
	 * @param email Email du guest recherch�
	 * @return Guest
	 */
	public Guest findOneByEmail(String email){
		
		Guest guest = null;
		try {
            guest = (Guest) HibernateFactory.getSession().createCriteria(Guest.class)
            	    .add( Restrictions.like("email", email) )
            	    .uniqueResult();
        } catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		return guest;
	}
}
