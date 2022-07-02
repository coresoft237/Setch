package setch.service;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import setch.beans.vente;
import setch.connection.connection;

public class venteservice implements setch.Dao.idao<setch.beans.vente> {

	setch.service.reductionservice reductionservice=new setch.service.reductionservice();
	setch.service.articleservice articleservice=new setch.service.articleservice();
	familleservice familleservice=new familleservice();
	setch.beans.reductionfacture reduction=null;
	setch.beans.reductionfacture reduction1=null;
	setch.beans.famille famille=null;
	setch.beans.articles article=null;
	private Statement stmt; 
	public venteservice(){
		
	}
	@Override
	public boolean add(vente obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql="INSERT INTO vente VALUES(NULL,"+obj.getIduser()+",'"+obj.getFacture()+"','"+obj.getDate()+"',"+obj.getIdpatient()+","+obj.getIdprescripteur()+","+obj.getIdarticle()+","+obj.getQuantite()+","+obj.getIdprixvente()+",'"+obj.getStatut()+"')";
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
	public boolean update(int a, vente obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE vente SET facture='"+obj.getFacture()+"',patient="+obj.getIdpatient()+",patient="+obj.getIdpatient()+",article="+obj.getIdarticle()+",quantite="+obj.getQuantite()+",statut='"+obj.getStatut()+"' WHERE id="+a+"";
				
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
	public boolean update(String facture,String date,String statut,int iduser,String statut1) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE vente SET facture='"+facture+"',date='"+date+"',statut='"+statut1+"' WHERE statut='"+statut+"' and user="+iduser+"";
				
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
	public boolean delete(vente obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM vente WHERE id="+obj.getId();
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
	
	public boolean delete1(vente obj,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM vente WHERE id="+obj.getId()+" and facture='"+statut+"'";
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
		String sql="DELETE FROM vente WHERE user="+user+" and statut='"+statut+"'";
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
	public vente getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.vente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public vente getByid(Double a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.vente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public vente getByfactureandarticle(String facture,int article) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.vente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE facture='"+facture+"' and article="+article+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getByfacture(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE facture='"+facture+"' order by date desc";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getByfacture1(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE facture='"+facture+"' order by id asc";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getByfacture2(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE facture='"+facture+"'  and quantite>0 ";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getByfacture(int idarticle,int iduser,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE article="+idarticle+" and user="+iduser+" and statut='"+statut+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getByfacture(int iduser,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM vente WHERE  user="+iduser+" and statut='"+statut+"' order by quantite desc";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	
	@Override
	public List<vente> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente order by article asc";
		
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getAll(String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente where statut='"+statut+"' order by article asc";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getAll(String statut,int iduser) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente where statut='"+statut+"' and user="+iduser+" order by article asc";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getAll1(String statut,int iduser) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente where statut='"+statut+"' and user="+iduser+" and quantite>0 order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getAllpatient(String statut,int idpatient) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente where statut='"+statut+"' and patient="+idpatient+" order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<vente> getAllprescripteur(String statut,int idprescripteur) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
		String sql="SELECT * FROM vente where statut='"+statut+"' and prescripteur="+idprescripteur+" order by article asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<String>getfacture(String date1,String date2,String statut,String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct facture FROM vente where facture like '%"+a+"%' AND date between '"+date1+"' and '"+date2+"'  and statut !='"+statut+"'"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=resultat.getString("facture");
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
	public List<setch.beans.vente>detailperiodique(String date1,String date2,String statut,String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT distinct facture FROM vente where facture like '%"+a+"%' AND date between '"+date1+"' and '"+date2+"'  and statut='"+statut+"'"; 

				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public Double totalvente(String date1,String date2,int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM vente where article="+a+" and date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59'  and facture !='en attente'"; 

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
	public List<String>famille(String date1,String date2,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct facture FROM vente where  date between '"+date1+"' and '"+date2+"'  and statut='"+statut+"'"; 

				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
						famille=familleservice.getByid(articleservice.getByid(c.getIdarticle()).getIdfamille());
						if(t.contains(famille.getNom())==false) {
							t.add(famille.getNom());
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
	public List<setch.beans.vente>getfacture1(String date1,String date2,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT * FROM vente where date between '"+date1+"' and '"+date2+"'  and statut !='"+statut+"'"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<setch.beans.vente>getfacture1(String date1,String date2,int idarticle) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT * FROM vente where date between '"+date1+"' and '"+date2+"'  and article="+idarticle+""; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<setch.beans.vente>getfacture2(String date1,String date2,int idarticle) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT * FROM vente where date between '"+date1+"' and '"+date2+"'  and article="+idarticle+" and statut='facture'"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<String>getfacture1(String date1,String date2) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct article FROM vente where date between '"+date1+"' and '"+date2+"' and statut='facture' ";
				System.out.println(sql);
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						t.add(String.valueOf(resultat.getInt("article")));
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
	public List<setch.beans.vente>getfacture1(List<setch.beans.vente> t,int s) {
		List<setch.beans.vente> t1 = new ArrayList<setch.beans.vente>();
		for(int i=0;i<t.size();i++)
		{
			article=articleservice.getByid(t.get(i).getIdarticle());
			if(article.getIdfamille()==s)
			{
				t1.add(t.get(i));
			}
		}
		return t1;
	}
	public List<setch.beans.vente>getfacture1(int idprescripteur,String date1,String date2,String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT * FROM vente where date between '"+date1+"' and '"+date2+"'  and statut !='"+statut+"' and prescripteur="+idprescripteur+" order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<setch.beans.vente>getfacture1(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.vente> t = new ArrayList<setch.beans.vente>();
				String sql="SELECT * FROM vente where facture ='"+facture+"'  and quantite>0"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
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
	public List<String>getfacture2(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				setch.service.articleservice articleservice=new setch.service.articleservice();
				String sql="SELECT * FROM vente where facture ='"+facture+"'  and quantite>0"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
						t.add(String.valueOf(c.getIdarticle()));						
						t.add(String.valueOf(c.getQuantite()));
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
	public List<String>getfacture(String statut,String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct facture FROM vente where facture like '%"+a+"%' and  statut !='"+statut+"' order by date desc"; 
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					String c=resultat.getString("facture");
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
	public Double getfacture(int article,String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t=0.0;
				String sql="SELECT * FROM vente where facture like '%"+facture+"%' and  article="+article+" and quantite<0 order by date desc"; 
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.vente c=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
					t=t+c.getQuantite();
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
	public List<String>getfacture(String a,List<String> listarticle) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				for(int i=0;i<listarticle.size();i++)
				{
					Double qte=0.0;
				int id=Integer.parseInt(listarticle.get(i));
				
				String sql="SELECT * FROM vente where facture like '%"+a+"%' and article="+id+" and quantite<0 order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{	
					qte =qte+resultat.getDouble("quantite");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				t.add(listarticle.get(i));
				t.add(String.valueOf(qte));
				}
				return t;	
	}
	public List<String>getfacture(String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<String> t = new ArrayList<String>();
				String sql="SELECT distinct article FROM vente where facture like '%"+a+"%' and quantite<0 order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{	
					t.add(resultat.getString("article"));
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
	public  double gettotalfacture(String facture) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				double t =0;
				reduction1=new setch.beans.reductionfacture();
				reduction1.setReduction(0.0);
				//------reduction
				reduction=reductionservice.getByfacture(facture);
				if(reduction==null)
				{
					reduction=new setch.beans.reductionfacture();
					reduction.setReduction(0.0);
				}
				//----Reduction ancien
				String[] a=facture.split("/");
				if((a[1].equals("RN"))||(a[1].equals("RS")))
				{
					setch.beans.vente vt=this.getByid(Double.parseDouble(a[2]));
					reduction1=reductionservice.getByfacture(vt.getFacture());
					if(reduction1==null)
					{
						reduction1=new setch.beans.reductionfacture();
						reduction1.setReduction(0.0);
					}
				}
				//----
				setch.beans.vente pers = null;
				setch.service.prixventeservice pvservice=new setch.service.prixventeservice();
				String sql="SELECT *  FROM vente where facture='"+facture+"'"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
					pers=new setch.beans.vente(resultat.getDouble("id"),resultat.getInt("user"),resultat.getString("facture"),resultat.getString("date"),resultat.getInt("patient"),resultat.getInt("prescripteur"),resultat.getInt("article"),resultat.getDouble("quantite"),resultat.getInt("prixvente"),resultat.getString("statut"));
					if(pers.getQuantite()<0)
					{
						t=t+(pvservice.getByid(pers.getIdprixvente()).getPrixvente()*pers.getQuantite()*((100-reduction1.getReduction())/100));
					}
					else
					{
						t=t+(pvservice.getByid(pers.getIdprixvente()).getPrixvente()*pers.getQuantite()*((100-reduction.getReduction())/100));
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
