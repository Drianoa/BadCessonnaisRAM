
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

@Entity
@NamedQueries(
		{
	@NamedQuery(name="findAllTournoi", query="SELECT t FROM Tournoi t"),
	//@NamedQuery(name="findGroupeLikeName", query="SELECT gm FROM GroupeMusique gm WHERE gm.nom LIKE :nomGroupe")
		}
)
public class Tournoi implements Serializable{


	private static final long serialVersionUID = 1L;

	
	//champs technique
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private long version;

	
	//@Pattern(regexp="[A-Za-z]*")
	private String nom;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateTournoi;
	
	
	
	public Tournoi(){}
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public Date getDateTournoi() {
		return dateTournoi;
	}
	public void setDateTournoi(Date dateTournoi) {
		this.dateTournoi = dateTournoi;
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
		Tournoi other = (Tournoi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
