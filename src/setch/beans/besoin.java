package setch.beans;

import java.math.BigInteger;

public class besoin {
	private Double id;
	private int user;
	private String date;
	private String numero;
	private int idarticle;
	private int prix;
	private Double quantite;
	private String statut;
	public besoin(Double id, int user, String date, String numero, int idarticle,int prix, Double quantite,String statut) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idarticle = idarticle;
		this.prix=prix;
		this.quantite = quantite;
		this.statut=statut;
	}
	public besoin(int user, String date, String numero, int idarticle,int prix, Double quantite,String statut) {
		super();
		this.user = user;
		this.date = date;
		this.numero = numero;
		this.idarticle = idarticle;
		this.prix=prix;
		this.quantite = quantite;
		this.statut=statut;
	}
	public besoin() {
		super();
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
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

}
