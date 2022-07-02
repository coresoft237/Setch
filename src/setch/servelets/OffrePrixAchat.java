package setch.servelets;

import java.io.IOException;
import java.time.LocalDate;
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
 * Servlet implementation class OffrePrixAchat
 */
@WebServlet("/OffrePrixAchat")
public class OffrePrixAchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
    private javax.servlet.http.HttpSession compte;
    private javax.servlet.http.HttpSession variable2;
    private javax.servlet.http.HttpSession variable3;
    private javax.servlet.http.HttpSession variable1;
    setch.service.offreservice offreservice=new setch.service.offreservice();
    List<setch.beans.recapoffre> listesyntheseoffre = new ArrayList<setch.beans.recapoffre>();
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.beans.entreprise entreprise=null;
	setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
	List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
    boolean executionoperation=false;
    List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
    setch.service.personneservice personneservice=new setch.service.personneservice();
    List<setch.beans.offre> listeoffre = new ArrayList<setch.beans.offre>();
    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
    setch.service.articleservice articleservice=new setch.service.articleservice();
    setch.beans.personne personne=null;
    static setch.beans.personne person=null;
    setch.beans.offre offre=null;
    static setch.beans.personne fournisseur;
	 static String numerodocument;
	 static String date;
	 static String action;
	 int matricule=0;
	 String numerooffre;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OffrePrixAchat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		option=request.getParameter("option");
		variable1 = request.getSession();
		variable2 = request.getSession();
		variable3 = request.getSession();
		compte = request.getSession();
		if(option.equals("ListOffre")) {
			request.setAttribute("print","en attente");
			 //---supprimer en attente----
			 boolean t=offreservice.delete(connect.use.getId(),"en attente");
			 {
				 if(t==true) {
					 listesyntheseoffre=offreservice.getAll2();
					 request.setAttribute("Titre","Liste des offres" );
					 request.setAttribute("option",option);
					 request.setAttribute("liste",listesyntheseoffre);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("utilisateur",connect.use);
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				 }
				 else {
					 
				 }
			 }
			 
		}
		else if(option.equals("AddOffreFournisseur")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listepersonne=personneservice.getAll("fournisseur","actif");
			 request.setAttribute("Listepersonne",Listepersonne );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",connect.use);
			 request.setAttribute("Titre","Creation offre vente" );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/offre1.jsp" ).forward( request, response );
		}
		else if(option.equals("AddOffre")) {
			request.setAttribute("print","en attente");
			offreservice.delete(connect.use.getId(),"en attente");
				//------------
			 listeoffre=offreservice.getAll1("en attente",connect.use.getId());
			 Listearticle=articleservice.getAll("actif");
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",connect.use);
			 request.setAttribute("listeoffre",listeoffre);
			 request.setAttribute("fournisseur",fournisseur);
			 request.setAttribute("numero",numerodocument);
			 request.setAttribute("date",date);
			request.setAttribute("option",option);
			 request.setAttribute("listearticle",Listearticle);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/offre2.jsp" ).forward( request, response );
		}
		else if(option.equals("AddLigneOffre")) {
			request.setAttribute("print","en attente");
			
				//------------
			 listeoffre=offreservice.getAll1("en attente",connect.use.getId());
			 Listearticle=articleservice.getAll("actif");
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",connect.use);
			 request.setAttribute("listeoffre",listeoffre);
			 request.setAttribute("fournisseur",fournisseur);
			 request.setAttribute("numero",numerodocument);
			 request.setAttribute("date",date);
			request.setAttribute("option",option);
			 request.setAttribute("listearticle",Listearticle);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/offre2.jsp" ).forward( request, response );
		}
		else if(option.equals("SaveOffre")) {
			 executionoperation=offreservice.update(connect.use.getId(),"en attente",numerodocument);
			if(executionoperation==true)
				{
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=ListOffre");
				}
		 }
		else if(option.equals("DeleteOffre")) {
			 request.setAttribute("print","en attente");
			 int id=Integer.parseInt(request.getParameter("id"));
			 executionoperation=offreservice.delete(id);
			 if(executionoperation==true){
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=AddOffre");
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=AddOffre");
			 }
		}
		else if(option.equals("DetailOffre")) {
			request.setAttribute("print","en attente");
			 numerooffre=request.getParameter("id");
				 listeoffre=offreservice.getAll(numerooffre);
				 request.setAttribute("liste",listeoffre);
				request.setAttribute("option",option);
				personne=personneservice.getByid(listeoffre.get(0).getIdfournisseur());
				request.setAttribute("Titre","Offre "+listeoffre.get(0).getNumero()+" de "+personne.getNom()+ " du " + listeoffre.get(0).getDate()+"" );
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("utilisateur",connect.use);
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
		}
		else if(option.equals("UpdateOffre")) {
			System.out.println("entree");
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 System.out.println(matricule);
			offre=offreservice.getByid(matricule);
			 Listefamille=familleservice.getAll("actif");
			 Listearticle=articleservice.getAll("actif");
			 personne=personneservice.getByid(offre.getIdfournisseur());
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("offre",offre );
			 request.setAttribute("utilisateur",connect.use);
			 request.setAttribute("Titre","Modifier offre N "+offre.getNumero()+" de "+personne.getNom()+"" );
			 request.setAttribute("listefamille",Listefamille );
			 request.setAttribute("listearticle",Listearticle);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(connect.use.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/UpdateOffrePrixAchat.jsp" ).forward( request, response );
		}
		else if(option.equals("PrintOffre")) {
			System.out.println("entree");
			request.setAttribute("print","en attente");
			 int idFournisseur = Integer.parseInt(request.getParameter("fournisser"));
			 String numero = request.getParameter("id");
			offre=offreservice.getByid(matricule);
			 Listefamille=familleservice.getAll("actif");
			 Listearticle=articleservice.getAll("actif");
			 personne=personneservice.getByid(idFournisseur);
			 //---
			 listeoffre=offreservice.getAll(numero, idFournisseur);
			 listeentreprise=entrepriseservice.getAll();
			 Document document = new Document(PageSize.A4.rotate());
			 try {
				 response.setContentType("application/pdf");
				 response.setHeader("Content-Disposition","inline; filename=report.pdf");
				//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
				PdfWriter.getInstance(document, response.getOutputStream());
				document.addTitle("offre");
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
		         Paragraph par0 = new Paragraph("Date document:"+listeoffre.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par0.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par0);
		         Paragraph par4 = new Paragraph("Editeur:"+connect.use.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
		         par4.setAlignment(Element.ALIGN_RIGHT);
		         document.add(par4);
	            Chunk ck = new Chunk("Offre  N " +listeoffre.get(0).getNumero() +" du Fournisseur "+personne.getNom()+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            ck.setUnderline((float) 1, -2);
	            Paragraph para = new Paragraph(ck);
	            para.setAlignment(Paragraph.ALIGN_CENTER);
	            document.add(para);
	            Paragraph p=new Paragraph(".");
	            document.add(p);
	            PdfPTable table = new PdfPTable(2);
	            table.getDefaultCell().setBorder(0);
	            //-
	            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Phrase titre4=new Phrase("Prix Unit(Fcfa)",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
	            Stream.of(titre1,titre4)
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
	            for(int i=0;i<listeoffre.size();i++) {
	            	Phrase titre6=new Phrase(articleservice.getByid(listeoffre.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
		            Phrase titre9=new Phrase(""+listeoffre.get(i).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
	            	table.addCell(titre6);
	            	table.addCell(titre9);	
	            }
	            document.add(table);
				 document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 //----
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(option.equals("AddOffreFournisseur")) {
			 action="creationoffreachat";
			 String val=request.getParameter("fournisseur");
			 String[] b =val.split(":");
			 
			 if(b.length==1) {
				 person=new setch.beans.personne();
				 person.setNom(request.getParameter("fournisseur"));
				 person.setTtitre("fournisseur");
				 person.setPhone("telephone");
				 person.setStatut("actif");
				 numerodocument=request.getParameter("numero");
				 date=LocalDate.now().toString();
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./Personnes?option=AddPersonnes"); 
			 }
			 else {
				 person=personneservice.getByid(Integer.parseInt(b[0]));
			   fournisseur=person;
				 numerodocument=request.getParameter("numero");
				 date=LocalDate.now().toString();
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=AddOffre"); 
			 }
			 
			
		}
		 else if(option.equals("AddOffre")) {
			 request.setAttribute("print","en attente");	
			 personne=personneservice.getByid(fournisseur.getId());
			 int idarticle=Integer.parseInt(request.getParameter("article"));
			 Double prix=Double.parseDouble(request.getParameter("prix"));
			 offre=new setch.beans.offre(connect.use.getId(),date,"en attente",personne.getId(),idarticle,prix,"actif");
			 executionoperation=offreservice.add(offre);
			 if(executionoperation==true){
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=AddLigneOffre");
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./OffrePrixAchat?option=AddLigneOffre");
			 } 
		 }
		 else if(option.equals("UpdateOffre")) {
			 int article =Integer.parseInt(request.getParameter("article"));
				Double pv=Double.parseDouble(request.getParameter("pv"));
				String statut=request.getParameter("Statut");
				offre.setIdarticle(article);
				offre.setPrixvente(pv);
				offre.setStatut(statut);
				executionoperation=offreservice.update(matricule, offre);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						 response.setHeader("Location","./OffrePrixAchat?option=DetailOffre&&id="+numerooffre+"");
					}
					else
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						 response.setHeader("Location","./OffrePrixAchat?option=UpdateOffre&&id="+matricule+"");  
					}
			}
		 
	}

}
