package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.besoin;
import setch.beans.commande2;
import setch.beans.contenuachat;
import setch.beans.famille;
import setch.beans.offre;
import setch.beans.prixvente;
import setch.beans.recapbesoin;
import setch.beans.recapcommande;
import setch.beans.recapoffre;
import setch.connection.connection;

public class besoinservice  implements setch.Dao.idao<setch.beans.besoin> {
	offreservice offreservice=new offreservice();
	articleservice articleservice=new articleservice();
	familleservice familleservice=new familleservice();
	personneservice personneservice=new personneservice();
	setch.service.contenuachatservice contenuachatservice=new setch.service.contenuachatservice();
	List<setch.beans.offre> t2 =null;
	List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
	 setch.beans.contenuachat contenuachat=null;
	 setch.beans.offre offre=null;
	private Statement stmt; 
	public besoinservice(){
		
	}
	@Override
	public boolean add(besoin obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
			// TODO Auto-generated method stub
			boolean t=true;
			String sql="INSERT INTO besoin VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumero()+"',"+obj.getIdarticle()+","+obj.getPrix()+","+obj.getQuantite()+",'"+obj.getStatut()+"')";
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
	public boolean update(int a, besoin obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE besoin SET user="+obj.getUser()+",date='"+obj.getDate()+"',numero='"+obj.getNumero()+"',article="+obj.getIdarticle()+",prix="+obj.getPrix()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
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
	public boolean update(Double a, besoin obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE besoin SET user="+obj.getUser()+",date='"+obj.getDate()+"',numero='"+obj.getNumero()+"',article="+obj.getIdarticle()+",prix="+obj.getPrix()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
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
	public boolean update(int user,String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE besoin SET numero='"+b+"' WHERE user="+user+" and numero='"+a+"'";
		
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
	public boolean update1(String numero,String fournisseur,String statut) {
		connection c2=new connection();
		Statement  stmt1=c2.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		t3=this.getAll(numero);
		for(int i=0;i<t3.size();i++)
		{
			t2=offreservice.getAll3(t3.get(i).getIdarticle(),"actif");
			if(personneservice.getByid(t2.get(0).getIdfournisseur()).getNom().equals(fournisseur)) {
				String sql ="UPDATE besoin SET statut='"+statut+"' WHERE numero='"+numero+"' and id="+t3.get(i).getId()+"";
				try {
					int  nbre=stmt1.executeUpdate(sql);
					t=true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					t=false;
				}
				finally {
					c2.close();
				}
				
			}
			
		}	
		return t;
	}
	public boolean update(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE besoin SET statut='"+b+"' WHERE numero='"+a+"'";
		
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
	public boolean delete(besoin obj) {
		
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete1(int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM besoin  WHERE quantite=0 and user="+user+"";
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
		String sql="DELETE FROM besoin  WHERE user="+user+" and numero='"+statut+"'";
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
		String sql="DELETE FROM besoin WHERE  id="+id+"";
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
	public besoin getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.besoin pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM besoin WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public besoin getByid(Double a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.besoin pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM besoin WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
		setch.beans.besoin pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM besoin WHERE article="+a+" and numero='"+numero+"' and user="+user+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public int getByid1(int a,String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.besoin pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM besoin WHERE article="+a+" and numero='"+numero+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public besoin getByid2(int a,String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.besoin pers = null;
		
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM besoin WHERE article="+a+" and numero='"+numero+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
			
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
	@Override
	public List<besoin> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.besoin> t = new ArrayList<setch.beans.besoin>();
		String sql="SELECT * FROM besoin order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public List<besoin> getAll(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		ResultSet resultat=null;
				List<setch.beans.besoin> t = new ArrayList<setch.beans.besoin>();
				String sql="SELECT * FROM besoin where numero='"+a+"' order by article asc";
				try {
					 resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
					t.add(c);
					}
					resultat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}		
				return t;
	}
	public List<besoin> getAll(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.besoin> t = new ArrayList<setch.beans.besoin>();
				String sql="SELECT * FROM besoin where numero='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public List<besoin> getAll(String a,String b,int fournisseur) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.besoin> t = new ArrayList<setch.beans.besoin>();
				String sql="SELECT * FROM besoin where numero='"+a+"' and statut='"+b+"' order by article asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
						t2=offreservice.getAll3(c.getIdarticle(),"actif");
						if(fournisseur==(personneservice.getByid(t2.get(0).getIdfournisseur()).getId())) {
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
	public String lastfacture(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct numero FROM besoin where numero  like '"+a+"%' order by date desc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=(resultat.getString("numero"));
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				if(t.size()>0)
				{
				return t.get(0);
				}
				else {
					return "vide";
				}
				
	}
	
	public List<String> lisbesoin(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct numero FROM besoin where statut='"+a+"'";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=(resultat.getString("numero"));
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
	public List<String> listeprix(String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct prix FROM besoin where numero='"+numero+"'order by prix asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					int c=(resultat.getInt("prix"));
					
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
	public List<besoin> listebesoinfournisseur(String a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<besoin> t = new ArrayList<besoin>();
				String sql="SELECT * FROM besoin where numero='"+a+"'";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public List<besoin> getAll1(String a,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.besoin> t = new ArrayList<setch.beans.besoin>();
				String sql="SELECT * FROM besoin where numero='"+a+"' and user="+user+" order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
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
	public List<commande2> getAll(String numero,int idfournisseur,String typecontenu) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.commande2> t = new ArrayList<setch.beans.commande2>();
				String sql="SELECT * FROM besoin where numero='"+numero+"'  order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						Double val=0.0;
		            	Double val2=0.0;
		            	String val3="B/1";
		            	String g="";
						setch.beans.besoin c=new setch.beans.besoin(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getInt("prix"),resultat.getDouble("quantite"),resultat.getString("statut"));
						if(offreservice.getByid(c.getPrix()).getIdfournisseur()==idfournisseur) {
							if(typecontenu.equals("unite")) {
								 g=""+articleservice.getByid(c.getIdarticle()).getNom()+"-"+val3+"";
								val=offreservice.getByid(c.getPrix()).getPrixvente();
			            		val2=c.getQuantite();
			            		commande2 k=new commande2(c.getUser(),c.getDate(),g,val2,val);
								t.add(k);
								
							}
							else {
								contenuachat=contenuachatservice.getByid1(c.getIdarticle());
								if(contenuachat==null) {
									g=""+articleservice.getByid(c.getIdarticle()).getNom()+"-"+val3+"";
									val=offreservice.getByid(c.getPrix()).getPrixvente();
				            		val2=c.getQuantite();
				            		commande2 k=new commande2(c.getUser(),c.getDate(),g,val2,val);
									t.add(k);
								}
								else {
									val3="B"+contenuachat.getQuantite()+"";
									 g=""+articleservice.getByid(c.getIdarticle()).getNom()+"-"+val3+"";
					            	val=offreservice.getByid(c.getPrix()).getPrixvente()*contenuachat.getQuantite();
				            		val2=c.getQuantite()/contenuachat.getQuantite();
				            		commande2 k=new commande2(c.getUser(),c.getDate(),g,val2,val);
									t.add(k);
								}
								
							}
							
							
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
	public List<String>listefacture(String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				int i=0;
				String sql="SELECT distinct numero,date FROM besoin where statut='"+b+"' order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						if(i<30)
						{
						String c=resultat.getString("numero");
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
	
	
	public List<recapcommande> getAll2(String numero) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		 stmt=c1.connecter();
				List<setch.beans.recapcommande> t = new ArrayList<setch.beans.recapcommande>();
				//List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
				List<setch.beans.besoin> t1 =null;
				List<setch.beans.offre> t2 =null;
				String sql="SELECT * FROM besoin where numero='"+numero+"' and statut='en attente' order by date desc";
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
						String a=(resultat1.getString("numero"));
						String b=(resultat1.getString("date"));
						String d=(resultat1.getString("statut"));
						articles article=articleservice.getByid(resultat1.getInt("article"));
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
							t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
							total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
							recapcommande c=new recapcommande(famille.getNom(),a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
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
	public List<recapcommande> getAll(List<String> z ) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.recapcommande> t = new ArrayList<setch.beans.recapcommande>();
				List<setch.beans.besoin> t1 =null;
				List<setch.beans.offre> t2 =null;
				for(int i=0;i<z.size();i++) {
					String sql="SELECT * FROM besoin where numero='"+z.get(i)+"' and statut='transforme' order by date desc";
					
					try {
						ResultSet resultat1=stmt.executeQuery(sql);
						while(resultat1.next())
						{
							String a=(resultat1.getString("numero"));
							String b=(resultat1.getString("date"));
							String d=(resultat1.getString("statut"));
							articles article=articleservice.getByid(resultat1.getInt("article"));
							famille famille=familleservice.getByid(article.getIdfamille());
							Double total=0.0;
								t2=offreservice.getAll3(resultat1.getInt("article"),"actif");
								total=total+(resultat1.getDouble("quantite")*t2.get(0).getPrixvente());
								recapcommande c=new recapcommande(famille.getNom(),a,b,personneservice.getByid(t2.get(0).getIdfournisseur()).getId(),total,d);
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
	public List<String> getAll2(List<String> t0) {
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				for(int i=0;i<t0.size();i++) {
					offre=offreservice.getByid(Integer.parseInt(t0.get(i)));
					if(t.contains(String.valueOf(offre.getIdfournisseur()))==false) {
						t.add(String.valueOf(offre.getIdfournisseur()));
					}
				}
				return t;
	}
	public List<recapcommande> getAll2(List<String> t0,List<setch.beans.recapcommande> t1) {
		// TODO Auto-generated method stub
		List<setch.beans.recapcommande> t = new ArrayList<setch.beans.recapcommande>();
				for(int i=0;i<t0.size();i++) {
					double val=0;
					for(int y=0;y<t1.size();y++) {
						String a=String.valueOf(t1.get(y).getFournisseur());
						if(a.equals(t0.get(i))) {
							val=val+t1.get(y).getMontant();
						}
					}
					recapcommande c=new recapcommande(t1.get(i).getType(),t1.get(i).getNumero(),t1.get(i).getDate(),Integer.parseInt(t0.get(i)),val,t1.get(i).getStatut());
					t.add(c);
				}
				return t;
	}
	
	public Double qtecde(String a,int article) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t = 0.0;
				String sql="SELECT * FROM besoin where numero='"+a+"' and article="+article+" and statut='transforme'";
				
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
	public int pucde(String a,int article) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				int t = 0;
				String sql="SELECT * FROM besoin where numero='"+a+"' and article="+article+" and statut='transforme'";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);;
					while(resultat.next())
					{
						t=resultat.getInt("prix");
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
