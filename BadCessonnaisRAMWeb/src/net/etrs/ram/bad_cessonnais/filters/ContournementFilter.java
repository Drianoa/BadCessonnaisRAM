package net.etrs.ram.bad_cessonnais.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.resource.spi.SecurityException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.java.Log;

/**
 * Filtre de contounement ..
 */
@WebFilter("/*")
@Log
public class ContournementFilter implements Filter {

	 private final static String FACES_RESOURCE_URL = "javax.faces.resource";
     private final static String LOCATION_KEY = "ln";
     private final static String DOT_DOT = "..";

     
    /**
     * Default constructor. 
     */
    public ContournementFilter() { 
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	        try {
	             HttpServletRequest path = (HttpServletRequest) request; // this is a downcast (which is bad usually) but we can safely do it in this file
	             
	             // Check for "dot dot" path traversal
	             // ----------------------------------
	             // There's a vulnerability in JSF which allows for hackers to traverse the directory
	             // structure. This vulnerability is typical performed by something like:
	             //     project-name/javax.faces.resources/stylesheet.css.jsf?ln=\..\..\..\..\sensitive.txt%00.html
	             // What happens is that the ".jsf" sends the request thru the JSF servlet where the ".jsf"
	             // gets stripped (resulting in a regular resource request: stylesheet.css). But then the "ln="
	             // confuses the application by not only traversing the path (with /../../) but there's a
	             // "%00.html" which tricks the system to load/display the file.
	             if(path.getRequestURL().indexOf(FACES_RESOURCE_URL) >= 0) {
	                 Enumeration<String> e = request.getParameterNames();
	                 while(e.hasMoreElements()) {
	                      String key = (String) e.nextElement();
	                      if(key.indexOf(LOCATION_KEY) >= 0) {
	                           String value = request.getParameter(key);
	                           if(value.indexOf(DOT_DOT) >= 0) {
	                                log.severe("Detected a dot dot pattern in an \"" + LOCATION_KEY + "\" parameter. The value is: " + value);
	                                throw new SecurityException("Tentative d'accès non-autorisée détéctée");
	                           }
	                      }
	                 }
	             }
	        }
	        catch(Exception e) {
	            throw new ServletException(e.getMessage());
	        }
	        
	        // Call the next filter (continue request processing)
	         chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
