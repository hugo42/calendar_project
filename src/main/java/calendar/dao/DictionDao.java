package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Diction;

public class DictionDao {
	
	public DictionDao(){
	}
	
	public Diction find(Integer id){
		
		Diction diction = null;
		try {
			diction = (Diction) HibernateFactory.getSession().get(Diction.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }

		return diction;
	}
}
