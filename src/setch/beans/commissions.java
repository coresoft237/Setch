package setch.beans;

public class commissions {
private int id;
private int user;
private int famille;
private String date;
private double pourcentage;
public commissions() {
	super();
}
public commissions(int user, int famille, String date, double pourcentage) {
	super();
	this.user = user;
	this.famille = famille;
	this.date = date;
	this.pourcentage = pourcentage;
}
public commissions(int id, int user, int famille, String date, double pourcentage) {
	super();
	this.id = id;
	this.user = user;
	this.famille = famille;
	this.date = date;
	this.pourcentage = pourcentage;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser() {
	return user;
}
public void setUser(int user) {
	this.user = user;
}
public int getFamille() {
	return famille;
}
public void setFamille(int famille) {
	this.famille = famille;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public double getPourcentage() {
	return pourcentage;
}
public void setPourcentage(double pourcentage) {
	this.pourcentage = pourcentage;
}

}
