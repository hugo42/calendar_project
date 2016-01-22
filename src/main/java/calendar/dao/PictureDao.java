package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Picture;

/**
 * Helper Hibernate pour les requetes de récupération des entités Picture
 */
public class PictureDao {

	public PictureDao(){
	}
	
	/**
	 * /**
	 * Récupère une entité Picture en base par son id
	 * @param id Id de l'image recherchée
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
