package setch.beans;

public class recapmarge {
	private String famille;
	private String article;
	private Double quantite;
	private Double cout;
	private Double prix;
	public recapmarge(String famille, String article, Double quantite, Double cout, Double prix) {
		super();
		this.famille = famille;
		this.article = article;
		this.quantite = quantite;
		this.cout = cout;
		this.prix = prix;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public Double getCout() {
		return cout;
	}
	public void setCout(Double cout) {
		this.cout = cout;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	
}
