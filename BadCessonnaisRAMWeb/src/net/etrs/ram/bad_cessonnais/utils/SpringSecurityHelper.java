package net.etrs.ram.bad_cessonnais.utils;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Classe utilitaire de lancement des fonctionnalit�s de login et logout 
 * de Spring Security.
 * 
 * @author francois.robin
 */
public class SpringSecurityHelper {

    private static final String SPRING_SECURITY_LOGIN = "/j_spring_security_check";
    private static final String SPRING_SECURITY_LOGOUT = "/j_spring_security_logout";

    private static RequestDispatcher getDispatcher(ExternalContext ctx, String url) {
        return ((ServletRequest) ctx.getRequest()).getRequestDispatcher(url);
    }

    /**
     * m�thode interne pour lancer un "forward" vers une URL depuis un contexte
     * JSF.
     *
     * @param url
     * @throws ServletException
     * @throws IOException
     */
    private static void doForward(String url) throws ServletException, IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = SpringSecurityHelper.getDispatcher(ctx, url);
        dispatcher.forward((ServletRequest) ctx.getRequest(), (ServletResponse) ctx.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * lance la fonction de "login" aupr�s du framework Spring Security.
     *
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static void login() throws IOException, ServletException {
        SpringSecurityHelper.doForward(SPRING_SECURITY_LOGIN);
    }

    /**
     * lance la fonction de "logout" auprès du framework Spring Security
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public static void logout() throws ServletException, IOException {
        SpringSecurityHelper.doForward(SPRING_SECURITY_LOGOUT);
    }
}
