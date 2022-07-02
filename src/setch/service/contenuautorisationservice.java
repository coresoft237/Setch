package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.contenuautorisation;
import setch.beans.entreprise;
import setch.connection.connection;

public class contenuautorisationservice implements setch.Dao.idao<setch.beans.contenuautorisation> {
	private Statement stmt; 
	public contenuautorisationservice(){
		
	}
	@Override
	public boolean add(contenuautorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO contenuautorisation VALUES(NULL,"+obj.getUser()+","+obj.getIdautorisation()+",'"+obj.getTable()+"','"+obj.getVisualiser()+"','"+obj.getCreer()+"','"+obj.getModifier()+"','"+obj.getSupprimer()+"','"+obj.getImprimer()+"')";		
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
	public boolean update(int a, contenuautorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE contenuautorisation SET user="+obj.getUser()+",idautorisation="+obj.getIdautorisation()+",tabbles='"+obj.getTable()+"',visualiser='"+obj.getVisualiser()+"',creer='"+obj.getCreer()+"',modifier='"+obj.getModifier()+"',supprimer='"+obj.getSupprimer()+"',imprimer='"+obj.getImprimer()+"' WHERE id="+a+"";
		
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
	public boolean update1(String anciennom,String nouveaunom,contenuautorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE contenuautorisation SET user="+obj.getUser()+",tables='"+nouveaunom+"' WHERE tables= 'etatvente"+anciennom+"'";
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
	public boolean updateActiveAll(int a, contenuautorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE contenuautorisation SET user="+obj.getUser()+",idautorisation="+obj.getIdautorisation()+",visualiser='"+obj.getVisualiser()+"',creer='"+obj.getCreer()+"',modifier='"+obj.getModifier()+"',supprimer='"+obj.getSupprimer()+"',imprimer='"+obj.getImprimer()+"' WHERE id="+a+"";
		
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
	public boolean update(int a, String colonne,String valeur) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE contenuautorisation SET "+colonne+"='"+valeur+"' WHERE id="+a+"";
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
	public boolean delete(contenuautorisation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public contenuautorisation getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.contenuautorisation pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM contenuautorisation WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.contenuautorisation(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idautorisation"),resultat.getString("tables"),resultat.getString("visualiser"),resultat.getString("creer"),resultat.getString("modifier"),resultat.getString("supprimer"),resultat.getString("imprimer"));
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
	public List<contenuautorisation> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<contenuautorisation> t = new ArrayList<contenuautorisation>();
				String sql="SELECT * FROM contenuautorisation order by tables asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.contenuautorisation c=new setch.beans.contenuautorisation(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idautorisation"),resultat.getString("tables"),resultat.getString("visualiser"),resultat.getString("creer"),resultat.getString("modifier"),resultat.getString("supprimer"),resultat.getString("imprimer"));
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
	public List<contenuautorisation> getAll(int id) {
		connection c1=new connection();
		 stmt=c1.connecter();
		
				List<contenuautorisation> t = new ArrayList<contenuautorisation>();
				String sql="SELECT * FROM contenuautorisation where idautorisation="+id+" order by tables asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.contenuautorisation c=new setch.beans.contenuautorisation(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idautorisation"),resultat.getString("tables"),resultat.getString("visualiser"),resultat.getString("creer"),resultat.getString("modifier"),resultat.getString("supprimer"),resultat.getString("imprimer"));
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
