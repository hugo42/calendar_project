package calendar.dao;

import org.hibernate.HibernateException;

/**
 * Centralise la r�cup�ration des helpers de gestion d'entit� Hibernate
 * Permet de persist une entit�.
 * Permet de commit la transaction hibernate en cours.
 * Fait un rollback sur la transaction en cas d'erreur.
 *
 */
public class EntityManager {

	public EntityManager(){
	}
	
	/**
	 * Persist l'entit� pass�e en param�tre dans la trasaction hibernate en cours
	 * Fait un rollback sur la transaction en cas d'erreur.
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
	 * Commit les modifications perist�es dans la transaction hibernate en cours
	 * puis termine la session en cours
	 * Fait un rollback sur la transaction en cas d'erreur.
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
	
	/**
	 * Annule la transactionhibernate en cours (rollback)
	 * puis ferme la session hibernate en cours
	 */
	public static void rollback(){
		HibernateFactory.getSession().getTransaction().rollback();
		HibernateFactory.getSession().close();
	}
}
