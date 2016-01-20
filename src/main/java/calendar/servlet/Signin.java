package calendar.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import calendar.business.Guest;

/**
 * Servlet implementation class Signin
 */
@WebServlet(name = "signin", urlPatterns = { "/signin" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = null;
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		
		session = sf.getCurrentSession();
		session.beginTransaction();
		
		try {
//			Création d'un Guest
			Guest g = new Guest();
			g.setName("Olivier Dupont");
			g.setEmail("olivier@gmail.com");
			g.setPassword("123");
			session.save(g);
			
//			Création des Day
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1); 
			int myMonth=calendar.get(Calendar.MONTH);

			while (myMonth==calendar.get(Calendar.MONTH)) {
			  System.out.print(calendar);
			  calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
//			Date date = new Date();
//			Day day = new Day();
//			day.setDayDate();
//			session.save(day);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
