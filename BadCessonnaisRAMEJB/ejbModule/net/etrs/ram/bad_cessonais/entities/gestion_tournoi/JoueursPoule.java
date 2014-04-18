package net.etrs.ram.bad_cessonais.entities.gestion_tournoi;

public enum JoueursPoule {
	JOUEURS3(3),
	JOUEURS4(4),
	JOUEURS5(5);
	
	private Integer joueurs;
	
	private String libelle;
	
	private JoueursPoule(Integer joueurs) {
		this.joueurs = joueurs;
		libelle = joueurs +"j / poule";
	}
	
	public String getLibelle(){
		return libelle;
	}
	
	public Integer getJoueurs() {
		return joueurs;
	}
}
