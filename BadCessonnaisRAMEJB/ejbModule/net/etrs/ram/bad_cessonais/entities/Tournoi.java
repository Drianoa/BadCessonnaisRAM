
package net.etrs.ram.bad_cessonais.entities;

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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
@NamedQueries(
		{
	@NamedQuery(name="findAllTournoi", query="SELECT t FROM Tournoi t"),
	@NamedQuery(name="countAllTournoi", query="SELECT count(t) FROM Tournoi as t")
		}
)
public class Tournoi implements Serializable{

	//champs technique
	@Id
	@Column(length=36)
	String id = UUID.randomUUID().toString();
	@Version
	Long version;

	@Column(length=40)
	String nom;
	
	
	@Temporal(TemporalType.DATE)
	Date dateTournoi;

}
