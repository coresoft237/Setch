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
import setch.beans.livraisonvente;
import setch.beans.offre;
import setch.beans.prixvente;
import setch.beans.recapbesoin;
import setch.beans.recapcommande;
import setch.beans.recaplivraison;
import setch.beans.recaplivraisonvente;
import setch.beans.recapoffre;
import setch.connection.connection;

public class livraisonventeservice implements setch.Dao.idao<setch.beans.livraisonvente> {
	offreservice offreservice=new offreservice();
	articleservice articleservice=new articleservice();
	familleservice familleservice=new familleservice();
	personneservice personneservice=new personneservice();
	List<setch.beans.offre> t2 =null;
	List<livraisonvente> t3 = new ArrayList<setch.beans.livraisonvente>();
	
	private Statement stmt; 
	public livraisonventeservice(){
		
	}
	@Override
	public boolean add(livraisonvente obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
			// TODO Auto-generated method stub
			boolean t=true;
			String sql="INSERT INTO livraisonvente VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumerobl()+"',"+obj.getIdclient()+","+obj.getIdarticle()+","+obj.getQuantite()+","+obj.getPu()+",'"+obj.getStatut()+"')";
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
	public boolean update(int a, livraisonvente obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraisonvente SET user="+obj.getUser()+",quantite="+obj.getQuantite()+",pu='"+obj.getPu()+"' WHERE id="+a+"";
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
	public boolean update1(int a, livraisonvente obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraisonvente SET user="+obj.getUser()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
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
				String sql ="UPDATE livraisonvente SET numerobl='"+bl+"' WHERE numerobl='"+a+"' and user="+user+"";
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
	public boolean update(String bl,String date,int client,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE livraisonvente SET statut='"+statut+"' WHERE numerobl='"+bl+"'  and date='"+date+"' and client="+client+"";
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
		String sql ="UPDATE livraisonvente SET statut='"+b+"' WHERE numerobl='"+a+"'";
		
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
	public boolean delete(livraisonvente obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete1(int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM livraisonvente  WHERE quantite=0 and user="+user+"";
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
	public boolean delete1(String valeur) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM livraisonvente  WHERE numerobl='"+valeur+"'";
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
		String sql="DELETE FROM livraisonvente  WHERE user="+user+" and numerobl='"+statut+"'";
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
		String sql="DELETE FROM livraisonvente WHERE  id="+id+"";
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
	public livraisonvente getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.livraisonvente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM livraisonvente WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	
	public int getByid1(int a,String numero,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.livraisonvente pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM livraisonvente WHERE article="+a+" and numerobl='"+numero+"' and user="+user+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				pers=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	@Override
	public List<livraisonvente> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
		String sql="SELECT * FROM livraisonvente order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getlastnumero(String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
		String sql="SELECT * FROM livraisonvente where numerobl !='"+statut+"'";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+a+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll1(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where  statut='"+a+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<String> getAll3(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT DISTINCT numerobl FROM livraisonvente where  statut='"+a+"' order by numerobl desc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=resultat.getString("numerobl");
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
	public List<livraisonvente> getAll(String bl,int client,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+bl+"'  and date='"+date+"' and client="+client+" order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll1(String bl,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+bl+"'  and date='"+date+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll(String a,String b,String client) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
						t2=offreservice.getAll3(c.getIdarticle(),"actif");
						if(client.equals(personneservice.getByid(t2.get(0).getIdfournisseur()).getNom())) {
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
				String sql="SELECT distinct numerobl FROM livraisonvente where statut='"+a+"'";
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
	public List<livraisonvente> listelivraisonclient(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<livraisonvente> t = new ArrayList<livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numero='"+a+"'";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	public List<livraisonvente> getAll1(String a,int user,String date,int client, String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.livraisonvente> t = new ArrayList<setch.beans.livraisonvente>();
				String sql="SELECT * FROM livraisonvente where numerobl='"+a+"' and date='"+date+"' and user="+user+" and client="+client+" and statut='"+statut+"' order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
				String sql="SELECT * FROM livraisonvente where numerobc='"+a+"' and article="+article+" and date<='"+date+"'";
				
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
	
	public Double totalachat(String date1,String date2,int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM livraisonvente where article="+a+" and date between '"+date1+"' and '"+date2+"'  and numerobl !='en attente'"; 

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
				String sql="SELECT distinct numerobl,date FROM livraisonvente where statut='"+b+"' order by date desc";
				
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
				String sql="SELECT distinct numerobl,date FROM livraisonservice  order by date desc";
				
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
	public List<recaplivraisonvente> getAll2() {
		connection c2=new connection();
		 stmt=c2.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraisonvente> t = new ArrayList<setch.beans.recaplivraisonvente>();

				List<setch.beans.livraisonvente> t1 =null;
				String sql="SELECT DISTINCT client,numerobl,date FROM livraisonvente order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						String a=(resultat.getString("numerobl"));
						String b=(resultat.getString("date"));
						int c=(resultat.getInt("client"));
						t3=this.getAll(resultat.getString("numerobl"));
						articles article=articleservice.getByid(t3.get(0).getIdarticle());
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
						
						for(int i=0;i<t3.size();i++)
						{
							t2=offreservice.getAll3(t3.get(i).getIdarticle(),"actif");
							total=total+(t3.get(i).getQuantite()*t2.get(0).getPrixvente());
						}
					recaplivraisonvente c1=new recaplivraisonvente(famille.getNom(),a,b,c,total,t3.get(0).getStatut());
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
	public List<recaplivraisonvente> getAll3() {
		connection c2=new connection();
		prixventeservice prixventeservice=new prixventeservice();
		 stmt=c2.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraisonvente> t = new ArrayList<setch.beans.recaplivraisonvente>();

				List<setch.beans.livraisonvente> t1 =null;
				String sql="SELECT DISTINCT date,user,numerobl,client,statut ,Sum(quantite),pu FROM livraisonvente group by date,numerobl,client,statut  order by date desc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					int i=0;
					while(resultat.next())
					{
						i++;
						String a=(resultat.getString("numerobl"));
						String b=(resultat.getString("date"));
						int c=resultat.getInt("client");
						Double quantite=(resultat.getDouble("Sum(quantite)"));
						int pu=(resultat.getInt("pu"));
						Double prixvente=prixventeservice.getByid(pu).getPrixvente();
						Double total=prixvente*quantite;
						String statut=(resultat.getString("statut"));
					recaplivraisonvente c1=new recaplivraisonvente("Type",a,b,c,total,statut);
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
	
	public List<recaplivraisonvente> getAll2(String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaplivraisonvente> t = new ArrayList<setch.beans.recaplivraisonvente>();
				//List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
				List<setch.beans.livraisonvente> t1 =null;
				List<setch.beans.offre> t2 =null;
				String sql="SELECT * FROM livraisonvente where numerobl='"+numero+"' and statut='en attente' order by date desc";
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
						String a=(resultat1.getString("numerobl"));
						String b=(resultat1.getString("date"));
						String d=(resultat1.getString("statut"));
						articles article=articleservice.getByid(resultat1.getInt("article"));
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
							t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
							total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
							recaplivraisonvente c=new recaplivraisonvente(famille.getNom(),a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
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
	public List<recaplivraisonvente> getAll(List<String> z ) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.recaplivraisonvente> t = new ArrayList<setch.beans.recaplivraisonvente>();
				List<setch.beans.livraisonvente> t1 =null;
				List<setch.beans.offre> t2 =null;
				for(int i=0;i<z.size();i++) {
					String sql="SELECT * FROM livraisonvente where numero='"+z.get(i)+"'  order by date desc";
					try {
						ResultSet resultat1=stmt.executeQuery(sql);
						while(resultat1.next())
						{
							String a=(resultat1.getString("numerobl"));
							String b=(resultat1.getString("date"));
							String d=(resultat1.getString("statut"));
							articles article=articleservice.getByid(resultat1.getInt("article"));
							famille famille=familleservice.getByid(article.getIdfamille());
							Double total=0.0;
								t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
								total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
								recaplivraisonvente c=new recaplivraisonvente(famille.getNom(),a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
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
	public List<String> getAll2(List<setch.beans.recaplivraisonvente> t0) {
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				for(int i=0;i<t0.size();i++) {
					if(t.contains(t0.get(i).getClient())==false) {
						t.add(personneservice.getByid(t0.get(i).getClient()).getNom());
					}
				}
				
				return t;
	}
	public List<recaplivraisonvente> getAll2(List<String> t0,List<setch.beans.recaplivraisonvente> t1) {
		// TODO Auto-generated method stub
		List<setch.beans.recaplivraisonvente> t = new ArrayList<setch.beans.recaplivraisonvente>();
				for(int i=0;i<t0.size();i++) {
					double val=0;
					int y=0;
					for(y=0;y<t1.size();y++) {
						if(personneservice.getByid(t1.get(y).getClient()).getNom().equals(t0.get(i))) {
							val=val+t1.get(y).getMontant();
						}
					}
					recaplivraisonvente c=new recaplivraisonvente(t1.get(0).getType(),t1.get(0).getNumerobl(),t1.get(0).getDate(),personneservice.getByid(t1.get(y).getClient()).getId(),val,t1.get(i).getStatut());
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
		String sql="SELECT * FROM livraisonvente where date <='"+date+"' and article="+idarticle+" and statut='cloture'";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.livraisonvente c=new setch.beans.livraisonvente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numerobl"),resultat.getInt("client"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("pu"),resultat.getString("statut"));
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
	
	


	
}
