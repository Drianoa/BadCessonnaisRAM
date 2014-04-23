package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.ValidationException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeMatch;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.ServiceGestionTournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

/**
 * Gestion de la vue de creation de tounoi. 
 *
 */
@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
public class CreerTournoiBean {
	
	@Getter
	@Setter
	Tournoi nouveauTournoi;
	
	@Getter
	@Setter
	Tableau tableau;

	@Getter
	List<Tableau> tableauxList;
 	
	@EJB
	FacadeTournoi facadeTournoi;
	
	@EJB
	FacadeTableau facadeTableau;
	
	@EJB
	ServiceGestionTournoi serviceGestionTournoi;
	
	@PostConstruct
	public void init(){
		nouveauTournoi = facadeTournoi.newInstance();
		tableau = facadeTableau.newInstance();
		tableauxList = new ArrayList<>();
	}
	
	/**
	 * Retourne la liste des types de tableaux.
	 * @return
	 */
	public TypeMatch[] listeTypes(){
		return TypeMatch.values();
	}
	
	/**
	 * Ajoute un nouveau tableau à la liste des tableaux du tournoi.
	 */
	public void ajouterTableau(){
		tableauxList.add(tableau);
		tableau = facadeTableau.newInstance();
	}
	
	/**
	 * Determine si il faut afficher la liste des tableaux.
	 * @return
	 */
	public boolean afficherTableaux(){
		return !tableauxList.isEmpty();
	}
	
	
	/**
	 * Lance la création du tournoi avec ses tableaux.
	 * @throws Exception 
	 */
	public void validerCreation() throws Exception{
		
		try {
			if(tableauxList.isEmpty()){
				throw new Exception( "Vous devez saisir au moins un tableau");
			}else{
				Tournoi tournoiCree =serviceGestionTournoi.creerTournoi(nouveauTournoi, tableauxList);
				JsfUtils.putInFlashScope("tournoi", tournoiCree.getId());
			}
		} catch (Exception e) {
			JsfUtils.sendMessage("growl", e);
			throw e;
		}		
	}
	
	/**
	 * Supprime un tableau de la liste es tableaux.
	 * @param tableau
	 */
	public void supprimerTableau(Tableau tableau){
		tableauxList.remove(tableau);
	}
}
