package calendar.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import calendar.business.Picture;

public class PictureDao {

	private Session session = null;
	
	public PictureDao(){
		
		SessionFactory sf = HibernateFactory.getFactory();
		this.session = sf.getCurrentSession();
		this.session.beginTransaction();
	}
	
	public Picture find(Integer id){
		
		Picture picture = null;
		try {
			picture = (Picture) session.get(Picture.class, id);
		} catch (HibernateException e) {
        	e.printStackTrace();
        }
		
		this.session.close();
		return picture;
	}
}
