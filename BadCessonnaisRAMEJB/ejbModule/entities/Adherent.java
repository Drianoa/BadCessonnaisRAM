package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

@Entity
@NamedQueries(
		{
	@NamedQuery(name="findAllAdherent", query="SELECT a FROM Adherent a"),
	//@NamedQuery(name="findGroupeLikeName", query="SELECT gm FROM GroupeMusique gm WHERE gm.nom LIKE :nomGroupe")
		}
)
public class Adherent implements Serializable{


	private static final long serialVersionUID = 1L;

	
	//champs tehcnique
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private long version;

	
	//@Pattern(regexp="[A-Za-z]*")
	private String nom;
	//@Pattern(regexp="[A-Za-z]*")
	private String prenom;
	
	@Pattern(regexp="[0-9]*")
	private int licenceFcd;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	
	
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getLicenceFcd() {
		return licenceFcd;
	}
	public void setLicenceFcd(int licenceFcd) {
		this.licenceFcd = licenceFcd;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public Long getId() {
		return id;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adherent other = (Adherent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
