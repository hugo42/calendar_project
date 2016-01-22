package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Picture;

/**
 * Helper Hibernate pour les requetes de r�cup�ration des entit�s Picture
 */
public class PictureDao {

	public PictureDao(){
	}
	
	/**
	 * /**
	 * R�cup�re une entit� Picture en base par son id
	 * @param id Id de l'image recherch�e
	 * @return Picture
	 */
	public Picture find(Integer id){
		
		Picture picture = null;
		try {
			picture = (Picture) HibernateFactory.getSession().get(Picture.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		return picture;
	}
}
