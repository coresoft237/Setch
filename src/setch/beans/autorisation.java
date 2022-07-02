package setch.beans;

public class autorisation {
	private int id;
	private String nom;
	private int user;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getuser() {
		return user;
	}
	public void setuser(int user) {
		this.user = user;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public autorisation(int id, String nom,int user) {
		super();
		this.id = id;
		this.nom = nom;
		this.user=user;

	}
	public autorisation(String nom,int user) {
		super();
		this.nom = nom;
		this.user=user;
	
	}
	public autorisation() {
		super();
	}

}
