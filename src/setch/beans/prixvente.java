package setch.beans;

public class prixvente {
	private int id;
	private int user;
	private String date;
	private int identreprise;
	private int idarticle;
	private double prixvente;
	public prixvente() {
		super();
	}
	public prixvente(int user, String date, int identreprise, int idarticle, double prixvente) {
		super();
		this.user = user;
		this.date = date;
		this.identreprise = identreprise;
		this.idarticle=idarticle;
		this.prixvente = prixvente;
	}
	public prixvente(int id, int user, String date, int identreprise, int idarticle, double prixvente) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.identreprise = identreprise;
		this.idarticle=idarticle;
		this.prixvente = prixvente;
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
	public int getIdentreprise() {
		return identreprise;
	}
	public void setIdentreprise(int identreprise) {
		this.identreprise = identreprise;
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

}
