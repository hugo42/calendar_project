package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

	private static SessionFactory instance;

	private HibernateFactory() {
	}

	public synchronized static SessionFactory getFactory() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return instance;
	}
	
	/**
	 * R�cup�re la session en cours (la cr�e si elle n'existe pas)
	 * D�marre une transaction si elle n'est pas d�j� d�marr�
	 * 
	 * (la session se termine au .flush())
	 * @return la session
	 */
	public static Session getSession(){
		Session session = getFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
//	/**
//	 * R�cup�re la session en cours avec la transaction d�marr�e
//	 */
//	public static void persist(Object entity){
//		try{
//			HibernateFactory.getSession().save(entity);
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			HibernateFactory.getSession().getTransaction().rollback();
//		}
//	}
//
//	/**
//	 * R�cup�re la session en cours (la cr�e si elle n'existe pas)
//	 * D�marre une transaction si elle n'est pas d�j� d�marr�
//	 * 
//	 * Commit les modifications de la transaction puis la termine
//	 */
//	public static void flush(){
//		try{
//			HibernateFactory.getSession().getTransaction().commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			HibernateFactory.getSession().getTransaction().rollback();
//		}
//	}
}