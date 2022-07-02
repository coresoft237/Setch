package setch.beans;

public class utilisateur {
	private int id;
	private String nom;
	private String login;
	private String password;
	private String statut;
	private int autorisation;
	
	public utilisateur(int id, String nom, String login, String password, String statut, int autorisation) {
		super();
		this.id = id;
		this.nom = nom;
		this.login = login;
		this.password = password;
		this.statut = statut;
		this.autorisation = autorisation;
	}
	public utilisateur(String nom, String login, String password, String statut, int autorisation) {
		super();
		this.nom = nom;
		this.login = login;
		this.password = password;
		this.statut = statut;
		this.autorisation = autorisation;
	}
	public utilisateur() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getAutorisation() {
		return autorisation;
	}
	public void setAutorisation(int autorisation) {
		this.autorisation = autorisation;
	}
	

}
