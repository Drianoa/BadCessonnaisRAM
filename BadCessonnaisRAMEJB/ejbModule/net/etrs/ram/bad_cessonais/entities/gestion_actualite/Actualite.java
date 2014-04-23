package net.etrs.ram.bad_cessonais.entities.gestion_actualite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.hibernate.validator.constraints.NotBlank;


@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of={"id"})
@NamedQueries({
	@NamedQuery(name="Actulite.findLast", query="SELECT a FROM Actualite a order by a.datePublication DESC"),
})

/**
 * Classe Actualité du site 
 *
 */
public class Actualite implements Serializable {
	//champs techniques
	
		@Id
		@Column(length=36)
		private String id;
		
		@Version
		private Long version;
		
		@NotBlank
		String titre;
		
		@Lob
		String texte;
		
		@Temporal(TemporalType.TIMESTAMP)
		Date datePublication = new Date();
		
}
