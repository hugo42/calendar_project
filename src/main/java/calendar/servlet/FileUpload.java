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
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet(name = "fileupload", urlPatterns = { "/fileupload" } )
@MultipartConfig(maxFileSize = 102400,maxRequestSize = 1048576,fileSizeThreshold = 10240)
public class FileUpload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(FileUpload.class.getCanonicalName());

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'envoi de fichiers */
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/upload.jsp").forward( request, response );
    }

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void processRequest(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    //final String abs_path = "/opt/tomcat/webapps/";
	    //final String dyn_path = "\\WEB-INF\\uploads";
	    //final String path = abs_path + dyn_path;
	    final String path = "/opt/tomcat/webapps/CalendarProject/resources/images/";
	    final Part filePart = request.getPart("picture");
	    final String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        doGet(request, response);
	    } catch (FileNotFoundException fne) {
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
	    }
	}

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
}