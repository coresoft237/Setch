package setch.beans;

public class recaptransfert {
	private String date;
	private String numero;
	private int emetteur;
	private int recepteur;
	public recaptransfert(String date, String numero, int emetteur, int recepteur) {
		super();
		this.date = date;
		this.numero = numero;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
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
	public int getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(int emetteur) {
		this.emetteur = emetteur;
	}
	public int getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(int recepteur) {
		this.recepteur = recepteur;
	}

}
