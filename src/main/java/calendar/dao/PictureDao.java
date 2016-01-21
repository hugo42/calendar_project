package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Picture;

public class PictureDao {

	public PictureDao(){
	}
	
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
