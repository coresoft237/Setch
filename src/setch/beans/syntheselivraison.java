package setch.beans;

public class syntheselivraison {
	private String bc;
	private String article;
	private Double qtecde;
	private Double qteliv;
	private Double pc;
	private Double pl;
	public syntheselivraison(String bc, String article, Double qtecde, Double qteliv, Double pc, Double pl) {
		super();
		this.bc = bc;
		this.article = article;
		this.qtecde = qtecde;
		this.qteliv = qteliv;
		this.pc = pc;
		this.pl = pl;
	}
	public String getBc() {
		return bc;
	}
	public void setBc(String bc) {
		this.bc = bc;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Double getQtecde() {
		return qtecde;
	}
	public void setQtecde(Double qtecde) {
		this.qtecde = qtecde;
	}
	public Double getQteliv() {
		return qteliv;
	}
	public void setQteliv(Double qteliv) {
		this.qteliv = qteliv;
	}
	public Double getPc() {
		return pc;
	}
	public void setPc(Double pc) {
		this.pc = pc;
	}
	public Double getPl() {
		return pl;
	}
	public void setPl(Double pl) {
		this.pl = pl;
	}
	

}
