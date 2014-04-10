package net.etrs.ram.bad_cessonais.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
	//@NamedQuery(name="findAllUtilisateur", query="SELECT a FROM Adherent a"),
	//@NamedQuery(name="findGroupeLikeName", query="SELECT gm FROM GroupeMusique gm WHERE gm.nom LIKE :nomGroupe")
		}
)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Utilisateur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(columnDefinition="VARCHAR(36)")
	private String id = UUID.randomUUID().toString();
	private String login;
	private String password;
	
	
	
}
