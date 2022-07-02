package setch.beans;

public class marge {
	private String article;
	private Double quantite;
	private Double pa;
	private Double pv;
	private Double marge;
	public marge(String article, Double quantite, Double pa, Double pv, Double marge) {
		super();
		this.article = article;
		this.quantite = quantite;
		this.pa = pa;
		this.pv = pv;
		this.marge = marge;
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
	public Double getPa() {
		return pa;
	}
	public void setPa(Double pa) {
		this.pa = pa;
	}
	public Double getPv() {
		return pv;
	}
	public void setPv(Double pv) {
		this.pv = pv;
	}
	public Double getMarge() {
		return marge;
	}
	public void setMarge(Double marge) {
		this.marge = marge;
	}
	
}
