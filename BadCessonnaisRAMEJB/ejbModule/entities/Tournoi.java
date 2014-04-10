
package entities;

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
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
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
	@Column(columnDefinition="VARCHAR(36)")
	private String id = UUID.randomUUID().toString();
	@Version
	private long version;

	
	//@Pattern(regexp="[A-Za-z]*")
	@Column(columnDefinition="VARCHAR(40)")
	private String nom;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateTournoi;
	
	
	

	

}
