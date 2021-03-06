package net.etrs.ram.bad_cessonais.entities.gestion_adherents;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration des différents sexes disponibles.
 * @author adrien.merly
 *
 */
@AllArgsConstructor
public enum Sexe {
	HOMME("Homme"),
	FEMME("Femme");
	
	@Getter
	private String libelle;


}
