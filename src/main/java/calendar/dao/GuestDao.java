package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import calendar.business.Guest;

/**
 * Helper Hibernate pour les requetes de récupération des entités Guest
 */
public class GuestDao {
	
	public GuestDao(){
	}

	/**
	 * Récupère une entité Guest en base par son id
	 * @param id
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
	 * Récupère une entité Guest en base par son email
	 * @param id
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
