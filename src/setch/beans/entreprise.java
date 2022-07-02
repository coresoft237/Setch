package setch.beans;

public class entreprise {
	private int id;
	private String name;
	private String sigle;
	private String formejuridique;
	private String activite;
	private String niu;
	private String bp;
	private String telephone;
	private String web;
	public entreprise() {
		super();
	}
	public entreprise(String sigle,String name, String formejuridique, String activite, String niu, String bp, String telephone,
			String web) {
		super();
		this.sigle=sigle;
		this.name = name;
		this.formejuridique = formejuridique;
		this.activite = activite;
		this.niu = niu;
		this.bp = bp;
		this.telephone = telephone;
		this.web = web;
	}
	public entreprise(int id,String sigle, String name, String formejuridique, String activite, String niu, String bp,
			String telephone, String web) {
		super();
		this.id = id;
		this.sigle=sigle;
		this.name = name;
		this.formejuridique = formejuridique;
		this.activite = activite;
		this.niu = niu;
		this.bp = bp;
		this.telephone = telephone;
		this.web = web;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	public String getFormejuridique() {
		return formejuridique;
	}
	public void setFormejuridique(String formejuridique) {
		this.formejuridique = formejuridique;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	public String getNiu() {
		return niu;
	}
	public void setNiu(String niu) {
		this.niu = niu;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	

}
