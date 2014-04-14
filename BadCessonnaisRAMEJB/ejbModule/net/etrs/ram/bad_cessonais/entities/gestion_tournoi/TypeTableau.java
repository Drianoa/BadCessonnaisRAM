package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

public enum TypeTableau {
	SIMPLE("Simple"),
	DOUBLE("Double");
	
	private String libelle;
	
	private TypeTableau(String libelle) {
		this.libelle =  libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
