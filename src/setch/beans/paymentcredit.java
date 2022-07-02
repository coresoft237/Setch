package setch.beans;

public class paymentcredit {
	int id;
	int user;
	String date;
	String facture;
	Double montant;
	public paymentcredit() {
		super();
	}
	public paymentcredit(int user, String date, String facture, Double montant) {
		super();
		this.user = user;
		this.date = date;
		this.facture = facture;
		this.montant = montant;
	}
	public paymentcredit(int id, int user, String date, String facture, Double montant) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.facture = facture;
		this.montant = montant;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFacture() {
		return facture;
	}
	public void setFacture(String facture) {
		this.facture = facture;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	

}
