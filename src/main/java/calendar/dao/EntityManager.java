package calendar.dao;

import org.hibernate.HibernateException;

public class EntityManager {

	public EntityManager(){
	}
	
	/**
	 * Récupère la session en cours avec la transaction démarrée
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
	 * Récupère la session en cours (la crée si elle n'existe pas)
	 * Démarre une transaction si elle n'est pas déjà démarré
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
