package setch.beans;

public class correctionstock {
	private Double id;
	private int user;
	private String date;
	private String numero;
	private int article;
	private Double quantite;
	public correctionstock() {
		super();
	}
	public correctionstock(int user, String date, String numero, int article, Double quantite) {
		super();
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.article = article;
		this.quantite = quantite;
	}
	public correctionstock(Double id, int user, String date, String numero, int article, Double quantite) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.article = article;
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
	public int getArticle() {
		return article;
	}
	public void setArticle(int article) {
		this.article = article;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	

}
