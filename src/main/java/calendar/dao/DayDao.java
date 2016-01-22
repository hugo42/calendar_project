package calendar.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Property;

import calendar.business.Day;

/**
 * Helper Hibernate pour les requetes de r�cup�ration des entit�s Day
 */
public class DayDao {

	public DayDao(){
	}
	
	/**
	 * R�cup�re une entit� Day en base par son id
	 * @param id Id du jour recherch�
	 * @return Day
	 */
	public Day find(Integer id){
		
		Day day = null;
		try {
            day = (Day) HibernateFactory.getSession().get(Day.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		return day;
	}

	/**
	 * R�cup�re toutes les entit�s Day en base
	 * @return
	 */
	public List<Day> findAll(){
		
		List<Day> days = null;
		try {
			days = HibernateFactory.getSession()
					.createCriteria(Day.class)
					.addOrder( Property.forName("id").asc() )
					.list();
			
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		return days;
	}
}
