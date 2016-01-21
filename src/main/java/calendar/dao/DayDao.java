package calendar.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import calendar.business.Day;
import calendar.business.Guest;

public class DayDao {

	private Session session = null;
	
	public DayDao(){
		
		SessionFactory sf = HibernateFactory.getFactory();
		this.session = sf.getCurrentSession();
		this.session.beginTransaction();
	}

	
	public List<Day> findAll(){
		
		List<Day> days = null;
		try {
			days = session.createCriteria(Day.class).list();
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		this.session.close();
		
		return days;
	}
}
