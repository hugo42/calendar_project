package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Diction;

/**
 * Helper Hibernate pour les requetes de r�cup�ration des entit�s Diction
 */
public class DictionDao {
	
	public DictionDao(){
	}
	
	/**
	 * R�cup�re une entit� Diction en base par son id
	 * @param id Id du dicton recherch�
	 * @return
	 */
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
