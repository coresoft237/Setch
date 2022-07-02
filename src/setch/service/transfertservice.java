package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.correctionstock;
import setch.beans.entreprise;
import setch.beans.famille;
import setch.beans.recaplivraison;
import setch.beans.recaptransfert;
import setch.beans.transfert;
import setch.connection.connection;

public class transfertservice implements setch.Dao.idao<setch.beans.transfert> {
	private Statement stmt; 
	public transfertservice(){
		
	}
	@Override
	public boolean add(transfert obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO transfert VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getNumero()+"',"+obj.getEmetteur()+","+obj.getRecepteur()+","+obj.getArticle()+","+obj.getQuantite()+")";
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
	public boolean update(int a, transfert obj) {
		// TODO Auto-generated method stub
		boolean t=true;
		
		return t;
	}
	public boolean update1(Double a, transfert obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE transfert SET user="+obj.getUser()+",date='"+obj.getDate()+"',emetteur="+obj.getEmetteur()+",recepteur="+obj.getRecepteur()+",article="+obj.getArticle()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
		
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
	public boolean update1(transfert obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE transfert SET date='"+obj.getDate()+"',emetteur="+obj.getEmetteur()+",recepteur="+obj.getRecepteur()+" WHERE numero='"+obj.getNumero()+"'";
		
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
	public boolean update1(int user,String numero1,String numero2) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE transfert SET numero='"+numero2+"'WHERE user="+user+" and numero='"+numero1+"'";
		
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
	public boolean delete(transfert obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete1( int user) {
		connection c1=new connection();
		stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM transfert  WHERE quantite=0 and user="+user+"";
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
	public boolean delete1(String id, int user) {
		connection c1=new connection();
		stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM transfert  WHERE numero='"+id+"' and user="+user+"";
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
		String sql="DELETE FROM transfert WHERE  id="+id+"";
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
	public transfert getByid(int a) {
		setch.beans.transfert pers = null;
		return pers;
	}
	public transfert getByid(double a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.transfert pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM transfert WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.transfert(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("emetteur"),resultat.getInt("recepteur"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
		setch.beans.transfert pers = null;
		int per=0;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM transfert WHERE article="+a+" and numero='"+numero+"' and user="+user+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				pers=new setch.beans.transfert(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("emetteur"),resultat.getInt("recepteur"),resultat.getInt("article"),resultat.getDouble("quantite"));
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
	public List<transfert> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<transfert> t = new ArrayList<transfert>();
				String sql="SELECT * FROM transfert order by id asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
					setch.beans.transfert pers=new setch.beans.transfert(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("emetteur"),resultat.getInt("recepteur"),resultat.getInt("article"),resultat.getDouble("quantite"));	
					t.add(pers);
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
	public List<transfert> getAll(String numero) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<transfert> t = new ArrayList<transfert>();
				String sql="SELECT * FROM transfert where numero='"+numero+"' order by id asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
					setch.beans.transfert pers=new setch.beans.transfert(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("emetteur"),resultat.getInt("recepteur"),resultat.getInt("article"),resultat.getDouble("quantite"));	
					t.add(pers);
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
	public List<recaptransfert> getAll1() {
		connection c2=new connection();
		stmt=c2.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recaptransfert> t = new ArrayList<setch.beans.recaptransfert>();

				List<setch.beans.transfert> t1 =null;
				String sql="SELECT DISTINCT date,numero,emetteur,recepteur FROM transfert  order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					int i=0;
					while(resultat.next())
					{
						i++;
						String a=(resultat.getString("date"));
						String b=(resultat.getString("numero"));
						int c=(resultat.getInt("emetteur"));
						int d=(resultat.getInt("recepteur"));
					recaptransfert c1=new recaptransfert(a,b,c,d);
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
	public List<recaptransfert> getAll1(String numero) {
		// TODO Auto-generated method stub
		connection c2=new connection();
		stmt=c2.connecter();
				List<setch.beans.recaptransfert> t = new ArrayList<setch.beans.recaptransfert>();

				List<setch.beans.transfert> t1 =null;
				String sql="SELECT DISTINCT date,numero,emetteur,recepteur FROM transfert where numero='"+numero+"'  order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					int i=0;
					while(resultat.next())
					{
						i++;
						String a=(resultat.getString("date"));
						String b=(resultat.getString("numero"));
						int c=(resultat.getInt("emetteur"));
						int d=(resultat.getInt("recepteur"));
					recaptransfert c1=new recaptransfert(a,b,c,d);
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
	public List<transfert> getAll1(String a,int user) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.transfert> t = new ArrayList<setch.beans.transfert>();
				String sql="SELECT * FROM transfert where numero='"+a+"' and user="+user+" order by id asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.transfert pers=new setch.beans.transfert(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("numero"),resultat.getInt("emetteur"),resultat.getInt("recepteur"),resultat.getInt("article"),resultat.getDouble("quantite"));	
						t.add(pers);
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
				String sql="SELECT distinct numero,date FROM transfert where numero !='"+b+"' order by date desc";
				
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
	
}
