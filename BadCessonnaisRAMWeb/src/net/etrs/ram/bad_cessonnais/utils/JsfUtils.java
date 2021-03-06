package net.etrs.ram.bad_cessonnais.utils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

/**
 * Outillage générique JSF.
 *
 * @author francois.robin
 */
public class JsfUtils
{

    /**
     * Envoie un message JSF pour l'IHM. Classe : INFO
     *
     * @param message
     */
    public static void sendMessage(String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    
    public static void sendMessage(String composantId, Exception ex)
    {
        FacesContext.getCurrentInstance().addMessage(composantId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
    }
    
    /**
     * 
     * Envoi d'un message sur un composant
     * @param composantId nom du composant
     * @param s Severity 
     * @param title titre du message
     * @param message contenu du message
     */
    public static void sendMessage(String composantId, Severity s,String title,String message)
    {
        FacesContext.getCurrentInstance().addMessage(composantId, new FacesMessage(s,title,message));
    }

    
    /**
     * Envoie le message d'une exception à l'IHM. Classe : ERROR
     *
     * @param ex
     */
    public static void sendMessage(Exception ex)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
    }

    /**
     * Envoie un message de classe INFO à un composant JSF identifié par son "id".
     *
     * @param composantId
     * @param message
     */
    public static void sendMessage(String composantId, String message)
    {
        FacesContext.getCurrentInstance().addMessage(composantId, new FacesMessage(message));
    }

    /**
     * Dépose une instance d'objet en fonction d'une clé (String) dans le scope FLASH de JSF 2.
     *
     * @param key
     * @param data
     */
    public static void putInFlashScope(String key, Object data)
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
        session.setAttribute(key, data);
    }

    /**
     * Récupère l'instance d'un objet en fonction d'une clé (String) dans le scope FLASH de JSF 2.
     *
     * @param key
     * @return
     */
    public static Object getFromFlashScope(String key)
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
        return session.getAttribute(key);
    }
}
