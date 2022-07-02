package setch.servelets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * Servlet implementation class inventaire
 */
@WebServlet("/inventaire")
public class inventaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	boolean executionoperation=false;
	 private javax.servlet.http.HttpSession compte; 
	 List<setch.beans.recapinventaire> listesyntheseinventaire = new ArrayList<setch.beans.recapinventaire>();
	 setch.service.inventaireservice inventaireservice=new setch.service.inventaireservice();
	 List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	 setch.service.familleservice familleservice=new setch.service.familleservice();
	 List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	 setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	 List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
	 List<setch.beans.inventaire> listeinventaire = new ArrayList<setch.beans.inventaire>();
	 List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
	 setch.service.articleservice articleservice=new setch.service.articleservice();
	 setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
		List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
		List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
		  setch.service.personneservice personneservice=new setch.service.personneservice();
			 setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
			 setch.service.livraisonservice livraisonservice=new setch.service.livraisonservice();
	 setch.beans.inventaire inventaire=null;
	 setch.beans.personne personne=null;
	 private String date;
	 private String number;
	 private String etat="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inventaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		compte = request.getSession();
		 if(option.equals("ListeInventaire"))
		 {
			 request.setAttribute("print","en attente");
				 listesyntheseinventaire=inventaireservice.recapinventaire();
				 request.setAttribute("Titre","Liste des inventaires" );
				 request.setAttribute("option",option);
				 request.setAttribute("liste",listesyntheseinventaire);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 request.setAttribute("utilisateur",connect.use);
				 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );

		 }
		 if(option.equals("AddInventaire"))
		 {
			 request.setAttribute("print","en attente");
				//------------
			 date=LocalDateTime.now().toString();
			 String date=LocalDateTime.now().toString();
			 listeinventaire=inventaireservice.getAll1("en attente",connect.use.getId());
			 Listearticle=articleservice.getAll("actif");
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("listeinventaire",listeinventaire);
			request.setAttribute("option",option);
			 request.setAttribute("listearticle",Listearticle);
			 request.setAttribute("utilisateur",connect.use);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/inventaire2.jsp" ).forward( request, response );

		 }
		 if(option.equals("DeleteInventaire"))
		 {
			 if(etat.equals("UpdateInventaire")) {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 executionoperation=inventaireservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./inventaire?option=UpdateInventaire&&id="+number+"");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./inventaire?option=UpdateInventaire&&id="+number+"");
				 }
				 
			 }
			 else {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 executionoperation=inventaireservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./inventaire?option=AddInventaire");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./inventaire?option=AddInventaire");
				 }
				 
			 }

		 }
		 else if(option.equals("SaveInventaire")) {
			 if(etat.equals("UpdateBesoin")) {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Besoin?option=ListeInventaire");
				 
			 }
			 else {
					String numero="";
					 if(inventaireservice.lastinventaire()=="vide")
					 {
						 numero="INV/"+1+"";
					 }
					 else {
						String [] tab=inventaireservice.lastinventaire().split("/");
						int val=Integer.parseInt(tab[1])+1;
						numero="INV/"+val+"";
						 
					 }
					 executionoperation=inventaireservice.update(connect.use.getId(),"en attente",numero);
					if(executionoperation==true)
					{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./inventaire?option=ListeInventaire");
				}
			 }

		 }
		 else if(option.equals("UpdateInventaire"))
		 {
			 etat=option;
			 number=request.getParameter("id");
			 listeinventaire=inventaireservice.getAll(number);
			 Listearticle=articleservice.getAll("actif");
			 request.setAttribute("print","en attente");
				 request.setAttribute("executionoperation",executionoperation );
				 request.setAttribute("listeinventaire",listeinventaire);
				request.setAttribute("option",option);
				 request.setAttribute("listearticle",Listearticle);
				 request.setAttribute("utilisateur",connect.use);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					this.getServletContext().getRequestDispatcher( "/vues/inventaire2.jsp" ).forward( request, response );

		 }
		 else if(option.equals("PrintInventaire")) {
			 number=request.getParameter("id");
			 listeinventaire=inventaireservice.getAll(number);
			 listeentreprise=entrepriseservice.getAll();
			 personne=personneservice.getByid(listeinventaire.get(0).getUser());
			 Document document = new Document(PageSize.A4.rotate());
			 try {
				 response.setContentType("application/pdf");
				 response.setHeader("Content-Disposition","inline; filename=report.pdf");
				//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
				PdfWriter.getInstance(document, response.getOutputStream());
				document.addTitle("Inventaire N "+number+"");
	            document.addAuthor("core");
	            document.addSubject("Génération de PDF.");
	            document.addKeywords("iText, inventaire");
	            document.getPageNumber();
				document.open();
				
				//-------
				Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
		         par3.setAlignment(Element.ALIGN_CENTER);
		         document.add(par3);
				 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par2.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par2);
		         Paragraph par0 = new Paragraph("Date document:"+listeinventaire.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par0.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par0);
		         Paragraph par4 = new Paragraph("Editeur:"+utilisateurservice.getByid(listeinventaire.get(0).getUser()).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par4.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par4);
	            Chunk ck = new Chunk("INVENTAIRE  N " +number+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            ck.setUnderline((float) 1, -2);
	            Paragraph para = new Paragraph(ck);
	            para.setAlignment(Paragraph.ALIGN_CENTER);
	            document.add(para);
	            Paragraph p=new Paragraph(".");
	            document.add(p);
	            PdfPTable table = new PdfPTable(4);
	            table.getDefaultCell().setBorder(0);
	            //-
	            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre3=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre4=new Phrase("Cout Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre5=new Phrase("Cout total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Stream.of(titre1,titre3,titre4,titre5)
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
	            for(int i=0;i<listeinventaire.size();i++) {
	            	Double prixarrondi=0.0;
            		Double quantitearrondi=0.0;
	            	Double val=livraisonservice.CMUP1(listeinventaire.get(0).getDate(), listeinventaire.get(i).getIdarticle());
	            	BigDecimal bd=new BigDecimal(val).setScale(0, RoundingMode.HALF_UP);
            		prixarrondi=bd.doubleValue();
            		Double val2=listeinventaire.get(i).getQuantite();
            		BigDecimal bd1=new BigDecimal(val2).setScale(0, RoundingMode.HALF_UP);
            		quantitearrondi=bd1.doubleValue();
            		//--
	            	Phrase titre6=new Phrase(articleservice.getByid(listeinventaire.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre8=new Phrase(""+quantitearrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre9=new Phrase(""+prixarrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre10=new Phrase(""+prixarrondi*quantitearrondi,FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            	table.addCell(titre6);
	            	table.addCell(titre8);
	            	table.addCell(titre9);
	            	table.addCell(titre10);
	            	t=t+(prixarrondi*quantitearrondi);
	            	
	            }
	            
	            document.add(table);
	            Paragraph par5 = new Paragraph("Arreter le present inventaire au cout de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            par5.setAlignment(Element.ALIGN_CENTER);
	            document.add(par5);
	           
	            
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
		 if(option.equals("AddInventaire"))
		 {
				request.setAttribute("print","en attente");
				 int idarticle=Integer.parseInt(request.getParameter("article"));
				 Double quantite=Double.parseDouble(request.getParameter("quantite"));
				 inventaire=new setch.beans.inventaire(connect.use.getId(),date,"en attente",idarticle,quantite);
				 int a=inventaireservice.getByid1(idarticle,"en attente",connect.use.getId());
				
				 if(a==0) {
				
					 executionoperation=inventaireservice.add(inventaire);
					 if(executionoperation==true){
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./inventaire?option=AddInventaire");
					 }
					 else  {
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
						 response.setHeader("Location","./inventaire?option=AddInventaire");
					 } 
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
					 response.setHeader("Location","./inventaire?option=AddInventaire");
				 }
		 }
		 else if(option.equals("UpdateInventaire"))
		 {
				request.setAttribute("print","en attente");
				int idarticle=Integer.parseInt(request.getParameter("article"));
				 Double quantite=Double.parseDouble(request.getParameter("quantite"));
				 listeinventaire=inventaireservice.getAll(number);
				 inventaire=new setch.beans.inventaire(connect.use.getId(),listeinventaire.get(0).getDate(),number,idarticle,quantite);
				 int a=inventaireservice.getByid1(idarticle,number,connect.use.getId());
				
				 if(a==0) {
				
					 executionoperation=inventaireservice.add(inventaire);
					 if(executionoperation==true){
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./inventaire?option=UpdateInventaire&&id="+number+"");
					 }
					 else  {
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./inventaire?option=UpdateInventaire&&id="+number+"");
					 } 
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./inventaire?option=UpdateInventaire&&id="+number+"");
				 }
				

		 }
	}

}
