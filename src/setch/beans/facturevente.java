package setch.beans;

import java.util.List;

public class facturevente {
	private setch.beans.entreprise entreprise;
	private List<setch.beans.vente> listevente;
	public facturevente() {
		super();
	}
	public facturevente(setch.beans.entreprise entreprise, List<vente> listevente) {
		super();
		this.entreprise = entreprise;
		this.listevente = listevente;
	}
	public setch.beans.entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(setch.beans.entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public List<setch.beans.vente> getListevente() {
		return listevente;
	}
	public void setListevente(List<setch.beans.vente> listevente) {
		this.listevente = listevente;
	}

}
