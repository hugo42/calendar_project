package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton de sessionFactory d'hibernate
 */
public class HibernateFactory {

	private static SessionFactory instance;

	private HibernateFactory() {
	}

	/**
	 * Retourne l'instance unique de la sessionFactory d'hibernate
	 * @return SessionFactory
	 */
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
	 * @return Session
	 */
	public static Session getSession(){
		Session session = getFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
}