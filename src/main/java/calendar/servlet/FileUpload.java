package calendar.servlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import calendar.business.Day;
import calendar.business.Guest;
import calendar.business.Picture;
import calendar.business.Purchase;
import calendar.dao.EntityManager;
import calendar.dao.RepositoryManager;

/**
 * Servlet implementation class FileUpload
 * Traite l'upload de fichier sur le serveur
 * Est mapp�e sur le path /fileupload et sert la vue update.jsp lorsque l'envoie de fichier est un succ�s
 * L'anotation @MultipartConfig set la taille max d'un fichier � 100ko
 */
@WebServlet(name = "fileupload", urlPatterns = { "/fileupload" } )
@MultipartConfig(maxFileSize = 102400,maxRequestSize = 1048576,fileSizeThreshold = 10240)
public class FileUpload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// G�n�ration d'un logger, plus utile maintenant
	private final static Logger LOGGER = Logger.getLogger(FileUpload.class.getCanonicalName());

	/**
	 * Redirige les requ�tes GET vers la vue upload.jsp (vue de confirmation).
	 */
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/upload.jsp").forward( request, response );
    }

	/**
	 * Redirige les requ�tes POST vers la m�thode processRequest() d'enregistrement de fichier.
	 */
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		processRequest(request, response);
	}
   
	/**
	 * Gestion de concurrence d'acc�s gr�ce au synchronized
	 * Traite l'upload de fichier vers l'application
	 * Pr�pare le fichier avant son �critue
	 * Remonte d'�ventuelles erreurs lors de l'�criture du fichier au travers d'un writer
	 * Flush l'entitymanager apr�s les transactions
	 * Et redirige vers la vue upload.jsp de confirmation
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected synchronized void processRequest(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
	    //response.setContentType("text/html;charset=UTF-8");

	    final String path = "/opt/tomcat/webapps/CalendarProject/resources/images";
	    final Part filePart = request.getPart("picture");
	    final String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();
	    
	    if (purchase(Integer.parseInt(request.getParameter("day")), path + File.separator + fileName, (Guest) request.getSession(true).getAttribute("guest"))){
	    	
	    	try {
		        out = new FileOutputStream(new File(path + File.separator
		                + fileName));
		        filecontent = filePart.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		    } catch (FileNotFoundException fne) {
		    	
		    	EntityManager.rollback();
		    	
		        writer.println("You either did not specify a file to upload or are "
		                + "trying to upload a file to a protected or nonexistent "
		                + "location.");
		        writer.println("<br/> ERROR: " + fne.getMessage());

		        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
		                new Object[]{fne.getMessage()});
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        if (writer != null) {
		            writer.close();
		        }
		        EntityManager.flush();
		        response.sendRedirect("main");
		    }
	    }	    
	}

	/**
	 * D�termine le nom du fichier uploader
	 * @param part
	 * @return Le nom du fichier ou null si il y a eu probl�me lors de la r�cup�ration
	 */
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	/**
	 * G�n�re un nouveau Purchase
	 * Lui affecte l'id de l'utilisateur logger
	 * Ainsi que l'id du fichier nouvellement upload�
	 * Et enfin l'id du jour concern� par l'achat
	 * G�re aussi la d�duction du co�t de l'achat au solde de l'utilisateur
	 * @param day
	 * @param file
	 * @param pGuest
	 * @return
	 */
	private boolean purchase(Integer day, String file, Guest pGuest){
		
		Day pDay = RepositoryManager.getDayManager().find(day);
		if( pDay.getPurchase() == null &&
				pGuest.getBalance() >= 5 ){
			
			Picture picture = new Picture();
			picture.setSource(file);
			
			Purchase purchase = new Purchase();
			
			purchase.setDay(pDay);
			purchase.setFeature(picture);
			purchase.setGuest(pGuest);
			
			pGuest.setBalance(pGuest.getBalance() - 5);
			
			EntityManager.persist(picture);
			EntityManager.persist(purchase);
				
			return true;
		}else{
			EntityManager.rollback();
			return false;
		}
	}
}