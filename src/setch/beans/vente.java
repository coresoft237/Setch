package setch.beans;

import java.math.BigInteger;

public class vente {
	private Double id;
	private String facture;
	private String date;
	private int iduser;
	private int idpatient;
	private int idprescripteur;
	private int idarticle;
	private Double quantite;
	private int idprixvente;
	private String statut;
	
	public vente() {
		super();
	}
	public vente( int iduser,String facture, String date, int idpatient, int idprescripteur, int idarticle,
			Double quantite, int idprixvente, String statut) {
		super();
		this.facture = facture;
		this.date = date;
		this.iduser = iduser;
		this.idpatient = idpatient;
		this.idprescripteur = idprescripteur;
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.idprixvente = idprixvente;
		this.statut = statut;
	
	}
	public vente(Double id, int iduser, String facture, String date, int idpatient, int idprescripteur, int idarticle,
			Double quantite, int idprixvente, String statut) {
		super();
		this.id = id;
		this.facture = facture;
		this.date = date;
		this.iduser = iduser;
		this.idpatient = idpatient;
		this.idprescripteur = idprescripteur;
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.idprixvente = idprixvente;
		this.statut = statut;
		
		
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public String getFacture() {
		return facture;
	}
	public void setFacture(String facture) {
		this.facture = facture;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getIdpatient() {
		return idpatient;
	}
	public void setIdpatient(int idpatient) {
		this.idpatient = idpatient;
	}
	public int getIdprescripteur() {
		return idprescripteur;
	}
	public void setIdprescripteur(int idprescripteur) {
		this.idprescripteur = idprescripteur;
	}
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public int getIdprixvente() {
		return idprixvente;
	}
	public void setIdprixvente(int idprixvente) {
		this.idprixvente = idprixvente;
	}
	public String getStatut() {
		return statut;
	}
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
}
