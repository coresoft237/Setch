package setch.beans;

public class recapvente {
	private String famille;
	private Double VN;
	private Double Retour1;
	private Double retour2;
	private Double credit1;
	private Double credit2;
	private Double total;
	public recapvente(String famille, Double vN, Double retour1, Double retour2, Double credit1, Double credit2,
			Double total) {
		super();
		this.famille = famille;
		VN = vN;
		Retour1 = retour1;
		this.retour2 = retour2;
		this.credit1 = credit1;
		this.credit2 = credit2;
		this.total = total;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public Double getVN() {
		return VN;
	}
	public void setVN(Double vN) {
		VN = vN;
	}
	public Double getRetour1() {
		return Retour1;
	}
	public void setRetour1(Double retour1) {
		Retour1 = retour1;
	}
	public Double getRetour2() {
		return retour2;
	}
	public void setRetour2(Double retour2) {
		this.retour2 = retour2;
	}
	public Double getCredit1() {
		return credit1;
	}
	public void setCredit1(Double credit1) {
		this.credit1 = credit1;
	}
	public Double getCredit2() {
		return credit2;
	}
	public void setCredit2(Double credit2) {
		this.credit2 = credit2;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}
