package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

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
	@NamedQuery(name="Joueur.findByLicenceAuto", query="SELECT jou FROM Joueur jou where jou.id not in (SELECT j.id FROM Tableau t JOIN t.inscrits j Where t = :tableauActif) AND jou.licenceFcd like CONCAT(:licenceFcd, '%')  ORDER by jou.licenceFcd ASC"),
	@NamedQuery(name="Joueur.findByLicence", query="SELECT j FROM Joueur j WHERE j.licenceFcd = :licenceFcd"),
	
})

public class Joueur {
	@Id
	@Column(length=36)
	String id;
	@Version
	Long version;
	
	@NotBlank
	@Column(unique=true)
	String licenceFcd;
	
	@NotBlank
	String nom;
	
	@NotBlank
	String prenom;
	
	Integer anneeNaissance;
	
	String club;
	
	
}
