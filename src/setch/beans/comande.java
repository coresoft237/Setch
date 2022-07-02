package setch.beans;

public class comande {
private int user;
private String date;
private int article;
private Double quantite;
private Double prix;
public comande(int user, String date, int article, Double quantite, Double prix) {
	super();
	this.user = user;
	this.date = date;
	this.article = article;
	this.quantite = quantite;
	this.prix = prix;
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
public Double getPrix() {
	return prix;
}
public void setPrix(Double prix) {
	this.prix = prix;
}

}
