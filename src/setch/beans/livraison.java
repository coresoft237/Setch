package setch.beans;

public class livraison {
	private Double id;
	private int user;
	private String date;
	private String numerobc;
	private String numerobl;
	private int idfournisseur;
	private int idarticle;
	private Double quantite;
	private Double pu;
	private String statut;
	public livraison() {
		super();
	}
	public livraison(int user, String date, String numerobc, String numerobl, int idfournisseur, int idarticle,
			Double quantite, Double pu, String statut) {
		super();
		this.user = user;
		this.date = date;
		this.numerobc = numerobc;
		this.numerobl = numerobl;
		this.idfournisseur = idfournisseur;
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.pu = pu;
		this.statut = statut;
	}
	public livraison(Double id, int user, String date, String numerobc, String numerobl, int idfournisseur,
			int idarticle, Double quantite, Double pu, String statut) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numerobc = numerobc;
		this.numerobl = numerobl;
		this.idfournisseur = idfournisseur;
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
	public String getNumerobc() {
		return numerobc;
	}
	public void setNumerobc(String numerobc) {
		this.numerobc = numerobc;
	}
	public String getNumerobl() {
		return numerobl;
	}
	public void setNumerobl(String numerobl) {
		this.numerobl = numerobl;
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
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
