package setch.beans;

public class unitevente {
	private int id;
	private int user;
	private String date;
	private int identreprise;
	private int idarticle;
	private double unitevente;
	public unitevente(int user, String date, int identreprise, int idarticle, double unitevente) {
		super();
		this.user = user;
		this.date = date;
		this.identreprise = identreprise;
		this.idarticle = idarticle;
		this.unitevente = unitevente;
	}
	public unitevente(int id, int user, String date, int identreprise, int idarticle, double unitevente) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.identreprise = identreprise;
		this.idarticle = idarticle;
		this.unitevente = unitevente;
	}
	public unitevente() {
		super();
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
	public int getIdentreprise() {
		return identreprise;
	}
	public void setIdentreprise(int identreprise) {
		this.identreprise = identreprise;
	}
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public double getUnitevente() {
		return unitevente;
	}
	public void setUnitevente(double unitevente) {
		this.unitevente = unitevente;
	}
	

}
