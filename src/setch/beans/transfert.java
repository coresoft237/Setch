package setch.beans;

public class transfert {
	private double id;
	private int user;
	private String date;
	private String numero;
	private int emetteur;
	private int recepteur;
	private int article;
	private double quantite;
	public transfert() {
		super();
	}
	public transfert(int user, String date, String numero, int emetteur, int recepteur, int article, double quantite) {
		super();
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.article = article;
		this.quantite = quantite;
	}
	public transfert(double id, int user, String date, String numero, int emetteur, int recepteur, int article,
			double quantite) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.article = article;
		this.quantite = quantite;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
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
	public int getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(int emetteur) {
		this.emetteur = emetteur;
	}
	public int getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(int recepteur) {
		this.recepteur = recepteur;
	}
	public int getArticle() {
		return article;
	}
	public void setArticle(int article) {
		this.article = article;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	
		

}
