package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class statistiqueventeservice  {
	setch.service.articleservice articleservice=new setch.service.articleservice();
	setch.service.familleservice familleservice=new setch.service.familleservice();
	setch.service.prixventeservice prixventeservice=new setch.service.prixventeservice();
	setch.service.commissionservice commissionservice=new setch.service.commissionservice();
	setch.service.personneservice personneservice=new setch.service.personneservice();
	setch.service.reductionservice reductionservice=new setch.service.reductionservice();
	setch.beans.reductionfacture reduction=null;
	public List<setch.beans.statistiquevente>fonction(List<setch.beans.vente> t1) {
		List<setch.beans.statistiquevente> t = new ArrayList<setch.beans.statistiquevente>();
		for(int i=0;i<t1.size();i++)
		{
			Double pourcentage=0.0;
			setch.beans.reductionfacture reduction=reductionservice.getByfacture(t1.get(i).getFacture());
			if(reduction!=null)
			{
				pourcentage=reduction.getReduction()/100;
			}
			String val1=t1.get(i).getDate();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime=LocalDateTime.parse(val1,formatter);
			LocalDate date2=dateTime.toLocalDate();
			List<setch.beans.prixvente>listeprix=prixventeservice.getfacture1("0000-00-00",date2.toString(),t1.get(i).getIdarticle());
			List<setch.beans.commissions>listecommission=commissionservice.getfacture1("0000-00-00",date2.toString(),articleservice.getByid(t1.get(i).getIdarticle()).getIdfamille());
			double total=listeprix.get(0).getPrixvente()*t1.get(i).getQuantite();
			double commission=0;
			double pourcentagecommission=0;
			if(listecommission.size()>0)
			{
					commission=total*(listecommission.get(0).getPourcentage()/100);
					pourcentagecommission=listecommission.get(0).getPourcentage()/100;
			}
			double reste=total-commission;
			reduction=reductionservice.getByfacture(t1.get(i).getFacture());
			if(reduction==null)
			{
				reduction=new setch.beans.reductionfacture();
				reduction.setReduction(0.0);
			}
			setch.beans.statistiquevente stat=new setch.beans.statistiquevente(t1.get(i).getFacture(),reduction.getReduction(),t1.get(i).getDate(),personneservice.getByid(t1.get(i).getIdprescripteur()).getId(),articleservice.getByid(t1.get(i).getIdarticle()).getNom(),familleservice.getByid(articleservice.getByid(t1.get(i).getIdarticle()).getIdfamille()).getId(),t1.get(i).getQuantite(),listeprix.get(0).getPrixvente()*(1-pourcentage),pourcentagecommission, commission, reste);
			t.add(stat);
		}
		return t;
	}
	public List<setch.beans.statistiquecommission>fonctioncommission(List<setch.beans.vente> t1) {
		List<setch.beans.statistiquecommission> t = new ArrayList<setch.beans.statistiquecommission>();
		for(int i=0;i<t1.size();i++)
		{
			Double pourcentage=0.0;
			setch.beans.reductionfacture reduction=reductionservice.getByfacture(t1.get(i).getFacture());
			if(reduction!=null)
			{
				pourcentage=reduction.getReduction()/100;
			}
			String val1=t1.get(i).getDate();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime=LocalDateTime.parse(val1,formatter);
			LocalDate date2=dateTime.toLocalDate();
			List<setch.beans.prixvente>listeprix=prixventeservice.getfacture1("0000-00-00",date2.toString(),t1.get(i).getIdarticle());
			List<setch.beans.commissions>listecommission=commissionservice.getfacture1("0000-00-00",date2.toString(),articleservice.getByid(t1.get(i).getIdarticle()).getIdfamille());
			double total=listeprix.get(0).getPrixvente()*t1.get(i).getQuantite()*(1-pourcentage);
			double commission=0;
			double pourcentagecommission=0;
			if(listecommission.size()>0)
			{
			commission=total*(listecommission.get(0).getPourcentage()/100);
			pourcentagecommission=listecommission.get(0).getPourcentage()/100;
			}
			reduction=reductionservice.getByfacture(t1.get(i).getFacture());
			if(reduction==null)
			{
				reduction=new setch.beans.reductionfacture();
				reduction.setReduction(0.0);
			}
			reduction=reductionservice.getByfacture(t1.get(i).getFacture());
			if(reduction==null)
			{
				reduction=new setch.beans.reductionfacture();
				reduction.setReduction(0.0);
			}
			setch.beans.statistiquecommission st=new setch.beans.statistiquecommission(t1.get(i).getDate(),t1.get(i).getFacture(),reduction.getReduction(),personneservice.getByid(t1.get(i).getIdpatient()).getNom(),articleservice.getByid(t1.get(i).getIdarticle()).getNom(),familleservice.getByid(articleservice.getByid(t1.get(i).getIdarticle()).getIdfamille()).getNom(),t1.get(i).getQuantite(),listeprix.get(0).getPrixvente()*(1-pourcentage),pourcentagecommission,(t1.get(i).getQuantite()*listeprix.get(0).getPrixvente())*(1-pourcentage),(t1.get(i).getQuantite()*listeprix.get(0).getPrixvente()*(listecommission.get(0).getPourcentage()/100))*(1-pourcentage));
			t.add(st);
		}
		return t;
	}
	public List<setch.beans.statistiquevente2>fonction1(List<setch.beans.statistiquevente> t1) {
		List<setch.beans.statistiquevente2> t = new ArrayList<setch.beans.statistiquevente2>();
		List<setch.beans.famille>t2=new ArrayList<setch.beans.famille>();
		t2=familleservice.getAll();
		for(int i=0;i<t2.size();i++)
		{
			double commission=0;
			double reste=0;
			for( int j=0;j<t1.size();j++)
			{
				
				if(t2.get(i).getNom().equals(familleservice.getByid(t1.get(j).getFamille()).getNom()))
				{
					commission=commission+t1.get(j).getCommissions()*((100-t1.get(j).getreduction())/100);
					reste=reste+t1.get(j).getReste()*((100-t1.get(j).getreduction())/100);
				}
				
				
			}
			setch.beans.statistiquevente2 st=new setch.beans.statistiquevente2(t2.get(i).getId(),0, commission, reste);
			if(commission+reste>0)
			{
			t.add(st);
			}
		}
		return t;
	}
	public List<setch.beans.statistiquevente2>fonction2(List<setch.beans.statistiquevente> t1) {
		List<setch.beans.statistiquevente2> t = new ArrayList<setch.beans.statistiquevente2>();
		List<setch.beans.personne>t2=new ArrayList<setch.beans.personne>();
		t2=personneservice.getAll("prescripteur", "actif");
		for(int i=0;i<t2.size();i++)
		{
			double commission=0;
			double reste=0;
			for( int j=0;j<t1.size();j++)
			{
				if(t2.get(i).getNom().equals(personneservice.getByid(t1.get(j).getPrescripteur()).getNom()))
				{
					commission=commission+t1.get(j).getCommissions()*((100-t1.get(j).getreduction())/100);
					reste=reste+t1.get(j).getReste()*((100-t1.get(j).getreduction())/100);
				}
				
				
			}
			setch.beans.statistiquevente2 st=new setch.beans.statistiquevente2(0,t2.get(i).getId(), commission, reste);
			t.add(st);
		}
		return t;
	}
	}



