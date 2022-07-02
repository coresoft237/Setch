package setch.beans;

public class articles {
	private int id;
	private int user;
	private int idfamille;
	private String nom;
	private String statut;
	public articles() {
		super();
	}
	public articles(int user, int idfamille, String nom, String statut) {
		super();
		this.user = user;
		this.idfamille = idfamille;
		this.nom = nom;
		
		this.statut = statut;
	}
	public articles(int id, int user, int idfamille, String nom, String statut) {
		super();
		this.id = id;
		this.user = user;
		this.idfamille = idfamille;
		this.nom = nom;
		this.statut = statut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getIdfamille() {
		return idfamille;
	}
	public void setIdfamille(int idfamille) {
		this.idfamille = idfamille;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

}
