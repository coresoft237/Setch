package setch.beans;

public class personne {
	private int id;
	private String nom;
	private String phone;
	private String statut;
	private String titre;
	public personne() {
		super();
	}
	public personne(String nom,String phone,String statut,String titre) {
		super();
		this.nom = nom;
		this.phone=phone;
		this.statut=statut;
		this.titre=titre;
	}
	public personne(int id, String nom,String phone,String statut,String titre) {
		super();
		this.id = id;
		this.nom = nom;
		this.phone=phone;
		this.statut=statut;
		this.titre=titre;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatut() {
		return statut;
	}
	public void setTtitre(String titre) {
		this.titre = titre;
	}
	public String getTitre() {
		return titre;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
}
