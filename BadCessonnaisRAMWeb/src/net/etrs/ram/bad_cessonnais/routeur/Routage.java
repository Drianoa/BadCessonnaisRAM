package net.etrs.ram.bad_cessonnais.routeur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class Routage {
//	private final static String REDIRECT = "?faces-redirect=true";
	private final static String REDIRECT = "";
	
	public  String accueil ()		{return "/test-layout.xhtml";}
	public  String creerTournoi()	{return "/pages/gestion-tournoi/creer-tournoi.xhtml" + REDIRECT;}
	public  String listerTournoi()	{return "/pages/gestion-tournoi/lister-tournoi.xhtml"  + REDIRECT;}
	public  String inscritpionTournoi()	{return "/pages/gestion-tournoi/inscription-tournoi.xhtml"  + REDIRECT;}
	public  String modifierDroits()	{return "/pages/administration/modifier-droits.xhtml"  + REDIRECT;}
	public  String listerDroits()	{return "/pages/administration/lister-droits.xhtml"  + REDIRECT;}
	
	
	/**
	 * Routage pour l'adhérent
	 */
	public  String listerAdherent()	{return "/pages/gestion-adherent/lister-adherent.xhtml" + REDIRECT;}
	public  String creerAdherent()	{return "/pages/gestion-adherent/creer-adherent.xhtml" + REDIRECT;}
	
	
	/**
	 * Routage pour l'administration
	 */
	
	public  String listerDroitAdherent()	{return "/pages/administration/lister-droits.xhtml" + REDIRECT;}
	//public  String configurerSite()			{return "/pages/administration/configurer-site.xhtml" + REDIRECT;}
	
}
