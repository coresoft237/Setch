package setch.beans;

public class service {
	private int id;
	private int user;
	private String nom;
	private String statut;
	public service() {
		super();
	}
	public service(int user, String nom, String statut) {
		super();
		this.user = user;
		this.nom = nom;
		this.statut = statut;
	}
	public service(int id, int user, String nom, String statut) {
		super();
		this.id = id;
		this.user = user;
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
