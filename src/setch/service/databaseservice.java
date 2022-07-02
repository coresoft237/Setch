package setch.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.layout.element.Cell;

import setch.beans.articles;
import setch.beans.commissions;
import setch.beans.famille;
import setch.beans.prixvente;
import setch.beans.unitevente;
import setch.connection.connection;

import java.io.InputStream;


public class databaseservice<POIFSFileSystem>  {
	//creation base de donnée
	List<setch.beans.famille> t=null;
	setch.beans.famille familles=null;
	setch.beans.articles article=null;
	setch.beans.offre offre=null;
	setch.beans.prixvente prix=null;
	setch.beans.unitevente unite=null;
	Statement stmt;
	setch.beans.commissions commission=null;
	setch.beans.contenuautorisation contenuautorisation=null;
	setch.beans.personne prescripteur=null;
	setch.service.familleservice familleservice=new setch.service.familleservice();
	setch.service.offreservice offreservice=new setch.service.offreservice();
	setch.service.articleservice articleservice=new setch.service.articleservice();
	setch.service.prixventeservice prixservice=new setch.service.prixventeservice();
	setch.service.uniteventeservice uniteservice=new setch.service.uniteventeservice();
	setch.service.commissionservice commissionservice=new setch.service.commissionservice();
	 setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	 setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
	 List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
	setch.service.personneservice personneservice=new setch.service.personneservice();
	setch.connection.connection connect=new setch.connection.connection();
	public boolean createdatabase() {
		connection c1=new connection();
		 stmt=c1.connecter1();
		boolean t=true;
		String sql="CREATE DATABASE IF NOT EXISTS setch";
	
		try {
			int  nbre=stmt.executeUpdate(sql);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close1();
		}
				
		return t;
	}
	public boolean createtablecontenuautorisation() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists contenuautorisation(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,idautorisation INT NOT NULL,tables VARCHAR(255) NOT NULL,visualiser VARCHAR(10) NOT NULL,creer VARCHAR(10) NOT NULL,modifier VARCHAR(10) NOT NULL,supprimer VARCHAR(10) NOT NULL,imprimer VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);
			//creer valeur
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}		
		return t;
	}
	public boolean createtableautilisateur() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists utilisateur(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,nom VARCHAR(255) NOT NULL,login VARCHAR(255) NOT NULL,password VARCHAR(255) NOT NULL,statut VARCHAR(10) NOT NULL,autorisation INT NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
		
	}
	public boolean createtablereduction() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists reduction(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,user INT NOT NULL,date DATE NOT NULL, facture VARCHAR(255) NOT NULL,reduction DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablecontenuachat() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists contenuachat(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,user INT NOT NULL, idarticle INT NOT NULL,quantite INT NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablecorrectiostock() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists correctionstock(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT ,user INT NOT NULL,date DATE NOT NULL, numero VARCHAR(255) NOT NULL,article INT NOT NULL,quantite DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtableapersonne() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists personne(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,nom VARCHAR(255) NOT NULL,phone VARCHAR(255) NOT NULL,statut VARCHAR(255) NOT NULL,titre VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	
	public boolean createtablevente() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists vente(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,facture VARCHAR(255) NOT NULL,date DATETIME NOT NULL,patient INT NOT NULL,prescripteur INT NOT NULL,article INT NOT NULL,quantite Double NOT NULL,prixvente INT NOT NULL,statut VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablebesoin() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists besoin(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATETIME NOT NULL,numero VARCHAR(255) NOT NULL,article INT NOT NULL,prix INT NOT NULL,quantite DOUBLE NOT NULL,statut VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablelivraison() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists livraison(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,numerobc VARCHAR(255) NOT NULL,numerobl VARCHAR(255) NOT NULL,fournisseur INT NOT NULL,article INT NOT NULL,quantite DOUBLE NOT NULL,pu DOUBLE NOT NULL,statut VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablelivraisonvente() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists livraisonvente(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATETIME NOT NULL,numerobl VARCHAR(255) NOT NULL,client INT NOT NULL,article INT NOT NULL,quantite DOUBLE NOT NULL,pu DOUBLE NOT NULL,statut VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablecommission() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists commissions(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,famille INT NOT NULL,date DATE NOT NULL,pourcentage DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablevalidite() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists validite(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,date VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablefacturation() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists facturation(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,date DATETIME NOT NULL,type VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablearticle() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists article(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,idfamille INT NOT NULL,nom VARCHAR(255) NOT NULL,statut VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtableinventaire() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists inventaire(id Double PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,numero VARCHAR(255) NOT NULL,article INT NOT NULL,quantite Double NOT NULL,prix Double NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablefamille() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists famille(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,nom VARCHAR(255) NOT NULL,statut VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtableservice() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists service(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,nom VARCHAR(255) NOT NULL,statut VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtabletransfert() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists transfert(id DOUBLE PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,numero VARCHAR(255) NOT NULL,emetteur INT NOT NULL,recepteur INT NOT NULL,article INT NOT NULL,quantite DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtablepaymentcredit() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists paymentcredit(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,facture VARCHAR(250) NOT NULL,montant DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createprixvente() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists prixvente(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,identreprise INT NOT NULL,idarticle INT NOT NULL,prixvente DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createoffre() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists offre(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,numero VARCHAR(50) NOT NULL,idfournisseur INT NOT NULL,idarticle INT NOT NULL,prixvente DOUBLE NOT NULL,statut VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createunitevente() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists unitevente(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,date DATE NOT NULL,identreprise INT NOT NULL,idarticle INT NOT NULL,unitevente DOUBLE NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.connecter();
		}
				
		return t;
	}
	public boolean createtabledepot() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists depot(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,nom VARCHAR(255) NOT NULL,statut VARCHAR(10) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtableautorisation() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists autorisation(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,user INT NOT NULL,nom VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public boolean createtableentreprise() {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="create table if not exists entreprise(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,sigle VARCHAR(255) NOT NULL,name VARCHAR(255) NOT NULL,formejuridique VARCHAR(255) NOT NULL,activite VARCHAR(255) NOT NULL,niu VARCHAR(255) NOT NULL,bp VARCHAR(255) NOT NULL,telephone VARCHAR(255) NOT NULL,siteweb VARCHAR(255) NOT NULL)";
			
				
		try {
			int  nbre=stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
				
		return t;
	}
	public List<String> getAlldatabases() {
		connection c1=new connection();
		Statement stmt1=c1.connecter1();
		List<String> t = new ArrayList<String>();
		String sql="show databases";
		try {
			ResultSet resultat=stmt1.executeQuery(sql);
			while(resultat.next())
			{
			String  c=resultat.getString("Database");
			t.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close1();
			
		}
		
		return t;
	}
	public boolean databasesexits(String table)
	{
		
		boolean t=false;
		int i=-1;
		do
		{
			i=i+1;
			getAlldatabases().size();
			if(i<getAlldatabases().size()&&getAlldatabases().get(i).equals(table))
			{
				t=true;
			}		
		}
		while(i<getAlldatabases().size()&&t==false);
		return t;
	}
	public List<String> getAlltables() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<String> t = new ArrayList<String>();
		String sql="show tables";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			String  c=resultat.getString("Tables_in_setch");
			t.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}

		return t;
	}
	
		public void chargementfamille(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
				org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(0);
				System.out.print("viens5");
				for(int i=1;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						familles=new famille(1,dataSheet.getRow(i).getCell(0).getStringCellValue(),"actif");
					boolean exe=familleservice.add(familles);
						if(exe==true)
						{
							//creer contenuautorisation
							Listeautorisation=autorisationservice.getAll();
							for(int j=0;j<Listeautorisation.size();j++)
							{
								if(Listeautorisation.get(j).getId()<3)
								{
								contenuautorisation=new setch.beans.contenuautorisation(1,Listeautorisation.get(j).getId(),"etatvente"+dataSheet.getRow(i).getCell(0).getStringCellValue()+"","true","true","true","true","true");
								}
								else
								{
									contenuautorisation=new setch.beans.contenuautorisation(1,Listeautorisation.get(i).getId(),"etatvente'"+dataSheet.getRow(j).getCell(0).getStringCellValue()+"'","false","false","false","false","false");	
								}
								contenuautorisationservice.add(contenuautorisation);
								
							}	
						}
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementarticle(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
		        org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(0);
				for(int i=0;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						article=new articles(1,(int)dataSheet.getRow(i).getCell(0).getNumericCellValue(),dataSheet.getRow(i).getCell(1).getStringCellValue(),"actif");
						System.out.println(article.getNom());
						articleservice.add(article);	
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementoffre(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
		        org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(0);
				for(int i=0;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						offre=new setch.beans.offre(1,dataSheet.getRow(i).getCell(0).getStringCellValue(),dataSheet.getRow(i).getCell(1).getStringCellValue(),(int)dataSheet.getRow(i).getCell(2).getNumericCellValue(),(int)dataSheet.getRow(i).getCell(3).getNumericCellValue(),(double)dataSheet.getRow(i).getCell(4).getNumericCellValue(),"actif");
						offreservice.add(offre);	
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementcommission(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
				org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(2);
				for(int i=1;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						commission=new commissions(1,(int)dataSheet.getRow(i).getCell(1).getNumericCellValue(),dataSheet.getRow(i).getCell(2).getStringCellValue(),dataSheet.getRow(i).getCell(3).getNumericCellValue());
						commissionservice.add(commission);
					}
				}
	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementprix(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
				org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(0);
				for(int i=0;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						prix=new prixvente(1,dataSheet.getRow(i).getCell(1).getStringCellValue(),1,(int)dataSheet.getRow(i).getCell(3).getNumericCellValue(),dataSheet.getRow(i).getCell(4).getNumericCellValue());
						prixservice.add(prix);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementunite(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
				org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(5);
				for(int i=1;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						unite=new unitevente(1,dataSheet.getRow(i).getCell(1).getStringCellValue(),1,(int)dataSheet.getRow(i).getCell(3).getNumericCellValue(),dataSheet.getRow(i).getCell(4).getNumericCellValue());
						uniteservice.add(unite);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void chargementprescripteur(String url) throws IOException
		{
			try {
				FileInputStream excel=new FileInputStream(new File(url));
				Workbook workbook=new XSSFWorkbook(excel);
				org.apache.poi.ss.usermodel.Sheet dataSheet=workbook.getSheetAt(4);
				for(int i=1;i<=dataSheet.getLastRowNum();i++)
				{
					Row row=dataSheet.getRow(i);
					if(row!=null)
					{
						prescripteur=new setch.beans.personne(dataSheet.getRow(i).getCell(0).getStringCellValue(),dataSheet.getRow(i).getCell(1).getStringCellValue(),"actif","prescripteur");
						personneservice.add(prescripteur);	
					}
				}	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public List<String> listetables() {
			connection c1=new connection();
			 stmt=c1.connecter();
			// TODO Auto-generated method stub
					List<String> t = new ArrayList<String>();
					String sql="SELECT distinct tables FROM contenuautorisation ";
					
					try {
						ResultSet resultat=stmt.executeQuery(sql);
						while(resultat.next())
						{
							
						String a=resultat.getString("tables");
						t.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						c1.close();
					}
					return t;	
					}

}
