package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.besoin;
import setch.beans.famille;
import setch.beans.livraison;
import setch.beans.offre;
import setch.beans.prixvente;
import setch.beans.recapbesoin;
import setch.beans.recapcommande;
import setch.beans.recaplivraison;
import setch.beans.recapoffre;
import setch.beans.totallivraison;
import setch.connection.connection;

public class livraisonservice implements setch.Dao.idao<setch.beans.livraison> {
	offreservice offreservice=new offreservice();
	articleservice articleservice=new articleservice();
	familleservice familleservice=new familleservice();
	personneservice personneservice=new personneservice();
	List<setch.beans.offre> t2 =null;
	List<setch.beans.livraison> t3 = new ArrayList<setch.beans.livraison>();
	
	private Statement stmt; 
	public livraisonservice(){
		
	}
	@Override
	public boolean add(livraison obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
			// TODO Auto-generated method stub
			boolean t=true;
			String sql="INSERT INTO livraison VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumerobc()+"','"+obj.getNumerobl()+"',"+obj.getIdfournisseur()+","+obj.getIdarticle()+","+obj.getQuantite()+","+obj.getPu()+",'"+obj.getStatut()+"')";
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

	@Override
	public boolean update(int a, livraison obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraison SET user="+obj.getUser()+",quantite="+obj.getQuantite()+",pu='"+obj.getPu()+"' WHERE id="+a+"";
				System.out.println(sql);
				try {
					int  nbre=stmt.executeUpdate(sql);
					t=true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					t=false;
				}
				finally {
					c1.close();
				}
				return t;
	}
	public boolean update(String a, String bl,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraison SET numerobl='"+bl+"' WHERE numerobl='"+a+"' and user="+user+"";
				try {
					int  nbre=stmt.executeUpdate(sql);
					t=true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					t=false;
				}
				finally {
					c1.close();
				}
				return t;
	}
	public boolean update(String bc,String bl,String date,int fournisseur,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraison SET statut='"+statut+"' WHERE numerobl='"+bl+"' and numerobc='"+bc+"' and date='"+date+"' and fournisseur="+fournisseur+"";
				try {
					int  nbre=stmt.executeUpdate(sql);
					t=true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					t=false;
				}
				finally {
					c1.close();
				}
				return t;
	}
	
	public boolean update(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE livraison SET statut='"+b+"' WHERE numerobl='"+a+"'";
		
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
		return t;
	}


	@Override
	public boolean delete(livraison obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete1(int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM livraison  WHERE quantite=0 and user="+user+"";
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
		return t;
	}
	public boolean delete(int user,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM livraison  WHERE user="+user+" and numerobl='"+statut+"'";
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
		return t;
	}
	public boolean delete(Double id) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM livraison WHERE  id="+id+"";
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
		}
		finally {
			c1.close();
		}
		return t;
	}

