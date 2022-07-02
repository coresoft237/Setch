package setch.beans;

public class statistiquevente {
	private String date;
	private String facture;
	private Double reduction;
	private int prescripteur;
	private String article;
	private int famille;
	private Double quantite;
	private double prix;
	private double pourcentage;
	private double commissions;
	private double reste;
	public statistiquevente() {
		super();
	}
	public statistiquevente(String facture,Double reduction,String date,int prescripteur, String article, int famille, Double quantite, double prix, double pourcentage,
			double commissions, double reste) {
		super();
		this.date = date;
		this.facture=facture;
		this.reduction=reduction;
		this.prescripteur = prescripteur;
		this.article = article;
		this.famille = famille;
		this.quantite = quantite;
		this.prix = prix;
		this.pourcentage = pourcentage;
		this.commissions = commissions;
		this.reste = reste;
	}
	public String getfacure() {
		return facture;
	}
	public void setfacture(String facture) {
		this.facture =facture;
	}
	public Double getreduction() {
		return reduction;
	}
	public void setreduction(Double reduction) {
		this.reduction =reduction;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrescripteur() {
		return prescripteur;
	}
	public void setPrescripteur(int prescripteur) {
		this.prescripteur = prescripteur;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public int getFamille() {
		return famille;
	}
	public void setFamille(int famille) {
		this.famille = famille;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public double getCommissions() {
		return commissions;
	}
	public void setCommissions(double commissions) {
		this.commissions = commissions;
	}
	public double getReste() {
		return reste;
	}
	public void setReste(double reste) {
		this.reste = reste;
	}
	
}
