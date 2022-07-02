package setch.beans;

public class recapinventaire {
	private String numero;
	private String date;
	public recapinventaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public recapinventaire(String numero, String date) {
		super();
		this.numero = numero;
		this.date = date;
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
	
}
