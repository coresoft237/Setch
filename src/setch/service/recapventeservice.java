package setch.service;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.paymentcredit;
import setch.beans.recapvente;
import setch.connection.connection;

public class recapventeservice  implements setch.Dao.idao<setch.beans.recapvente>{
setch.beans.famille famille=null;
setch.beans.articles article=null;
setch.beans.prixvente prixvente=null;
paymentcredit payementcredit=null;
venteservice venteservice=new venteservice();
articleservice articleservice=new articleservice();
prixventeservice prixventeservice=new prixventeservice();
paymentcreditservice payementcreditservice=new paymentcreditservice();
familleservice familleservice=new familleservice();
List<setch.beans.vente> Listevente = new ArrayList<setch.beans.vente>();
List<String> listefamille = new ArrayList<String>();

public recapventeservice(){
	
}
	@Override
	public boolean add(recapvente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int a, recapvente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(recapvente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public recapvente getByid(int a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<recapvente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<recapvente> getAll(String date1,String date2,String statut,List<String> t) {
		// TODO Auto-generated method stub
		List<setch.beans.recapvente> Listerecapvente = new ArrayList<setch.beans.recapvente>();
		List<setch.beans.recapvente> Listerecapvente1 = new ArrayList<setch.beans.recapvente>();
		for(int i=0;i<t.size();i++) {
			//--------vente normale
				Listevente=venteservice.detailperiodique(date1, date2, statut,t.get(i));
				for(int x=0;x<Listevente.size();x++) {
					famille=familleservice.getByid(articleservice.getByid(Listevente.get(x).getIdarticle()).getIdfamille());
					prixvente=prixventeservice.getByid(Listevente.get(x).getIdprixvente());
					setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),Listevente.get(x).getQuantite()*prixvente.getPrixvente(),0.0,0.0,0.0,0.0,0.0);
					Listerecapvente.add(c);
				}
			//--------vente retour 
				Listevente=venteservice.detailperiodique(date1, date2, statut,t.get(i));
				for(int x=0;x<Listevente.size();x++) {
					famille=familleservice.getByid(articleservice.getByid(Listevente.get(x).getIdarticle()).getIdfamille());
					prixvente=prixventeservice.getByid(Listevente.get(x).getIdprixvente());
					if(venteservice.gettotalfacture(Listevente.get(x).getFacture())>0) {
					setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),0.0,Listevente.get(x).getQuantite()*prixvente.getPrixvente(),0.0,0.0,0.0,0.0);
					Listerecapvente.add(c);
					}
					else {
						setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),0.0,0.0,Listevente.get(x).getQuantite()*prixvente.getPrixvente(),0.0,0.0,0.0);
						Listerecapvente.add(c);
					}
				}
			//----credit-----
				Listevente=venteservice.detailperiodique(date1, date2, statut,t.get(i));
				for(int x=0;x<Listevente.size();x++) {
					famille=familleservice.getByid(articleservice.getByid(Listevente.get(x).getIdarticle()).getIdfamille());
					prixvente=prixventeservice.getByid(Listevente.get(x).getIdprixvente());
					setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),0.0,0.0,0.0,Listevente.get(x).getQuantite()*prixvente.getPrixvente(),0.0,0.0);
					Listerecapvente.add(c);	
			}
				//-------credit payé
				Listevente=venteservice.detailperiodique(date1, date2, statut,t.get(i));
				for(int x=0;x<Listevente.size();x++) {
					Double val=0.0;
					Double payement=payementcreditservice.totalpayment(Listevente.get(x).getFacture());
					Double totalfacture=venteservice.gettotalfacture(Listevente.get(x).getFacture());
					famille=familleservice.getByid(articleservice.getByid(Listevente.get(x).getIdarticle()).getIdfamille());
					prixvente=prixventeservice.getByid(Listevente.get(x).getIdprixvente());
					if(payement>0) {
						val=((payement/totalfacture)*(Listevente.get(x).getQuantite()*prixvente.getPrixvente()));
					}
					setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),0.0,0.0,0.0,0.0,val,0.0);
					Listerecapvente.add(c);	
			}
		}
			listefamille=venteservice.famille(date1, date2, statut);
			Double vn1=0.0;
			Double retour11=0.0;
			Double retour21=0.0;
			Double credit11=0.0;
			Double credit21=0.0;
			Double total1=0.0;
			for(int y=0;y<listefamille.size();y++) {
				Double vn=0.0;
				Double retour1=0.0;
				Double retour2=0.0;
				Double credit1=0.0;
				Double credit2=0.0;
				Double total=0.0;
				for(int z=0;z<Listerecapvente.size();z++) {
					if(listefamille.get(y)==Listerecapvente.get(z).getFamille()) {
						vn=vn+Listerecapvente.get(z).getVN();
						retour1=retour1+Listerecapvente.get(z).getRetour1();
						retour2=retour2+Listerecapvente.get(z).getRetour2();
						credit1=credit1+Listerecapvente.get(z).getCredit1();
						credit2=credit2+Listerecapvente.get(z).getCredit2();
						total=vn+retour1+credit2;
					
				}
			}
				setch.beans.recapvente c=new setch.beans.recapvente(famille.getNom(),vn,retour1,retour2,credit1,credit2,total);
				Listerecapvente.add(c);	
				vn1=vn1+vn;
				retour11=retour11+retour1;
				retour21=retour21+retour2;
				credit11=credit11+credit1;
				credit21=credit21+credit2;
				total1=total1+total;
	}
			setch.beans.recapvente c=new setch.beans.recapvente("Totaux",vn1,retour11,retour21,credit11,credit21,total1);
			Listerecapvente.add(c);	

	
		return Listerecapvente1;
}
}
