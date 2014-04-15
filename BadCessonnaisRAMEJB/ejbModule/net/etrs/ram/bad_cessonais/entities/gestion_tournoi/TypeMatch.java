package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

public enum TypeMatch {
	SIMPLE("Simple"),
	DOUBLE("Double");
	
	private String libelle;
	
	private TypeMatch(String libelle) {
		this.libelle =  libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
