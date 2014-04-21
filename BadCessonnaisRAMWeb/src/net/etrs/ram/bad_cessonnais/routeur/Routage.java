package net.etrs.ram.bad_cessonnais.routeur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ApplicationScoped
@URLMappings(mappings = {
		@URLMapping(id = "listeTournois", pattern = "/tournois", viewId = "/pages/gestion-tournoi/lister-tournoi.xhtml"),
		@URLMapping(id = "accueil", pattern = "/accueil", viewId = "/index.xhtml"),
		@URLMapping(id = "creerTournois", pattern = "/tournois/creer", viewId = "/pages/gestion-tournoi/creer-tournoi.xhtml"),
		@URLMapping(id = "inscriptionTournois", pattern = "/tournois/inscriptions", viewId = "/pages/gestion-tournoi/inscription-tournoi.xhtml"),
		@URLMapping(id = "modificationDroits", pattern = "/droits/modification", viewId = "/pages/administration/modifier-droits.xhtml"),
		@URLMapping(id = "listeDroits", pattern = "/droits", viewId = "/pages/administration/lister-droits.xhtml"),
		@URLMapping(id = "listerDroitAdherent", pattern = "/droits", viewId = "/pages/administration/lister-droits.xhtml"),
		@URLMapping(id = "gererPoules", pattern = "/tournois/poules", viewId = "/pages/gestion-tournoi/gerer-poules-tournoi.xhtml"),
		@URLMapping(id = "listerAdherent", pattern = "/adherents", viewId = "/pages/gestion-adherent/lister-adherent.xhtml"),
		@URLMapping(id = "creerAdherent", pattern = "/adherents/creer", viewId = "/pages/gestion-adherent/creer-adherent.xhtml"),
		
		
		
})
public class Routage {
	private final static String REDIRECT = "?faces-redirect=true";
//	private final static String REDIRECT = "";
	
	public  String accueil ()		{return "/index.xhtml" + REDIRECT;}
	public  String creerTournoi()	{return "/pages/gestion-tournoi/creer-tournoi.xhtml" + REDIRECT;}
	public  String listerTournoi()	{return "/pages/gestion-tournoi/lister-tournoi.xhtml" + REDIRECT;}
	public  String inscritpionTournoi()	{return "/pages/gestion-tournoi/inscription-tournoi.xhtml"  + REDIRECT;}
	public  String modifierDroits()	{return "/pages/administration/modifier-droits.xhtml"  + REDIRECT;}
	public  String listerDroits()	{return "/pages/administration/lister-droits.xhtml"  + REDIRECT;}
	public  String gererPoules()	{return "/pages/gestion-tournoi/gerer-poules-tournoi.xhtml"  + REDIRECT;}

	
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
