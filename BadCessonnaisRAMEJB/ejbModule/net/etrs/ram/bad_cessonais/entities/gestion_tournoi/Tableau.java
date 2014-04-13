package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

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
	@NamedQuery(name="Tableau.countAll", query="SELECT count(t) FROM Tableau t")
	
})
public class Tableau implements Serializable {

	@Id
	@Column(length=36)
	String id = UUID.randomUUID().toString();
	@Version
	Long version;
	
	//@NotBlank(message="Saisisez le nom")
	String nom;
		
	@NotNull
	@Enumerated(EnumType.STRING)
	TypeTableau typeTableau = TypeTableau.SIMPLE;
}
