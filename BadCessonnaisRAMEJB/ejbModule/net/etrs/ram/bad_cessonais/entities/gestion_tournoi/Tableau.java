package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of={"id"})
@NamedQueries({
	@NamedQuery(name="Tableau.countAll", query="SELECT count(t) FROM Tableau t")
	
})
public class Tableau implements Serializable {

	@Id
	@Column(length=36)
	String id;
	
	@Version
	Long version;
	
	//@NotBlank(message="Saisisez le nom")
	String nom;
		
//	@NotNull
//	@Enumerated(EnumType.STRING)
//	TypeMatch typeMatch = TypeMatch.SIMPLE;
	
	@ManyToMany(cascade=CascadeType.ALL)
	List<Joueur> inscrits = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	JoueursPoule joueursPoule = JoueursPoule.JOUEURS4;
	
	Integer tempsMatch = 25;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="ID_TABLEAU")
	List<Poule> poules = new ArrayList<>();
	
	public Integer nbInscrits(){
		return inscrits.size();
	}

	public void ajouterPoule(Poule poule) {
		poules.add(poule);		
	}
}
