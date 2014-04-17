
package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.hibernate.validator.constraints.NotBlank;



/**
 * Classe représentant un Tournoi.
 * @author adrien.merly
 *
 */
@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of={"id"})
//@NamedQueries(
//		{
////	@NamedQuery(name="findAllTournoi", query="SELECT t FROM Tournoi t"),
////	@NamedQuery(name="countAllTournoi", query="SELECT count(t) FROM Tournoi as t")
//		}
//)
public class Tournoi implements Serializable{

	//champs technique
	@Id
	@Column(length=36)
	String id = UUID.randomUUID().toString();
	@Version
	Long version;

	@NotBlank(message="Saisisez le nom")
	@Column(length=40)
	String nom;
	
	@Temporal(TemporalType.DATE)
	Date dateTournoi;
	
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="TOURNOI_ID")
	List<Tableau> lstTableaux = new ArrayList<>();

}
