package net.etrs.ram.bad_cessonais.entities.gestion_adherents;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

import net.etrs.ram.bad_cessonais.entities.Utilisateur;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@NamedQueries(
		{
	@NamedQuery(name="findAllAdherent", query="SELECT a FROM Adherent a"),
	//@NamedQuery(name="findGroupeLikeName", query="SELECT gm FROM GroupeMusique gm WHERE gm.nom LIKE :nomGroupe")
		}
)

public class Adherent extends Utilisateur implements Serializable{


	private static final long serialVersionUID = 1L;

	
	//champs techniques
	@Id
	@Column(length=36)
	private String id = UUID.randomUUID().toString();
	
	@Version
	private Long version;

	
	//@Pattern(regexp="[A-Za-z]*")
	@Column(length=40)
	private String nom;
	
	
	//@Pattern(regexp="[A-Za-z]*")
	@Column(length=40)
	private String prenom;
	
	@Pattern(regexp="[0-9]*")
	@Column(length=10)
	private String licenceFcd;
	
	@Pattern(regexp="[0-9]*")
	@Column(length=10)
	private String licenceFFBa;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	

	

}