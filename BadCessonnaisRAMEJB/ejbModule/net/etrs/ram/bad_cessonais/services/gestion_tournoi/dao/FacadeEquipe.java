package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Equipe;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeMatch;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeEquipe extends AbstractFacade<Equipe> {
	
	/**
	 * Crée une équipe de 1 joueur pour les matchs simples.
	 * @param j
	 * @return
	 */
	public Equipe creerEquipe(Joueur j){
		Equipe e = newInstance();
		e.getJoueurs().add(j);
		create(e);
		return e;
	}
	
	/**
	 * Crée une équipe de 2 joueurs pour les matchs double.
	 * @param j1
	 * @param j2
	 * @return
	 */
	public Equipe creerEquipe(Joueur j1 ,Joueur j2){
		Equipe e = newInstance();
		e.getJoueurs().add(j1);
		e.getJoueurs().add(j2);
		e.setPourMatch(TypeMatch.DOUBLE);
		create(e);
		return e;
	}
	
	
	/**
	 * Renvoi les Equipes en fonction de leur {@link TypeMatch}.
	 * Cette méthode est utilisée pour l'initialisation des données de test
	 * 
	 * @param typeMatch
	 * @return
	 */
	public List<Equipe> listerEquipes(TypeMatch typeMatch){
		TypedQuery<Equipe> query = em.createNamedQuery("Equipe.findByTypeMatch",Equipe.class);
		query.setParameter("typeMatch", typeMatch);
		return query.getResultList();
		
	}
	
}
