package setch.beans;

public class stock {
private String date1;
private String date2;
private String article;
private Double entree;
private Double ventes;
private Double correctionentree;
private Double correctionsortie;
private Double reste;
public stock(String date1, String date2, String article, Double entree, Double ventes, Double correctionentree,
		Double correctionsortie, Double reste) {
	super();
	this.date1 = date1;
	this.date2 = date2;
	this.article = article;
	this.entree = entree;
	this.ventes = ventes;
	this.correctionentree = correctionentree;
	this.correctionsortie = correctionsortie;
	this.reste = reste;
}
public stock() {
	super();
}
public String getDate1() {
	return date1;
}
public void setDate1(String date1) {
	this.date1 = date1;
}
public String getDate2() {
	return date2;
}
public void setDate2(String date2) {
	this.date2 = date2;
}
public String getArticle() {
	return article;
}
public void setArticle(String article) {
	this.article = article;
}
public Double getEntree() {
	return entree;
}
public void setEntree(Double entree) {
	this.entree = entree;
}
public Double getVentes() {
	return ventes;
}
public void setVentes(Double ventes) {
	this.ventes = ventes;
}
public Double getCorrectionentree() {
	return correctionentree;
}
public void setCorrectionentree(Double correctionentree) {
	this.correctionentree = correctionentree;
}
public Double getCorrectionsortie() {
	return correctionsortie;
}
public void setCorrectionsortie(Double correctionsortie) {
	this.correctionsortie = correctionsortie;
}
public Double getReste() {
	return reste;
}
public void setReste(Double reste) {
	this.reste = reste;
}

}
