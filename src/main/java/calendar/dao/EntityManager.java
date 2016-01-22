package calendar.dao;

import org.hibernate.HibernateException;

public class EntityManager {

	public EntityManager(){
	}
	
	/**
	 * R�cup�re la session en cours avec la transaction d�marr�e
	 */
	public static void persist(Object entity){
		try{
			HibernateFactory.getSession().save(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
			EntityManager.rollback();
		}
	}

	/**
	 * R�cup�re la session en cours (la cr�e si elle n'existe pas)
	 * D�marre une transaction si elle n'est pas d�j� d�marr�
	 * 
	 * Commit les modifications de la transaction puis la termine
	 */
	public static void flush(){
		try{
			HibernateFactory.getSession().getTransaction().commit();
			HibernateFactory.getSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			EntityManager.rollback();
		}
	}
	
	public static void rollback(){
		HibernateFactory.getSession().getTransaction().rollback();
		HibernateFactory.getSession().close();
	}
}
