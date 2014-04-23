package net.etrs.ram.bad_cessonais.entities.administration;

/**
 * Enumération des droits de l'utilisateur 
 *
 */
public enum Droit {
	PRESIDENT("President"),
	SECRETAIRE("Secrétaire"),
	ADHERENT("Adhérent"),
	ANIMATEUR("Adhérent Animateur"),
	PRINCIPAL("Animateur Principal");
	
	private String libelle;
	
	private Droit(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}