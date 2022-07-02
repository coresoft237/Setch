package setch.beans;

public class livraisonvente {
	private Double id;
	private int user;
	private String date;
	private String numerobl;
	private int idclient;
	private int idarticle;
	private Double quantite;
	private int pu;
	private String statut;
	public livraisonvente() {
		super();
	}
	public livraisonvente(int user, String date, String numerobl, int idclient, int idarticle,
			Double quantite, int pu, String statut) {
		super();
		this.user = user;
		this.date = date;
		this.numerobl = numerobl;
		this.idclient = idclient;
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.pu = pu;
		this.statut = statut;
	}
	public livraisonvente(Double id, int user, String date, String numerobl, int idclient,
			int idarticle, Double quantite, int pu, String statut) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numerobl = numerobl;
		this.idclient = idclient;
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.pu = pu;
		this.statut = statut;
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
	
	public String getNumerobl() {
		return numerobl;
	}
	public void setNumerobl(String numerobl) {
		this.numerobl = numerobl;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
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
	public int getPu() {
		return pu;
	}
	public void setPu(int pu) {
		this.pu = pu;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
