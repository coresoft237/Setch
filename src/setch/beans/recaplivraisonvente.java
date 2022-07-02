package setch.beans;

public class recaplivraisonvente {
	private String type;
	private String numerobl;
	private String date;
	private int client;
	private Double montant;
	private String statut;
	public recaplivraisonvente(String type,String numerobl, String date,int client, Double montant,String statut) {
		super();
		this.type=type;
		this.numerobl = numerobl;
		this.date = date;
		this.client=client;
		this.montant = montant;
		this.statut=statut;
	}
	public recaplivraisonvente() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

}
