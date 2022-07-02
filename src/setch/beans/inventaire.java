package setch.beans;

public class inventaire {
	private Double id;
	private int user;
	private String date;
	private String numero;
	private int idarticle;
	private Double quantite;
	public inventaire(Double id, int user, String date, String numero, int idarticle, Double quantite) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idarticle = idarticle;
		this.quantite = quantite;
	}
	public inventaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public inventaire(int user, String date, String numero, int idarticle, Double quantite) {
		super();
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idarticle = idarticle;
		this.quantite = quantite;
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
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
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	
}
