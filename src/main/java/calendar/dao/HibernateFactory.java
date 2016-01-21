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
	 * Récupère la session en cours (la crée si elle n'existe pas)
	 * Démarre une transaction si elle n'est pas déjà démarré
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
//	 * Récupère la session en cours avec la transaction démarrée
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
//	 * Récupère la session en cours (la crée si elle n'existe pas)
//	 * Démarre une transaction si elle n'est pas déjà démarré
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