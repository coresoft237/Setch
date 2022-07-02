package setch.service;

import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.marge;
import setch.beans.reductionfacture;

public class margeservice  implements setch.Dao.idao<setch.beans.marge> {
articleservice articleservice=new articleservice();
venteservice venteservice=new venteservice();
livraisonservice livraisonservice=new livraisonservice();
prixventeservice prixventeservice=new prixventeservice();
uniteventeservice uniteventeservice=new uniteventeservice();
reductionservice reductionservice=new reductionservice();
List<setch.beans.vente> Listevente = new ArrayList<setch.beans.vente>();
articles article=null;
reductionfacture reduction=null;
	@Override
	public boolean add(marge obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int a, marge obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(marge obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public marge getByid(int a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<marge> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<marge> getAll(List<String>t,String date1,String date2) {
		// TODO Auto-generated method stub
		List<setch.beans.marge> t1 = new ArrayList<setch.beans.marge>();
		for(int a=0;a<t.size();a++) {
			Double quantite=0.0;
			Double cmup=0.0;
			Double pv=0.0;
			Double marge=0.0;
			article=articleservice.getByid(Integer.parseInt(t.get(a)));
			Listevente=venteservice.getfacture1(date1, date2, Integer.parseInt(t.get(a)));
			cmup=livraisonservice.CMUP(date2,article.getId());
			int c=0;
			for(int b=0;b<Listevente.size();b++) {	
				Double quantite1=0.0;
				Double pv1=0.0;
				
						if(uniteventeservice.unitevente(Listevente.get(b).getDate(),article.getId())>0){
							quantite1=Listevente.get(b).getQuantite()*uniteventeservice.unitevente(Listevente.get(b).getDate(),article.getId());
							if(reductionservice.getByfacture(Listevente.get(b).getFacture())!=null) {
								//pv1=((prixventeservice.getByid(Listevente.get(b).getIdprixvente()).getPrixvente())/uniteventeservice.unitevente(Listevente.get(b).getDate(),article.getId()))*((100-reductionservice.getByfacture(Listevente.get(b).getFacture()).getReduction())/100);
							pv1=prixventeservice.getByid(Listevente.get(b).getIdprixvente()).getPrixvente()*((100-reductionservice.getByfacture(Listevente.get(b).getFacture()).getReduction())/100);
							}
							else {
								//pv1=((prixventeservice.getByid(Listevente.get(b).getIdprixvente()).getPrixvente())/uniteventeservice.unitevente(Listevente.get(b).getDate(),article.getId()))/quantite1;
								pv1=prixventeservice.getByid(Listevente.get(b).getIdprixvente()).getPrixvente();
							}
							
						}
				quantite=quantite+quantite1;
				if(Listevente.get(b).getQuantite()>0) {
					System.out.println("pv1"+pv1);
					c++;
					pv=pv+pv1;	
				}
				
			}
			marge c1=new marge(article.getNom(),quantite,cmup,pv/c,((pv/c)-cmup)*quantite);
			t1.add(c1);
			
		}
		return t1;
	}

}
