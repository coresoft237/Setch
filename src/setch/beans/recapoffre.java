package setch.beans;

public class recapoffre {
	private String numero;
	private String date;
	private int fournisseur;
	public recapoffre(String numero, String date, int fournisseur) {
		super();
		this.numero = numero;
		this.date = date;
		this.fournisseur = fournisseur;
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
	public int getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(int fournisseur) {
		this.fournisseur = fournisseur;
	}
	

}
