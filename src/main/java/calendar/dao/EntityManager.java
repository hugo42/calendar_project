package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManager {

	private Session session = null;
	
	public EntityManager(){
		
		SessionFactory sf = HibernateFactory.getFactory();
		
		this.session = sf.getCurrentSession();
		this.session.beginTransaction();
	}
	
	public void persist(Object entity){
		try{
			this.session.save(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
			this.session.getTransaction().rollback();
		}
	}
	
	public void flush(){
		try{
			this.session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
}
