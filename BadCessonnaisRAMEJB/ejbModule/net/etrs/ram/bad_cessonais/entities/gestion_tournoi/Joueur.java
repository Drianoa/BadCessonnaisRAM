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
	@NamedQuery(name="Joueur.findByLicenceAuto", query="SELECT j FROM Joueur j WHERE j.licenceFcd like CONCAT(:licenceFcd, '%') ORDER by j.licenceFcd ASC")
	
})

public class Joueur {
	@Id
	@Column(length=36)
	String id = UUID.randomUUID().toString();
	@Version
	Long version;
	
	@NotBlank
	String licenceFcd;
	
	@NotBlank
	String nom;
	
	@NotBlank
	String prenom;
	
	Integer anneeNaissance;
	
	String club;
	
	
}
