package setch.beans;

public class contenuachat {
	private int id;
	private int user;
	private int article;
	private int quantite;
	public contenuachat() {
		super();
	}
	public contenuachat(int user, int article, int quantite) {
		super();
		this.user = user;
		this.article = article;
		this.quantite = quantite;
	}
	public contenuachat(int id, int user, int article, int quantite) {
		super();
		this.id = id;
		this.user = user;
		this.article = article;
		this.quantite = quantite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getArticle() {
		return article;
	}
	public void setArticle(int article) {
		this.article = article;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
