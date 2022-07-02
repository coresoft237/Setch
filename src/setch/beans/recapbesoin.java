package setch.beans;

public class recapbesoin {
	private String type;
	private String numero;
	private String date;
	private Double montant;
	private String statut;
	public recapbesoin(String type,String numero, String date, Double montant,String statut) {
		super();
		this.type=type;
		this.numero = numero;
		this.date = date;
		this.montant = montant;
		this.statut=statut;
	}
	public recapbesoin() {
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
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}

}
