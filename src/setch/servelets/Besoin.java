package setch.servelets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.royken.converter.FrenchNumberToWords;
import com.itextpdf.kernel.xmp.impl.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class Bseoin
 */
@WebServlet("/Besoin")
public class Besoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	 private javax.servlet.http.HttpSession compte;
	 setch.service.besoinservice besoinservice=new setch.service.besoinservice();
	 setch.service.recapbesoinservice recapbesoinservice=new setch.service.recapbesoinservice();
	 setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
	 setch.beans.besoin besoin=null;
	 List<setch.beans.recapbesoin> listesynthesebesoin = new ArrayList<setch.beans.recapbesoin>();
	 List<setch.beans.besoin> listebesoin = new ArrayList<setch.beans.besoin>();
	 List<setch.beans.commande2> listebesoin2 = new ArrayList<setch.beans.commande2>();
	 List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	 setch.service.familleservice familleservice=new setch.service.familleservice();
	 List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	 setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	 List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
	 setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
	 setch.service.articleservice articleservice=new setch.service.articleservice();
	 setch.service.contenuachatservice contenuachatservice=new setch.service.contenuachatservice();
	 setch.service.offreservice offreservice=new setch.service.offreservice();
	 List<setch.beans.offre> listeoffre = new ArrayList<setch.beans.offre>();
	 List<String> liste = new ArrayList<String>();
	 List<String> listeprix = new ArrayList<String>();
	 setch.beans.famille famille=null;
	 setch.beans.entreprise entreprise=null;
	 setch.beans.contenuachat contenuachat=null;
	setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
	List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
	 List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
	  setch.service.personneservice personneservice=new setch.service.personneservice();
	 setch.beans.contenuautorisation contenuautorisation=null;
	 setch.beans.personne personne=null;
	 setch.beans.offre offre=null;
	 boolean executionoperation=false;
	 List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
	 public static int flle;
	 private String date;
	 private String number;
	 private String etat="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Besoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		compte = request.getSession();
		 if(option.equals("ListeBesoin"))
		 {
			 request.setAttribute("print","en attente");
			 besoinservice.delete(connect.use.getId(),"en attente");
			 listesynthesebesoin=recapbesoinservice.getAll2();
			 request.setAttribute("Titre","Liste des besoins" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",listesynthesebesoin);
			 request.setAttribute("utilisateur",connect.use);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );

		 }
		 else if(option.equals("ChoixType"))
		 {
			 etat="";
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("Listefamille",Listefamille );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("Titre","Creation besoin" );
			 request.setAttribute("utilisateur",connect.use);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/besoin1.jsp" ).forward( request, response ); 
		 }
		 else if(option.equals("AddBesoin"))
		 {
			 request.setAttribute("print","en attente");
			
			 Listearticle=articleservice.getAll2("actif", Besoin.flle);
			 listebesoin=besoinservice.getAll1("en attente",connect.use.getId());
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("listebesoin",listebesoin);
			 request.setAttribute("famille",Besoin.flle);
			 request.setAttribute("date",date);
			request.setAttribute("option",option);
			 request.setAttribute("listearticle",Listearticle);
			 request.setAttribute("utilisateur",connect.use);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/besoin2.jsp" ).forward( request, response ); 
		 }
		 else if(option.equals("DeleteBesoin")) {
			 if(etat.equals("UpdateBesoin")) {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 executionoperation=besoinservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=UpdateBesoin&&id="+number+"");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=UpdateBesoin&&id="+number+"");
				 }
				 
			 }
			 else {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 executionoperation=besoinservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=AddBesoin");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=AddBesoin");
				 }
				 
			 }
			
		 }
		 else if(option.equals("SaveBesoin")) {
			 if(etat.equals("UpdateBesoin")) {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Besoin?option=ListeBesoin");
				 
			 }
			 else {
					String numero="";
					 famille=familleservice.getByid(Besoin.flle);
					 String a=""+famille.getNom().charAt(0)+""+famille.getNom().charAt(1)+""+famille.getNom().charAt(2)+"";
					
					 if(besoinservice.lastfacture(a)=="vide")
					 {
						 numero=""+a+"/B/"+1+"";
					 }
					 else {
						String [] tab=besoinservice.lastfacture(a).split("/");
						int val=Integer.parseInt(tab[2])+1;
						numero=""+a+"/B/"+val+"";
						 
					 }
					 executionoperation=besoinservice.update(connect.use.getId(),"en attente",numero);
					if(executionoperation==true)
					{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Besoin?option=ListeBesoin");
				}
			 }

		 }
		 else if(option.equals("UpdateBesoin")) {
			 etat=option;
			 number=request.getParameter("id");
			 listebesoin=besoinservice.getAll(number);
			 int famille=articleservice.getByid(listebesoin.get(0).getIdarticle()).getIdfamille();
			 Listearticle=articleservice.getAll2("actif", famille);
			 request.setAttribute("print","en attente");
				date=LocalDateTime.now().toString();
				 request.setAttribute("executionoperation",executionoperation );
				 request.setAttribute("listebesoin",listebesoin);
				 request.setAttribute("famille",famille);
				 request.setAttribute("date",listebesoin.get(0).getDate());
				request.setAttribute("option",option);
				 request.setAttribute("listearticle",Listearticle);
				 request.setAttribute("utilisateur",connect.use);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/besoin2.jsp" ).forward( request, response );
		 }
		 else if(option.equals("PrintBesoin")) {
			 number=request.getParameter("id");
			 listebesoin=besoinservice.getAll(number);
			 listeentreprise=entrepriseservice.getAll();
			 personne=personneservice.getByid(listebesoin.get(0).getUser());
			 Document document = new Document(PageSize.A4.rotate());
			 try {
				 response.setContentType("application/pdf");
				 response.setHeader("Content-Disposition","inline; filename=report.pdf");
				//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
				PdfWriter.getInstance(document, response.getOutputStream());
				document.addTitle("besoin N "+number+"");
	            document.addAuthor("core");
	            document.addSubject("Génération de PDF.");
	            document.addKeywords("iText, besoin");
	            document.getPageNumber();
				document.open();
				
				//-------
				Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
		         par3.setAlignment(Element.ALIGN_CENTER);
		         document.add(par3);
				 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par2.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par2);
		         Paragraph par0 = new Paragraph("Date document:"+listebesoin.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par0.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par0);
		         Paragraph par4 = new Paragraph("Editeur:"+utilisateurservice.getByid(listebesoin.get(0).getUser()).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par4.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par4);
	            Chunk ck = new Chunk("BESOIN  N " +number+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            ck.setUnderline((float) 1, -2);
	            Paragraph para = new Paragraph(ck);
	            para.setAlignment(Paragraph.ALIGN_CENTER);
	            document.add(para);
	            Paragraph p=new Paragraph(".");
	            document.add(p);
	            PdfPTable table = new PdfPTable(5);
	            table.getDefaultCell().setBorder(0);
	            //-
	            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre2=new Phrase("Fournisseur",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre3=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre4=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre5=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Stream.of(titre1,titre2,titre3,titre4,titre5)
	            .forEach(columnTitle -> {
	            	
	              PdfPCell header = new PdfPCell();
	             // header.setBackgroundColor(BaseColor.CYAN);
	              header.setPhrase(new Phrase(columnTitle));
	              header.setBorder(0);
	              header.setBorderWidthBottom(1);
	              header.setBorderColorBottom(BaseColor.GREEN);
	              header.setPaddingBottom(10);
	              table.addCell(header);
	             
	          });
	            //-
	            Paragraph p1=new Paragraph(".");
	            document.add(p1);
	            //--
	            Double t=0.0;
	            for(int i=0;i<listebesoin.size();i++) {
	            	offre=offreservice.getByid(listebesoin.get(i).getPrix());
	            	personne=personneservice.getByid(offre.getIdfournisseur());
	            	Phrase titre6=new Phrase(articleservice.getByid(listebesoin.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            	Phrase titre7=new Phrase(personne.getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre8=new Phrase(""+listebesoin.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre9=new Phrase(""+offreservice.getByid(listebesoin.get(i).getPrix()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre10=new Phrase(""+listebesoin.get(i).getQuantite()*offreservice.getByid(listebesoin.get(i).getPrix()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            	table.addCell(titre6);
	            	table.addCell(titre7);
	            	table.addCell(titre8);
	            	table.addCell(titre9);
	            	table.addCell(titre10);
	            	t=t+(listebesoin.get(i).getQuantite()*offreservice.getByid(listebesoin.get(i).getPrix()).getPrixvente());
	            	
	            }
	            
	            document.add(table);
	            Paragraph par5 = new Paragraph("Arreter le present besoin a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            par5.setAlignment(Element.ALIGN_CENTER);
	           document.add(par5);
	            
				 document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		 else if(option.equals("PrintCommande")) {
			 //----
			 number=request.getParameter("id");
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("Listefamille",Listefamille );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("Titre","Choix contenu" );
			 request.setAttribute("utilisateur",connect.use);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/choixcontenucommande.jsp" ).forward( request, response ); 
			 
			 //-----
			 
		 }
		 else if(option.equals("PrintCommande2")) {
			 String type=request.getParameter("choix");
			 Document document;
					 listeprix=besoinservice.listeprix(number);
					 liste=besoinservice.getAll2(listeprix);
					 
							  document = new Document(PageSize.A4.rotate());
							  
							 try {
								 response.setContentType("application/pdf");
								 response.setHeader("Content-Disposition","inline; filename=report.pdf");
								PdfWriter.getInstance(document, response.getOutputStream());
								document.addTitle("commande  ");
					            document.addAuthor("core");
					            document.addSubject("Génération de PDF.");
					            document.addKeywords("iText,commande");
					            document.getPageNumber();
								document.open();
								//-------
								for(int i=0;i<liste.size();i++) {
									 listebesoin2=besoinservice.getAll(number, Integer.parseInt(liste.get(i)),type);
									 listeentreprise=entrepriseservice.getAll(); 
								Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
						         par3.setAlignment(Element.ALIGN_CENTER);
						         document.add(par3);
								 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
						         par2.setAlignment(Element.ALIGN_RIGHT);
						         document.add(par2);
						         Paragraph par0 = new Paragraph("Date document:"+listebesoin2.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
						         par0.setAlignment(Element.ALIGN_RIGHT);
						         document.add(par0);
						         Paragraph par4 = new Paragraph("Editeur:"+utilisateurservice.getByid(listebesoin2.get(0).getUser()).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
						         par4.setAlignment(Element.ALIGN_RIGHT);
						         document.add(par4);
						         Paragraph par5 = new Paragraph("Fournisseur:"+personneservice.getByid(Integer.parseInt(liste.get(i))).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
						         par5.setAlignment(Element.ALIGN_RIGHT);
						         document.add(par5);
					            Chunk ck = new Chunk("BON DE COMMANDE  N"+number+"("+i+") ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            ck.setUnderline((float) 1, -2);
					            Paragraph para = new Paragraph(ck);
					            para.setAlignment(Paragraph.ALIGN_CENTER);
					            document.add(para);
					            Paragraph p=new Paragraph(".");
					            document.add(p);
					            PdfPTable table = new PdfPTable(5);
					            table.getDefaultCell().setBorder(0);
					            //-
					            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            Phrase titre3=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            Phrase titre4=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            Phrase titre5=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            Phrase titre11=new Phrase("Qte livree",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            Stream.of(titre1,titre3,titre4,titre5,titre11)
					            .forEach(columnTitle -> {
					            	
					              PdfPCell header = new PdfPCell();
					             // header.setBackgroundColor(BaseColor.CYAN);
					              header.setPhrase(new Phrase(columnTitle));
					              header.setBorder(0);
					              header.setBorderWidthBottom(1);
					              header.setBorderColorBottom(BaseColor.GREEN);
					              header.setPaddingBottom(10);
					              table.addCell(header);
					             
					          });
					            //-
					            Paragraph p1=new Paragraph(".");
					            document.add(p1);
					            //--
					            Double t=0.0;
					            for(int a=0;a<listebesoin2.size();a++) {
					            	Double prixarrondi=0.0;
				            		Double quantitearrondi=0.0;
					            	BigDecimal bd=new BigDecimal(listebesoin2.get(a).getPrix()).setScale(0, RoundingMode.HALF_UP);
				            		prixarrondi=bd.doubleValue();
				            		BigDecimal bd1=new BigDecimal(listebesoin2.get(a).getQuantite()).setScale(0, RoundingMode.HALF_UP);
				            		quantitearrondi=bd1.doubleValue();
					            	Phrase titre6=new Phrase(""+listebesoin2.get(a).getArticle(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
						            Phrase titre8=new Phrase(""+quantitearrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
						            Phrase titre9=new Phrase(""+prixarrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
						            Phrase titre10=new Phrase(""+quantitearrondi*prixarrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
						            Phrase titre12=new Phrase("",FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            	table.addCell(titre6);
					            	table.addCell(titre8);
					            	table.addCell(titre9);
					            	table.addCell(titre10);
					            	table.addCell(titre12);
					            	t=t+(quantitearrondi*prixarrondi);
					            	
					            }
					            long l = (new Double(t)).longValue();
					            document.add(table);
					            NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
					            Paragraph par6 = new Paragraph("Arreter la presente commande a la somme de FCFA "+ l ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            par6.setAlignment(Element.ALIGN_CENTER);
					            document.add(par6);
					            //--------en lettre
					            String lettre=FrenchNumberToWords.convert(l);
					            Paragraph par7 = new Paragraph("Soit FCFA: "+ lettre ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
					            par7.setAlignment(Element.ALIGN_CENTER);
					            document.add(par7);
					            //---------------
					            document.newPage();
					           
								}
								 document.close();
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
				
		 }		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(option.equals("ChoixType")) {
		 flle=Integer.parseInt(request.getParameter("famille"));
		 response.setStatus(response.SC_MOVED_TEMPORARILY);
		 response.setHeader("Location","./Besoin?option=AddBesoin");
		 date=LocalDateTime.now().toString();
		}
		else if(option.equals("AddBesoin")) {
			request.setAttribute("print","en attente");
			 int idarticle=Integer.parseInt(request.getParameter("article"));
			 listeoffre=offreservice.getAll3(idarticle,"actif");
			 Double quantite=Double.parseDouble(request.getParameter("quantite"));
			 besoin=new setch.beans.besoin(connect.use.getId(),date,"en attente",idarticle,listeoffre.get(0).getId(),quantite,"en attente");
			 int a=besoinservice.getByid1(idarticle,"en attente",connect.use.getId());
			
			 if(a==0) {
			
				 executionoperation=besoinservice.add(besoin);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=AddBesoin");
				 }
				 else  {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=AddBesoin");
				 } 
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Besoin?option=AddBesoin");
			 }
			
		}
		else if(option.equals("UpdateBesoin")){
			request.setAttribute("print","en attente");
			 int idarticle=Integer.parseInt(request.getParameter("article"));
			 listeoffre=offreservice.getAll3(idarticle,"actif");
			 Double quantite=Double.parseDouble(request.getParameter("quantite"));
			 listebesoin=besoinservice.getAll(number);
			 besoin=new setch.beans.besoin(connect.use.getId(),listebesoin.get(0).getDate(),number,idarticle,listeoffre.get(0).getId(),quantite,"en attente");
			 int a=besoinservice.getByid1(idarticle,number,connect.use.getId());
			
			 if(a==0) {
			
				 executionoperation=besoinservice.add(besoin);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=UpdateBesoin&&id="+number+"");
				 }
				 else  {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Besoin?option=UpdateBesoin&&id="+number+"");
				 } 
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Besoin?option=UpdateBesoin&&id="+number+"");
			 }
			
		}
		
	}

}
