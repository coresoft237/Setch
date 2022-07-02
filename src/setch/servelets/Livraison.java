package setch.servelets;

import java.io.IOException;
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
 * Servlet implementation class Livraison
 */
@WebServlet("/Livraison")
public class Livraison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	String numerobc="";
	String numerobl="";
	String date="";
	private String etat="";
	boolean executionoperation=false;
	String number="";
    setch.service.livraisonservice livraisonservice=new setch.service.livraisonservice();
    setch.service.besoinservice besoinservice=new setch.service.besoinservice();
    setch.beans.utilisateur utilisateur=connect.use;
    List<setch.beans.recaplivraison> listerecaplivraison = new ArrayList<setch.beans.recaplivraison>();
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.beans.contenuautorisation contenuautorisation=null;
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
    setch.service.personneservice personneservice=new setch.service.personneservice();
    List<setch.beans.livraison> listelivraison = new ArrayList<setch.beans.livraison>();
    List<setch.beans.totallivraison> listetotallivraison = new ArrayList<setch.beans.totallivraison>();
    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
    setch.service.articleservice articleservice=new setch.service.articleservice();
    setch.beans.livraison livraison=null;
    setch.beans.besoin besoin=null;
    setch.beans.entreprise entreprise=null;
	setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
	List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
	setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
	setch.service.offreservice offreservice=new setch.service.offreservice();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Livraison() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		 if(option.equals("ListeLivraison"))
		 {
			 livraisonservice.delete(utilisateur.getId(),"en attente");
			 number=request.getParameter("id");
			 System.out.println("number"+number);
			 request.setAttribute("print","en attente");
				livraisonservice.delete1(utilisateur.getId());
				 listerecaplivraison=livraisonservice.getAll3(number);
				 request.setAttribute("Titre","Liste des livraisons" );
				 request.setAttribute("option",option);
				 request.setAttribute("liste",listerecaplivraison);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("utilisateur",utilisateur);
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
		 }
		 else if(option.equals("AddLivraison1"))
		 {
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listepersonne=personneservice.getAll("fournisseur","actif");
			 request.setAttribute("Listepersonne",Listepersonne );
			 request.setAttribute("number",number );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("Titre","Creation livraison" );
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/livraison1.jsp" ).forward( request, response );
		 }
		 else if(option.equals("AddLivraison2"))
		 {
			 request.setAttribute("print","en attente");
				 listelivraison=livraisonservice.getAll1("en attente",utilisateur.getId(),date,OffrePrixAchat.person.getId(),"en attente");
				 Listearticle=articleservice.getAll();
				 request.setAttribute("executionoperation",executionoperation );
				 request.setAttribute("listelivraison",listelivraison);
				 request.setAttribute("fournisseur",OffrePrixAchat.person.getNom());
				 request.setAttribute("numerobc",numerobc);
				 request.setAttribute("numerobl",numerobl);
				 request.setAttribute("date",date);
				request.setAttribute("option",option);
				request.setAttribute("utilisateur",utilisateur);
				 request.setAttribute("listearticle",Listearticle);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/livraison2.jsp" ).forward( request, response );
		 }
		 else if(option.equals("DeleteLivraison"))
		 {
			 if(etat.equals("UpdateLivraison")) {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 livraison=livraisonservice.getByid(id);
				 executionoperation=livraisonservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=UpdateLivraison&&id="+livraison.getNumerobl()+"&&fournisseur="+livraison.getIdfournisseur()+"&&date="+livraison.getDate()+"&&bc="+livraison.getNumerobc()+"");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
					 response.setHeader("Location","./Livraison?option=UpdateLivraison&&id="+livraison.getNumerobl()+"&&fournisseur="+livraison.getIdfournisseur()+"&&date="+livraison.getDate()+"&&bc="+livraison.getNumerobc()+"");
				 }
				}
			 else {
				 request.setAttribute("print","en attente");
				 Double id=Double.parseDouble(request.getParameter("id"));
				 executionoperation=livraisonservice.delete(id);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=AddLivraison2");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=AddLivraison2");
				 }
			 }
			 
			
		 }
		 else if(option.equals("SaveLivraison")) {
			 System.out.println("viens");
			 System.out.println(etat);
			 if(etat.equals("UpdateLivraison")) {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Livraison?option=ListeLivraison&&id="+number+"");
			 }
			 else {
				 System.out.println("*******");
				 System.out.println(numerobl);
				 executionoperation=livraisonservice.update("en attente",numerobl,utilisateur.getId());	
					if(executionoperation==true) {
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./Livraison?option=ListeLivraison&&id="+number+"");	
					}
					else {
						 response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./Livraison?option=ListeLivraison&&id="+number+"");
					}
			 }
				
		 }
		 else if(option.equals("UpdateLivraison")) {
			 etat=option;
			 number=request.getParameter("bc");
			 numerobl=request.getParameter("id");
			 date=request.getParameter("date");
			 OffrePrixAchat.person=personneservice.getByid(Integer.parseInt(request.getParameter("fournisseur")));
			 listelivraison=livraisonservice.getAll(numerobl,number,OffrePrixAchat.person.getId(),date);
			 Listearticle=articleservice.getAll("actif");
			 request.setAttribute("print","en attente");
				 request.setAttribute("executionoperation",executionoperation );
				 request.setAttribute("listelivraison",listelivraison);
				request.setAttribute("option",option);
				request.setAttribute("numerobc",number);
				 request.setAttribute("numerobl",numerobl);
				 request.setAttribute("fournisseur",OffrePrixAchat.person.getNom());
				 request.setAttribute("date",date);
				 request.setAttribute("listearticle",Listearticle);
				 request.setAttribute("utilisateur",utilisateur);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/livraison2.jsp" ).forward( request, response );
		 }
		 else if(option.equals("PrintLivraison")) {
			 number=request.getParameter("bc");
			 numerobl=request.getParameter("id");
			 date=request.getParameter("date");
			 listeentreprise=entrepriseservice.getAll(); 
			 OffrePrixAchat.person=personneservice.getByid(Integer.parseInt(request.getParameter("fournisseur")));
			 listelivraison=livraisonservice.getAll(numerobl,number,OffrePrixAchat.person.getId(),date);
			 Document document;
					  document = new Document(PageSize.A4.rotate());
					  
					 try {
						 response.setContentType("application/pdf");
						 response.setHeader("Content-Disposition","inline; filename=report.pdf");
						PdfWriter.getInstance(document, response.getOutputStream());
						document.addTitle("Livraison  ");
			            document.addAuthor("core");
			            document.addSubject("Génération de PDF.");
			            document.addKeywords("iText,commande");
			            document.getPageNumber();
						document.open();
						Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
				         par3.setAlignment(Element.ALIGN_CENTER);
				         document.add(par3);
						 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par2.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par2);
				         Paragraph par0 = new Paragraph("Date document:"+listelivraison.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par0.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par0);
				         Paragraph par4 = new Paragraph("Editeur:"+utilisateurservice.getByid(listelivraison.get(0).getUser()).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par4.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par4);
				         Paragraph par5 = new Paragraph("Fournisseur:"+OffrePrixAchat.person.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par5.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par5);
			            Chunk ck = new Chunk("BON DE LIVRAISON  N"+listelivraison.get(0).getNumerobl()+" DU BON DE COMMANDE N+"+listelivraison.get(0).getNumerobc()+"", FontFactory.getFont(FontFactory.COURIER, 12f,0));
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
			            Phrase titre4=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre5=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
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
			            for(int a=0;a<listelivraison.size();a++) {
			            	Phrase titre6=new Phrase(articleservice.getByid(listelivraison.get(a).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            Phrase titre8=new Phrase(""+listelivraison.get(a).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            Phrase titre9=new Phrase(""+listelivraison.get(a).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            Phrase titre10=new Phrase(""+listelivraison.get(a).getQuantite()*listelivraison.get(a).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
			            	table.addCell(titre6);
			            	table.addCell(titre8);
			            	table.addCell(titre9);
			            	table.addCell(titre10);
			            	t=t+(listelivraison.get(a).getQuantite()*listelivraison.get(a).getPu());
			            	
			            }
			            
			            document.add(table);
			            Paragraph par6 = new Paragraph("Arreter la presente livraison a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            par6.setAlignment(Element.ALIGN_CENTER);
			            document.add(par6);
			            document.newPage();
			           
						
						 document.close();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 }
		 else if(option.equals("RapportLivraison")) {
			 numerobc=request.getParameter("id");
			 listeentreprise=entrepriseservice.getAll(); 
			 Document document;
					  document = new Document(PageSize.A4.rotate());
					  
					 try {
						 response.setContentType("application/pdf");
						 response.setHeader("Content-Disposition","inline; filename=report.pdf");
						PdfWriter.getInstance(document, response.getOutputStream());
						document.addTitle("Rapport Livraison  ");
			            document.addAuthor("core");
			            document.addSubject("Génération de PDF.");
			            document.addKeywords("iText,rapport");
			            document.getPageNumber();
						document.open();
						Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
				         par3.setAlignment(Element.ALIGN_CENTER);
				         document.add(par3);
						 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par2.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par2);
				         Paragraph par4 = new Paragraph("Editeur:",FontFactory.getFont(FontFactory.COURIER, 11f,0));
				         par4.setAlignment(Element.ALIGN_RIGHT);
				         document.add(par4);
			            Chunk ck = new Chunk("RAPPORT DES LIVRAISONS  DE LA COMMANDE N+"+numerobc+"", FontFactory.getFont(FontFactory.COURIER, 12f,0));
			            ck.setUnderline((float) 1, -2);
			            Paragraph para = new Paragraph(ck);
			            para.setAlignment(Paragraph.ALIGN_CENTER);
			            document.add(para);
			            Paragraph p=new Paragraph(".");
			            document.add(p);
			            PdfPTable table = new PdfPTable(7);
			            table.getDefaultCell().setBorder(0);
			            //-
			            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre2=new Phrase("Quantite cde",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre3=new Phrase("Quantite liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre4=new Phrase("Ecart",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre5=new Phrase("Prix liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre6=new Phrase("Prix cde",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Phrase titre7=new Phrase("Ecart",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
			            Stream.of(titre1,titre2,titre3,titre4,titre5,titre6,titre7)
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
			            listetotallivraison=livraisonservice.getAlltotallivraison(numerobc);
			            for(int a=0;a<listetotallivraison.size();a++) {
			            	String ecartquantite="RAS";
			            	String ecartprix="RAS";
			            	int f=listetotallivraison.get(a).getIdarticle();
			            	besoin=besoinservice.getByid2(f, numerobc);
			            	if(besoin==null) {
			            		ecartquantite="Non cde";
			            		ecartprix="Non cde";
			            		int qte=0;
			            		int prix=0;
			            		Phrase titre8=new Phrase(articleservice.getByid(listetotallivraison.get(a).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            	 Phrase titre9=new Phrase(""+qte,FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            	Phrase titre10=new Phrase(""+listetotallivraison.get(a).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));					       
					            Phrase titre11=new Phrase(""+ecartquantite,FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            Phrase titre13=new Phrase(""+prix,FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            Phrase titre12=new Phrase(""+listetotallivraison.get(a).getPrixmoyen(),FontFactory.getFont(FontFactory.COURIER, 12f,0));					        
					            Phrase titre14=new Phrase(""+ecartprix,FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            table.addCell(titre8);
				            	table.addCell(titre9);
				            	table.addCell(titre10);
				            	table.addCell(titre11);
				            	table.addCell(titre12);
				            	table.addCell(titre13);
				            	table.addCell(titre14);
			            	}
			            	else {
			            		Phrase titre8=new Phrase(articleservice.getByid(listetotallivraison.get(a).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            	 Phrase titre9=new Phrase(""+besoin.getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
				            	Phrase titre10=new Phrase(""+listetotallivraison.get(a).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            if(listetotallivraison.get(a).getQuantite()>besoin.getQuantite()) {
					            	ecartquantite="livraison en exces";
					            }
					            else if(listetotallivraison.get(a).getQuantite()<besoin.getQuantite()) {
					            	ecartquantite="livraison partielle";
					            }
					            Phrase titre11=new Phrase(""+ecartquantite,FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            Phrase titre13=new Phrase(""+offreservice.getByid(besoin.getPrix()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            Phrase titre12=new Phrase(""+listetotallivraison.get(a).getPrixmoyen(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            if(offreservice.getByid(besoin.getPrix()).getPrixvente()>listetotallivraison.get(a).getPrixmoyen()) {
					            	ecartprix="prix bas";
					            }
					            else if(offreservice.getByid(besoin.getPrix()).getPrixvente()<listetotallivraison.get(a).getPrixmoyen()) {
					            	ecartprix="prix eleve";
					            }
					            Phrase titre14=new Phrase(""+ecartprix,FontFactory.getFont(FontFactory.COURIER, 12f,0));
					            table.addCell(titre8);
				            	table.addCell(titre9);
				            	table.addCell(titre10);
				            	table.addCell(titre11);
				            	table.addCell(titre12);
				            	table.addCell(titre13);
				            	table.addCell(titre14);
			            	}
			            	
			            		
			            }			            
			            document.add(table);			            
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
		if(option.equals("AddLivraison1"))
		 {
			String val=request.getParameter("fournisseur");
			 String[] b =val.split("-");
			 if(b.length==1) {
				 OffrePrixAchat.action="creationlivraison";
				 OffrePrixAchat.person=new setch.beans.personne();
				 OffrePrixAchat.person.setNom(request.getParameter("fournisseur"));
				 OffrePrixAchat.person.setTtitre("fournisseur");
				 OffrePrixAchat.person.setPhone("telephone");
				 OffrePrixAchat.person.setStatut("actif");
				 numerobc=request.getParameter("numerocommande");
				 numerobl=request.getParameter("numerolivraison");
				 date=request.getParameter("datelivraison");
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./Personnes?option=AddPersonnes"); 
			 }
			 else {
				 etat="";
				 OffrePrixAchat.person=personneservice.getByid(Integer.parseInt(b[0]));
				 numerobc=request.getParameter("numerocommande");
				 numerobl=request.getParameter("numerolivraison");
				 System.out.println("numerobl"+numerobl);
				 date=request.getParameter("datelivraison");
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./Livraison?option=AddLivraison2"); 
			 }
		 }
		else if(option.equals("AddLivraison2")) {
			 int article=Integer.parseInt(request.getParameter("article"));
			 Double quantite=Double.parseDouble(request.getParameter("quantite"));
			 Double prix=Double.parseDouble(request.getParameter("prix"));
			 livraison=new setch.beans.livraison(utilisateur.getId(),date,numerobc,"en attente",OffrePrixAchat.person.getId(),article,quantite,prix,"en attente");
			 int a=livraisonservice.getByid1(article,"en attente",numerobc,utilisateur.getId());
			 if(a==0) {
				 executionoperation=livraisonservice.add(livraison);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=AddLivraison2&&id="+number+"");
				 }
				 else  {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
					 response.setHeader("Location","./Livraison?option=AddLivraison2&&id="+number+"");
				 } 
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				 response.setHeader("Location","./Livraison?option=AddLivraison2&&id="+number+"");
			 }	
		}
		else if(option.equals("UpdateLivraison")){
			int article=Integer.parseInt(request.getParameter("article"));
			 Double quantite=Double.parseDouble(request.getParameter("quantite"));
			 Double prix=Double.parseDouble(request.getParameter("prix"));
			 livraison=new setch.beans.livraison(utilisateur.getId(),date,number,numerobl,OffrePrixAchat.person.getId(),article,quantite,prix,"en attente");
			 int a=livraisonservice.getByid1(article,numerobl,number,utilisateur.getId());
			
			 if(a==0) {
			
				 executionoperation=livraisonservice.add(livraison);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=UpdateLivraison&&id="+numerobl+"&&date="+date+"&&bc="+number+"&&fournisseur="+OffrePrixAchat.person.getId()+"");
				 }
				 else  {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Livraison?option=UpdateLivraison&&id="+numerobl+"&&date="+date+"&&bc="+number+"&&fournisseur="+OffrePrixAchat.person.getId()+"");
				 } 
			 }
			 else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Livraison?option=UpdateLivraison&&id="+numerobl+"&&date="+date+"&&bc="+number+"&&fournisseur="+OffrePrixAchat.person.getId()+"");
			 }
			
		}
	}

}
