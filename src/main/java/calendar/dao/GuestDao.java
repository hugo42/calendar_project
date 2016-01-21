package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import calendar.business.Guest;

public class GuestDao {
	
	private Session session = null;
	
	public GuestDao(){
		
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		this.session = sf.getCurrentSession();
		this.session.beginTransaction();
	}

	
	public Guest find(Integer id){
		
		Guest guest = null;
		try {
            guest = (Guest) session.get(Guest.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		this.session.close();
		return guest;
	}

	public Guest findOneByEmail(String email){
		
		Guest guest = null;
		try {
            guest = (Guest) this.session.createCriteria(Guest.class)
            	    .add( Restrictions.like("email", email) )
            	    .uniqueResult();
        } catch (HibernateException e) {
        	e.printStackTrace();
        }
		
//		this.session.getTransaction().commit();
		this.session.close();
		return guest;
	}
}
