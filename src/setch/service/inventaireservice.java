package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.besoin;
import setch.beans.famille;
import setch.beans.inventaire;
import setch.beans.recapcommande;
import setch.beans.recapinventaire;
import setch.beans.recaplivraison;
import setch.connection.connection;

public class inventaireservice implements setch.Dao.idao<setch.beans.inventaire> {
	private Statement stmt;
	@Override
	public boolean add(inventaire obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
			// TODO Auto-generated method stub
			boolean t=true;
			String sql="INSERT INTO inventaire VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumero()+"',"+obj.getIdarticle()+","+obj.getQuantite()+")";
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
	public boolean update(int a, inventaire obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
			// TODO Auto-generated method stub
					boolean t=true;
					String sql ="UPDATE inventaire SET user="+obj.getUser()+",date='"+obj.getDate()+"',numero='"+obj.getNumero()+"',article="+obj.getIdarticle()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
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
		String sql ="UPDATE inventaire SET numero='"+b+"' WHERE user="+user+" and numero='"+a+"'";
		
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
	public boolean delete(inventaire obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM inventaire WHERE id="+obj.getId()+"";
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
		String sql="DELETE FROM inventaire WHERE id="+id+"";
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
	public inventaire getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.inventaire pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM inventaire WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.inventaire(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
		setch.beans.inventaire pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM inventaire WHERE article="+a+" and numero='"+numero+"' and user="+user+"";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				pers=new setch.beans.inventaire(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<inventaire> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.inventaire> t = new ArrayList<setch.beans.inventaire>();
		String sql="SELECT * FROM inventaire order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.inventaire c=new setch.beans.inventaire(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<inventaire> getAll(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		ResultSet resultat=null;
				List<setch.beans.inventaire> t = new ArrayList<setch.beans.inventaire>();
				String sql="SELECT * FROM inventaire where numero='"+a+"' order by article asc";
				try {
					 resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.inventaire c=new setch.beans.inventaire(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<inventaire> getAll1(String a,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.inventaire> t = new ArrayList<setch.beans.inventaire>();
				String sql="SELECT * FROM inventaire where numero='"+a+"' and user="+user+" order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.inventaire c=new setch.beans.inventaire(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public String lastinventaire() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct numero FROM inventaire where numero !='en attente'  order by date desc";
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
	public List<recapinventaire> recapinventaire() {
			connection c2=new connection();
			 stmt=c2.connecter();
					List<setch.beans.recapinventaire> t = new ArrayList<setch.beans.recapinventaire>();

					List<setch.beans.livraison> t1 =null;
					String sql="SELECT DISTINCT numero,date FROM inventaire order by date desc";
					
					try {
						ResultSet resultat=stmt.executeQuery(sql);
						while(resultat.next())
						{
							String a=(resultat.getString("numero"));
							String b=(resultat.getString("date"));
						recapinventaire c1=new recapinventaire(a,b);
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

}
