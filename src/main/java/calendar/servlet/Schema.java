package calendar.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.business.Day;
import calendar.business.Guest;
import calendar.dao.EntityManager;

/**
 * Servlet implementation class SchemaUpdate
 */
@WebServlet("/create-data")
public class Schema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schema() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		EntityManager em = new EntityManager();
		
		/**
		 * Génération de Guest
		 */
		Guest g = new Guest();
		g.setName("Hugo Delphin-Poulat");
		g.setEmail("hugo@gmail.com");
		g.setPassword("123");
		em.persist(g);
		
		g = new Guest();
		g.setName("Olivier Dupont");
		g.setEmail("olivier@gmail.com");
		g.setPassword("123");
		em.persist(g);
		
		
		/**
		 * Génération des jours
		 * ATTENTION : Calendar.JANUARY = 0, Calendar.FEBRUARY = 1 etc...
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		while (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
			
			@SuppressWarnings({ "deprecation", "deprecation" })
			Date date = new Date(
					calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH)
			);
			Day day = new Day();
			day.setDayDate(date);
			System.out.println(day.getTextDate());
			em.persist(day);
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		em.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
