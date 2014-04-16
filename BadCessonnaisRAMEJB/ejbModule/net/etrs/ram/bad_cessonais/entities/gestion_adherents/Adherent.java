package net.etrs.ram.bad_cessonais.entities.gestion_adherents;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import net.etrs.ram.bad_cessonais.entities.administration.Droit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(of={"id"},callSuper=false)
@ToString
@NamedQueries(
		{
	@NamedQuery(name="findAllAdherent", query="SELECT a FROM Adherent a"),
	@NamedQuery(name="findAdherentLike", query="SELECT a FROM Adherent a WHERE a.nom LIKE :motCle or a.prenom LIKE :motCle or a.licenceFcd LIKE :motCle"),
	@NamedQuery(name="countAllAdherent", query="SELECT count(a) FROM Adherent a")
		}
)

public class Adherent implements Serializable{

	
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
	@Past
	private Date dateNaissance;

	private String lieuNaissance;
	
	
	private String adresse;
	
	@Pattern(regexp="[0-9]*")
	@Column(length=5)
	private String codePostal;
	
	private String ville;
	
	
	private String telephone;
	

	@Enumerated(EnumType.STRING)
	@ElementCollection
	private List<Justificatif> justificatif;
	
	@Enumerated(EnumType.STRING)
	Sexe sexe;
	
	@Enumerated(EnumType.STRING)
	Droit droit = Droit.ADHERENT;
	
	

	

}
