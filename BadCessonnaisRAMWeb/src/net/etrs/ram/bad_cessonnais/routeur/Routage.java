package net.etrs.ram.bad_cessonnais.routeur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;



@ManagedBean
@ApplicationScoped
public class Routage {
//	private final static String REDIRECT = "?faces-redirect=true";
	private final static String REDIRECT = "";
	
	public  String accueil ()		{return "/test-layout.xhtml";}
	public  String creerTournoi()	{return "/pages/gestion-tournoi/creer-tournoi.xhtml" + REDIRECT;}
	public  String listerTournoi()	{return "/pages/gestion-tournoi/lister-tournoi.xhtml"  + REDIRECT;}
	
}
