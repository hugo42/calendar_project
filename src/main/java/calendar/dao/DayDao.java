package calendar.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import calendar.business.Day;
import calendar.business.Guest;

public class DayDao {

	public DayDao(){
	}
	
	public Day find(Integer id){
		
		Day day = null;
		try {
            day = (Day) HibernateFactory.getSession().get(Day.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		return day;
	}

	
	public List<Day> findAll(){
		
		List<Day> days = null;
		try {
			days = HibernateFactory.getSession().createCriteria(Day.class).list();
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		return days;
	}
}
