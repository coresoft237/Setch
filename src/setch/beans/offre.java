package setch.beans;

public class offre {
	private int id;
	private int user;
	private String date;
	private String numero;
	private int idfournisseur;
	private int idarticle;
	private double prixvente;
	private String statut;
	public offre(int id, int user, String date, String numero, int idfournisseur, int idarticle, double prixvente,
			String statut) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idfournisseur = idfournisseur;
		this.idarticle = idarticle;
		this.prixvente = prixvente;
		this.statut = statut;
	}
	public offre(int user, String date, String numero, int idfournisseur, int idarticle, double prixvente,
			String statut) {
		super();
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idfournisseur = idfournisseur;
		this.idarticle = idarticle;
		this.prixvente = prixvente;
		this.statut = statut;
	}
	public offre() {
		super();
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getIdfournisseur() {
		return idfournisseur;
	}
	public void setIdfournisseur(int idfournisseur) {
		this.idfournisseur = idfournisseur;
	}
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public double getPrixvente() {
		return prixvente;
	}
	public void setPrixvente(double prixvente) {
		this.prixvente = prixvente;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

}
