package setch.beans;

public class totallivraison {
	int idarticle;
	Double quantite;
	Double prixmoyen;
	public totallivraison(int idarticle, Double quantite, Double prixmoyen) {
		super();
		this.idarticle = idarticle;
		this.quantite = quantite;
		this.prixmoyen = prixmoyen;
	}
	public totallivraison() {
		super();
	}
	public Double getPrixmoyen() {
		return prixmoyen;
	}
	public void setPrixmoyen(Double prixmoyen) {
		this.prixmoyen = prixmoyen;
	}
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	

}
