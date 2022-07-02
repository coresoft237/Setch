package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.besoin;
import setch.beans.comande;
import setch.beans.famille;
import setch.beans.offre;
import setch.beans.prixvente;
import setch.beans.recapbesoin;
import setch.beans.recapcommande;
import setch.beans.recapoffre;
import setch.connection.connection;

public class commandeservice  implements setch.Dao.idao<setch.beans.comande> {
	offreservice offreservice=new offreservice();
	articleservice articleservice=new articleservice();
	familleservice familleservice=new familleservice();
	personneservice personneservice=new personneservice();
	setch.beans.personne personne=null;
	List<setch.beans.offre> t2 =null;
	List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
	public commandeservice(){
		
	}
	@Override
	public boolean add(comande obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(int a, comande obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(comande obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public comande getByid(int a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<comande> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<comande> getAll2(int fournisseur,List<setch.beans.besoin> t1) {
		// TODO Auto-generated method stub
		List<setch.beans.comande> t = new ArrayList<setch.beans.comande>();
			for(int i=0;i<t1.size();i++) {
				personne=personneservice.getByid(offreservice.getByid(t1.get(i).getPrix()).getIdfournisseur());
				if(fournisseur==personne.getId()) {
					setch.beans.comande c=new setch.beans.comande(t1.get(i).getUser(),t1.get(i).getDate(),t1.get(i).getIdarticle(),t1.get(i).getQuantite(),offreservice.getByid(t1.get(i).getPrix()).getPrixvente());
					t.add(c);
				}
			}	
		return t;
	}
	

	
}
