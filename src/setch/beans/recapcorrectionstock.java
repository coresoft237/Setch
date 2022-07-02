package setch.beans;

public class recapcorrectionstock {
	private String numero;
	private String date;
	public recapcorrectionstock(String date, String numero) {
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
