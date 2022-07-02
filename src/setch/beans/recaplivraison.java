package setch.beans;

public class recaplivraison {
	private String type;
	private String numerobc;
	private String numerobl;
	private String date;
	private int fournisseur;
	private Double montant;
	private String statut;
	public recaplivraison(String type,String numerobc,String numerobl, String date,int fournisseur, Double montant,String statut) {
		super();
		this.type=type;
		this.numerobc = numerobc;
		this.numerobl = numerobl;
		this.date = date;
		this.fournisseur=fournisseur;
		this.montant = montant;
		this.statut=statut;
	}
	public recaplivraison() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumerobc() {
		return numerobc;
	}
	public void setNumerobc(String numerobc) {
		this.numerobc = numerobc;
	}
	public String getNumerobl() {
		return numerobl;
	}
	public void setNumerobl(String numerobl) {
		this.numerobl = numerobl;
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
