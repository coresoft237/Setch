package setch.beans;

public class reductionfacture {
	private int id;
	private int user;
	private String date;
	private String facture;
	private double reduction;
	public reductionfacture() {
		super();
	}
	public reductionfacture(int user,String date, String facture, double reduction) {
		super();
		this.date = date;
		this.facture = facture;
		this.reduction = reduction;
		this.user=user;
	}
	public reductionfacture(int id,int user, String date, String facture, double reduction) {
		super();
		this.id = id;
		this.user=user;
		this.user=user;
		this.date = date;
		this.facture = facture;
		this.reduction = reduction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getuser() {
		return user;
	}
	public void setuser(int user) {
		this.user =user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFacture() {
		return facture;
	}
	public void setFacture(String facture) {
		this.facture = facture;
	}
	public double getReduction() {
		return reduction;
	}
	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	

}
