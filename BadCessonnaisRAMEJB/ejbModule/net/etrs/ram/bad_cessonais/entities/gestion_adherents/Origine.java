package net.etrs.ram.bad_cessonais.entities.gestion_adherents;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration des différentes Origine disponibles.
 * @author auchart.alexandre
 *
 */
@AllArgsConstructor
public enum Origine {
	DEFENSE("Défense"),
	CIVIL("Civil");
	
	@Getter
	private String libelle;
	

	

}
