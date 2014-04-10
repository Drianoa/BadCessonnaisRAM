package net.etrs.ram.bad_cessonais.entities.gestion_adherents;

/**
 * Enumeration des différents sexes disponibles.
 * @author adrien.merly
 *
 */
public enum Sexe {
	HOMME("Homme"),
	FEMME("Femme");
	
	private String libelle;
	
	private Sexe(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
