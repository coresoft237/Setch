package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.offre;
import setch.beans.prixvente;
import setch.beans.recapoffre;
import setch.connection.connection;

public class offreservice  implements setch.Dao.idao<setch.beans.offre> {
	private Statement stmt; 
	public offreservice(){
		
	}
	@Override
	public boolean add(offre obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO offre VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumero()+"',"+obj.getIdfournisseur()+","+obj.getIdarticle()+","+obj.getPrixvente()+",'"+obj.getStatut()+"')";
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
	public boolean update(int a, offre obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE offre SET user="+obj.getUser()+",date='"+obj.getDate()+"',numero='"+obj.getNumero()+"',idfournisseur="+obj.getIdfournisseur()+",idarticle="+obj.getIdarticle()+",prixvente="+obj.getPrixvente()+",statut='"+obj.getStatut()+"' WHERE id="+a+"";
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
		String sql ="UPDATE offre SET numero='"+b+"' WHERE user="+user+" and numero='"+a+"'";
		
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
	public boolean delete(offre obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete(int user,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM offre WHERE user="+user+" and numero='"+statut+"'";
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
	
	public boolean delete(int id) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM offre WHERE  id="+id+"";
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
	public offre getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.offre pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM offre WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.offre(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("idfournisseur"),resultat.getInt("idarticle"),resultat.getDouble("prixvente"),resultat.getString("statut"));
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
	public List<offre> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.offre> t = new ArrayList<setch.beans.offre>();
				String sql="SELECT * FROM offre order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.offre c=new setch.beans.offre(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("idfournisseur"),resultat.getInt("idarticle"),resultat.getDouble("prixvente"),resultat.getString("statut"));
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
	public List<offre> getAll(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.offre> t = new ArrayList<setch.beans.offre>();
				String sql="SELECT * FROM offre where numero='"+a+"' order by id desc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.offre c=new setch.beans.offre(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("idfournisseur"),resultat.getInt("idarticle"),resultat.getDouble("prixvente"),resultat.getString("statut"));
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
	
	public List<offre> getAll1(String a,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.offre> t = new ArrayList<setch.beans.offre>();
				String sql="SELECT * FROM offre where numero='"+a+"' and user="+user+" order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.offre c=new setch.beans.offre(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("idfournisseur"),resultat.getInt("idarticle"),resultat.getDouble("prixvente"),resultat.getString("statut"));
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
	public List<offre> getAll(String numero,int idfournisseur) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.offre> t = new ArrayList<setch.beans.offre>();
				String sql="SELECT * FROM offre where numero='"+numero+"' and idfournisseur="+idfournisseur+" order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.offre c=new setch.beans.offre(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("idfournisseur"),resultat.getInt("idarticle"),resultat.getDouble("prixvente"),resultat.getString("statut"));
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
	public List<recapoffre> getAll2() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.recapoffre> t = new ArrayList<setch.beans.recapoffre>();
				String sql="SELECT DISTINCT numero,date,idfournisseur FROM offre order by date desc";		
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.recapoffre c=new setch.beans.recapoffre(resultat.getString("numero"),resultat.getString("date"),resultat.getInt("idfournisseur"));
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
	public List<offre> getAll3(int a,String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.offre> t = new ArrayList<setch.beans.offre>();
				String sql="SELECT * FROM offre where idarticle="+a+" and statut='"+b+"' and prixvente>0 order by prixvente asc";
				try {
					ResultSet resultat2=stmt.executeQuery(sql);
					while(resultat2.next())
					{
						
					setch.beans.offre c=new setch.beans.offre(resultat2.getInt("id"),resultat2.getInt("user"),resultat2.getString("date"),resultat2.getString("numero"),resultat2.getInt("idfournisseur"),resultat2.getInt("idarticle"),resultat2.getDouble("prixvente"),resultat2.getString("statut"));
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

	
}
