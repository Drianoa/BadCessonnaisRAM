package net.etrs.ram.bad_cessonnais.beans.authentification;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonnais.utils.SpringSecurityHelper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ManagedBean JSF qui permet de contrôler les action de login et logout des
 * utilisateurs.
 *
 * @author francois.robin
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{


	
	
    public String getUserLogin() 
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();

    }

    /**
     * permet de savoir si l'utilisateur est dans un rôle passé en paramètre.
     *
     * @param role
     * @return true si l'utilisateur possède le rôle, false sinon.
     */
    public boolean isInRole(String role) {
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	for(GrantedAuthority authority : authentication.getAuthorities())
    	{
    		if (authority.getAuthority().equalsIgnoreCase(role))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    public List <String> getRoles()
    {
    	List <String> roles = new ArrayList<>();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	for(GrantedAuthority authority : authentication.getAuthorities())
    	{    		
    			roles.add(authority.getAuthority());
    	}
    	return roles;
    }

    /**
     * lance la fonction de "login"
     *
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public String login() throws IOException, ServletException {
        SpringSecurityHelper.login();
        JsfUtils.sendMessage("Connexion OK !");
        return null;
    }

    /**
     * lance la fonction de "logout"
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String logout() throws ServletException, IOException {
        SpringSecurityHelper.logout();
        return null;
    }
}
