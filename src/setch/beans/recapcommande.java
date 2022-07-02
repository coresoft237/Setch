package setch.beans;

public class recapcommande {
	private String type;
	private String numero;
	private String date;
	private int fournisseur;
	private Double montant;
	private String statut;
	public recapcommande(String type,String numero, String date,int fournisseur, Double montant,String statut) {
		super();
		this.type=type;
		this.numero = numero;
		this.date = date;
		this.fournisseur=fournisseur;
		this.montant = montant;
		this.statut=statut;
	}
	public recapcommande() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public int getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(int fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

}
