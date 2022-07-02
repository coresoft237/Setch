package setch.beans;

public class statistiquecommission {
	private String date;
	private String facture;
	private Double reduction;
	private String patient;
	private String article;
	private String famille;
	 private Double quantite;
	 private double pu;
	 private double pourcentage;
	 private double ptixtotal;
	 private double quotepart;
	public statistiquecommission() {
		super();
	}
	public statistiquecommission(String date,String facture,Double reduction, String patient, String article, String famille, Double quantite,
			double pu, double pourcentage, double ptixtotal, double quotepart) {
		super();
		this.date=date;
		this.reduction=reduction;
		this.facture = facture;
		this.patient = patient;
		this.article = article;
		this.famille = famille;
		this.quantite = quantite;
		this.pu = pu;
		this.pourcentage = pourcentage;
		this.ptixtotal = ptixtotal;
		this.quotepart = quotepart;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getreduction() {
		return reduction;
	}
	public void setreduction(Double reduction) {
		this.reduction = reduction;
	}
	public String getFacture() {
		return facture;
	}
	public void setFacture(String facture) {
		this.facture = facture;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public double getPu() {
		return pu;
	}
	public void setPu(double pu) {
		this.pu = pu;
	}
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public double getPtixtotal() {
		return ptixtotal;
	}
	public void setPtixtotal(double ptixtotal) {
		this.ptixtotal = ptixtotal;
	}
	public double getQuotepart() {
		return quotepart;
	}
	public void setQuotepart(double quotepart) {
		this.quotepart = quotepart;
	}

}
