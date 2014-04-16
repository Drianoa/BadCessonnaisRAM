package net.etrs.ram.bad_cessonais.entities.gestion_adherents;




/**
 * Enumeration des différentes pieces justificatives.
 * @author alexandre.auchart
 *
 */

public enum Justificatif {
	PHOTO_IDENTITE("Photo d'identité"),
	CERTIFICAT_MEDICAL("Certificat Médical"),
	NON_CONTRE_INDICATION_COMPETITION("Absence de contre indication à la compétition"),
	CHEQUE_LICENCE("Chèque de licence FCD"),
	CHEQUE_CLUB("Chèque d'adhésion au club");
	
	private String libelle;
	
	private Justificatif(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