	@Override
	public livraison getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.livraison pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM livraison WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		finally {
			c1.close();
		}
		return pers;
	}
	public livraison getByid(Double a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.livraison pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM livraison WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		finally {
			c1.close();
		}
		return pers;
	}
	
	public int getByid1(int a,String numerobl,String numerobc,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.livraison pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM livraison WHERE article="+a+" and numerobc='"+numerobc+"' and numerobl='"+numerobl+"' and user="+user+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{

				pers=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
			per=pers.getIdarticle();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		finally {
			c1.close();
		}
		return per;
	}
	public  totallivraison getLivraison(String bc,int article) {
		connection c1=new connection();
		setch.beans.totallivraison pers = null;
		int i=0;
		Double quantite=0.0;
		Double totalprix=0.0;
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				
				String sql="SELECT * FROM livraison where article="+article+" and  numerobc='"+bc+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						i++;
						quantite=quantite+resultat.getDouble("quantite");
						totalprix=totalprix+resultat.getDouble("pu");
					
					}
					 pers=new setch.beans.totallivraison(article,quantite,totalprix/i);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				
				return pers;
	}
	@Override
	public List<livraison> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
		String sql="SELECT * FROM livraison order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	
	public List<livraison> getAll(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+a+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	public List<String> listearticle(String numerobc) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct article FROM livraison where numerobc='"+numerobc+"'order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					int c=(resultat.getInt("article"));
					
					t.add(String.valueOf(c));
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

	public List<livraison> getAll(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	public List<livraison> getAll(String bl,String bc,int fournisseur,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+bl+"' and numerobc='"+bc+"' and date='"+date+"' and fournisseur="+fournisseur+" order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	public List<totallivraison> getAlltotallivraison(String bc) {
		List<String> liste = new ArrayList<String>();
		liste=this.listearticle(bc);
		List<setch.beans.totallivraison> t = new ArrayList<setch.beans.totallivraison>();
		for(int i=0;i<liste.size();i++) {
			setch.beans.totallivraison c=this.getLivraison(bc, Integer.parseInt(liste.get(i)));
			t.add(c);
		}
		return t;
		
	}
	public List<livraison> getAll1(String bl,String bc,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+bl+"' and numerobc='"+bc+"' and date='"+date+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	public List<livraison> getAll(String a,String b,String fournisseur) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
						t2=offreservice.getAll3(c.getIdarticle(),"actif");
						if(fournisseur.equals(personneservice.getByid(t2.get(0).getIdfournisseur()).getNom())) {
							t.add(c);
						}
						
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
	
	public List<String> lislivraison(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct numerobl FROM livraison where statut='"+a+"'";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=(resultat.getString("numerobl"));
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
	public List<livraison> listelivraisonfournisseur(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<livraison> t = new ArrayList<livraison>();
				String sql="SELECT * FROM livraison where numero='"+a+"'";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
						t2=offreservice.getAll3(c.getIdarticle(),"actif");
						if(b.equals(personneservice.getByid(t2.get(0).getIdfournisseur()).getNom())) {
							t.add(c);
						}
						
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
	public List<livraison> getAll1(String a,int user,String date,int fournisseur, String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraison> t = new ArrayList<setch.beans.livraison>();
				String sql="SELECT * FROM livraison where numerobl='"+a+"' and date='"+date+"' and user="+user+" and fournisseur="+fournisseur+" and statut='"+statut+"' order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
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
	public Double totalqteliv(String a,int article,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t = 0.0;
				String sql="SELECT * FROM livraison where numerobc='"+a+"' and article="+article+" and date<='"+date+"'";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						Double qte=resultat.getDouble("quantite");
						t=t+qte;
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
	public Double totalpuliv(String a,int article,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t = 0.0;
				String sql="SELECT * FROM livraison where numerobc='"+a+"' and article="+article+" and date<='"+date+"'";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					int i=0;
					Double t1=0.0;
					while(resultat.next())
					{
						i++;
						Double pu=resultat.getDouble("pu");
						t1=t1+pu;
					}
					t=t1/i;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				return t;
	}
	public Double totalachat(String date1,String date2,int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM livraison where article="+a+" and date between '"+date1+"' and '"+date2+"'  and numerobl !='en attente'"; 

				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						t=t+resultat.getDouble("quantite");	
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
	public List<String>listefacture(String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				int i=0;
				String sql="SELECT distinct numerobl,date FROM livraison where statut='"+b+"' order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						if(i<30)
						{
						String c=resultat.getString("numerobl");
						t.add(c);
						i++;
						}
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
	public List<String>listefacture() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				int i=0;
				String sql="SELECT distinct numerobl,date FROM livraison  order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						if(i<30)
						{
						String c=resultat.getString("numerobl");
						t.add(c);
						i++;
						}
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
	public List<recaplivraison> getAll2() {
		connection c2=new connection();
		 stmt=c2.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraison> t = new ArrayList<setch.beans.recaplivraison>();

				List<setch.beans.livraison> t1 =null;
				String sql="SELECT DISTINCT fournisseur,numerobc,numerobl,date FROM livraison order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						String a=(resultat.getString("numerobl"));
						String b=(resultat.getString("date"));
						int c=(resultat.getInt("fournisseur"));
						String d=(resultat.getString("numerobc"));
						t3=this.getAll(resultat.getString("numerobl"));
						
						articles article=articleservice.getByid(t3.get(0).getIdarticle());
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
						
						for(int i=0;i<t3.size();i++)
						{
							t2=offreservice.getAll3(t3.get(i).getIdarticle(),"actif");
							total=total+(t3.get(i).getQuantite()*t2.get(0).getPrixvente());
						}
					recaplivraison c1=new recaplivraison(famille.getNom(),d,a,b,c,total,t3.get(0).getStatut());
					t.add(c1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c2.close();
				}
				return t;
	}
	public List<recaplivraison> getAll3(String bc) {
		connection c2=new connection();
		 stmt=c2.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraison> t = new ArrayList<setch.beans.recaplivraison>();

				List<setch.beans.livraison> t1 =null;
				String sql="SELECT DISTINCT date,user,numerobl,numerobc,fournisseur,statut ,sum(quantite*pu) FROM livraison  where numerobc='"+bc+"' group by date,numerobl,numerobc,fournisseur,statut  order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					int i=0;
					while(resultat.next())
					{
						i++;
						String a=(resultat.getString("numerobl"));
						String b=(resultat.getString("date"));
						int c=resultat.getInt("fournisseur");
						String d=(resultat.getString("numerobc"));
						Double total=(resultat.getDouble("SUM(quantite*pu)"));
						String statut=(resultat.getString("statut"));
					recaplivraison c1=new recaplivraison("Type",d,a,b,c,total,statut);
					t.add(c1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c2.close();
				}
				return t;
	}
	
	public List<recaplivraison> getAll2(String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraison> t = new ArrayList<setch.beans.recaplivraison>();
				//List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
				List<setch.beans.livraison> t1 =null;
				List<setch.beans.offre> t2 =null;
				String sql="SELECT * FROM livraison where numerobl='"+numero+"' and statut='en attente' order by date desc";
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
						String a=(resultat1.getString("numerobl"));
						String b=(resultat1.getString("date"));
						String d=(resultat1.getString("statut"));
						String e=(resultat1.getString("numerobc"));
						articles article=articleservice.getByid(resultat1.getInt("article"));
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
							t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
							total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
							recaplivraison c=new recaplivraison(famille.getNom(),e,a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
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
	public List<recaplivraison> getAll(List<String> z ) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.recaplivraison> t = new ArrayList<setch.beans.recaplivraison>();
				List<setch.beans.livraison> t1 =null;
				List<setch.beans.offre> t2 =null;
				for(int i=0;i<z.size();i++) {
					String sql="SELECT * FROM livraison where numero='"+z.get(i)+"'  order by date desc";
					try {
						ResultSet resultat1=stmt.executeQuery(sql);
						while(resultat1.next())
						{
							String a=(resultat1.getString("numerobl"));
							String b=(resultat1.getString("date"));
							String d=(resultat1.getString("statut"));
							String e=(resultat1.getString("numerobc"));
							articles article=articleservice.getByid(resultat1.getInt("article"));
							famille famille=familleservice.getByid(article.getIdfamille());
							Double total=0.0;
								t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
								total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
								recaplivraison c=new recaplivraison(famille.getNom(),e,a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
								t.add(c);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					finally {
						c1.close();
					}
				}
				return t;
	}
	public List<String> getAll2(List<setch.beans.recaplivraison> t0) {
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				for(int i=0;i<t0.size();i++) {
					if(t.contains(t0.get(i).getFournisseur())==false) {
						t.add(personneservice.getByid(t0.get(i).getFournisseur()).getNom());
					}
				}
				
				return t;
	}
	public List<recaplivraison> getAll2(List<String> t0,List<setch.beans.recaplivraison> t1) {
		// TODO Auto-generated method stub
		List<setch.beans.recaplivraison> t = new ArrayList<setch.beans.recaplivraison>();
				for(int i=0;i<t0.size();i++) {
					double val=0;
					int y=0;
					for(y=0;y<t1.size();y++) {
						if(personneservice.getByid(t1.get(y).getFournisseur()).getNom().equals(t0.get(i))) {
							val=val+t1.get(y).getMontant();
						}
					}
					recaplivraison c=new recaplivraison(t1.get(0).getType(),t1.get(0).getNumerobc(),t1.get(0).getNumerobl(),t1.get(0).getDate(),personneservice.getByid(t1.get(y).getFournisseur()).getId(),val,t1.get(i).getStatut());
					t.add(c);
				}
				return t;
	}
	public Double CMUP(String date,int idarticle) {
		connection c1=new connection();
		 stmt=c1.connecter();
		Double val=0.0;
		Double quantite=0.0;
		Double numerateur=0.0;
		String sql="SELECT * FROM livraison where date <='"+date+"' and article="+idarticle+" ";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
				numerateur=numerateur+(c.getQuantite()*c.getPu());
				quantite=quantite+c.getQuantite();
				}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}
if(quantite>0) {
	val=numerateur/quantite;
}
		return val;
	}
	public Double CMUP1(String date,int idarticle) {
		connection c1=new connection();
		 stmt=c1.connecter();
		Double val=0.0;
		Double quantite=0.0;
		Double numerateur=0.0;
		int i=0;
		String sql="SELECT * FROM livraison where date <='"+date+"' and article="+idarticle+" ";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			
			while(resultat.next())
			{
				if(i<5)
				{
				setch.beans.livraison c=new setch.beans.livraison(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobc"),resultat.getString("numerobl"),resultat.getInt("fournisseur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getDouble("pu"),resultat.getString("statut"));
				numerateur=numerateur+(c.getQuantite()*c.getPu());
				quantite=quantite+c.getQuantite();
				i=i++;
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}
		
			if(quantite>0) {
					val=numerateur/quantite;
				}
		return val;
	}
	
	


	
}
