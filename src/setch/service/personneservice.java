package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.personne;
import setch.connection.connection;

public class personneservice implements setch.Dao.idao<setch.beans.personne> {
	private Statement stmt; 
	public personneservice(){
		
	}
	@Override
	public boolean add(personne obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO personne VALUES(NULL,'"+obj.getNom()+"','"+obj.getPhone()+"','"+obj.getStatut()+"','"+obj.getTitre()+"')";
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
	public boolean update(int a, personne obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE personne SET nom='"+obj.getNom()+"',phone='"+obj.getPhone()+"',statut='"+obj.getStatut()+"',titre='"+obj.getTitre()+"' WHERE id="+a+"";
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
	public boolean delete(personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public personne getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.personne pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM personne WHERE id="+a+"";


		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
	public personne getByid(String patient) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.personne pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM personne WHERE nom='"+patient+"'";


		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
	public personne getByid(String patient,String titre) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.personne pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM personne WHERE nom='"+patient+"' and titre='"+titre+"'";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
	public List<personne> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.personne> t = new ArrayList<setch.beans.personne>();
				String sql="SELECT * FROM personne order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.personne c=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
	public List<personne> getAll1() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.personne> t = new ArrayList<setch.beans.personne>();
				String sql="SELECT * FROM personne where phone='000000000'order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.personne c=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
	public List<personne> getAll(String titre,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.personne> t = new ArrayList<setch.beans.personne>();
				String sql="SELECT * FROM personne where statut='"+statut+"' and titre='"+titre+"' order by nom asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.personne c=new setch.beans.personne(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("phone"),resultat.getString("statut"),resultat.getString("titre"));
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
