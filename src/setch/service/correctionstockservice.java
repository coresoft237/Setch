package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.besoin;
import setch.beans.correctionstock;
import setch.beans.entreprise;
import setch.beans.famille;
import setch.beans.recapcorrectionstock;
import setch.connection.connection;

public class correctionstockservice implements setch.Dao.idao<setch.beans.correctionstock> {
	private Statement stmt; 
	public correctionstockservice(){
		
	}
	@Override
	public boolean add(correctionstock obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO correctionstock VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumero()+"',"+obj.getArticle()+","+obj.getQuantite()+")";
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
	public boolean update(int a, correctionstock obj) {
		// TODO Auto-generated method stub
		boolean t=true;
		
		return t;
	}
	public boolean update(Double a, correctionstock obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE correctionstock SET user="+obj.getUser()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
		
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
	public boolean update(String numero,int id,String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE correctionstock SET numero='"+numero+"' WHERE numero='"+a+"' and user="+id+"";
		
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
	public boolean delete(correctionstock obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete1(int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM correctionstock  WHERE quantite=0 and user="+user+"";
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
		String sql="DELETE FROM correctionstock WHERE  id="+id+"";
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
	public boolean delete(String id,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM correctionstock WHERE  numero='"+id+"' and user="+user+"";
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
	public correctionstock getByid(int a) {
		setch.beans.correctionstock pers = null;
		
		return pers;
	}
	public correctionstock getByid(Double a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.correctionstock pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM correctionstock WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
		setch.beans.correctionstock pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM correctionstock WHERE article="+a+" and numero='"+numero+"' and user="+user+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				pers=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
			per=pers.getArticle();
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
	public List<correctionstock> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<correctionstock> t = new ArrayList<correctionstock>();
				String sql="SELECT * FROM correctionstock order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.correctionstock c=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<correctionstock> getAll(String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<correctionstock> t = new ArrayList<correctionstock>();
				String sql="SELECT * FROM correctionstock where numero='"+statut+"' order by id desc";				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.correctionstock c=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<correctionstock> getAll1(String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<correctionstock> t = new ArrayList<correctionstock>();
				String sql="SELECT * FROM correctionstock where numero !='"+numero+"' order by id desc";				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.correctionstock c=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<correctionstock> getAll1(String a,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.correctionstock> t = new ArrayList<setch.beans.correctionstock>();
				String sql="SELECT * FROM correctionstock where numero='"+a+"' and user="+user+" order by article asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.correctionstock c=new setch.beans.correctionstock(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<String>listefacture(String b) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<String> t = new ArrayList<String>();
		int i=0;
		String sql="SELECT distinct numero,date FROM correctionstock where numero !='"+b+"' order by date desc";
		
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
	public Double totalcorrectionentree(String date1,String date2,int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM correctionstock where article="+a+" and date between '"+date1+"' and '"+date2+"'  and numero !='en attente' and quantite >=0"; 

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
	public Double totalcorrectionsortie(String date1,String date2,int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM correctionstock where article="+a+" and date between '"+date1+"' and '"+date2+"'  and numero !='en attente' and quantite <0"; 

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
	public List<recapcorrectionstock>recapcorrection() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<recapcorrectionstock> t = new ArrayList<recapcorrectionstock>();
				int i=0;
				String sql="SELECT distinct date,numero FROM correctionstock  order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						if(i<30)
						{
							setch.beans.recapcorrectionstock c=new setch.beans.recapcorrectionstock(resultat.getString("date"),resultat.getString("numero"));
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

}
