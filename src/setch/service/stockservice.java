package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.famille;
import setch.beans.stock;

public class stockservice  implements setch.Dao.idao<setch.beans.stock> {
	setch.service.articleservice articleservice=new setch.service.articleservice();
	setch.service.venteservice venteservice=new setch.service.venteservice();
	setch.service.livraisonservice livraisonservice=new setch.service.livraisonservice();
	setch.service.correctionstockservice correctionstockservice=new setch.service.correctionstockservice();
	@Override
	public boolean add(stock obj) {
		// TODO Auto-generated method stub
				boolean t=true;
					return t;
	}

	@Override
	public boolean update(int a, stock obj) {
		// TODO Auto-generated method stub
				boolean t=true;
				
				return t;
	}

	@Override
	public boolean delete(stock obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public stock getByid(int a) {
		setch.beans.stock pers = null;
		// TODO Auto-generated method stub		
		return pers;
	}

	@Override
	public List<stock> getAll() {
		// TODO Auto-generated method stub
		List<setch.beans.stock> t = new ArrayList<setch.beans.stock>();
		return t;
	}
	public List<stock> getAll(String date1,String date2,List<articles>tabarticle) throws SQLException {
		// TODO Auto-generated method stub
		List<setch.beans.stock> t = new ArrayList<setch.beans.stock>();
		try {
			for(int i=0;i<tabarticle.size();i++) {
				int article=tabarticle.get(i).getId();
				Double entree=livraisonservice.totalachat(date1, date2, article);
				Double ventes=venteservice.totalvente(date1, date2, article);
				Double ce=correctionstockservice.totalcorrectionentree(date1, date2, article);
				Double cs=correctionstockservice.totalcorrectionsortie(date1, date2, article);
				Double reste=entree-ventes+ce+cs;
				if(reste==0) {
					
				}
				else {
					setch.beans.stock c=new setch.beans.stock(date1, date2,tabarticle.get(i).getNom(), entree, ventes, ce, cs, reste);
					t.add(c);
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	
}
