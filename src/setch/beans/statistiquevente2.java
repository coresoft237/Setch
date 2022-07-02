package setch.beans;

public class statistiquevente2 {
private int famille;
private int prescripteur;
private double commission;
private double reste;
public statistiquevente2(int famille,int prescripteur, double commission, double reste) {
	super();
	this.famille = famille;
	this.prescripteur=prescripteur;
	this.commission = commission;
	this.reste = reste;
}
public statistiquevente2() {
	super();
}
public int getFamille() {
	return famille;
}
public void setFamille(int famille) {
	this.famille = famille;
}
public int getPrescripteur() {
	return prescripteur;
}
public void setPrescripteur(int prescripteur) {
	this.prescripteur = prescripteur;
}
public double getCommission() {
	return commission;
}
public void setCommission(double commission) {
	this.commission = commission;
}
public double getReste() {
	return reste;
}
public void setReste(double reste) {
	this.reste = reste;
}


}
