package calendar.dao;

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

}