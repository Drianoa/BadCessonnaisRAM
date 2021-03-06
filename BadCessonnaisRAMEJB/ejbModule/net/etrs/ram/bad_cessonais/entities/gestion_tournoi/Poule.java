package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of={"id"})
@NamedQueries({
	@NamedQuery(name="Tableau.countAll", query="SELECT count(t) FROM Tableau t")
	
})
/**
 * Classe des Poules
 *
 */
public class Poule {

	@Id
	@Column(length=36)
	String id;
	
	@Version
	Long version;
	
	@ManyToMany(cascade=CascadeType.ALL)
	List<Joueur> joueurs = new ArrayList<>();

	public void ajouterJoueur(Joueur joueur) {
		joueurs.add(joueur);
	}

	public void supprimerJoueur(Joueur joueur) {
		joueurs.remove(joueur);		
	}
	
	
}
