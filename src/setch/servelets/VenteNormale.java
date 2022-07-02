package setch.servelets;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setch.beans.vente;
import setch.service.prints;
import setch.service.venteservice;

/**
 * Servlet implementation class VenteNormale
 */
@WebServlet("/VenteNormale")
public class VenteNormale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpSession compte; 
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
	setch.service.facturationservice facturationservice=new setch.service.facturationservice();
	List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
	setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
	setch.service.venteservice venteservice=new setch.service.venteservice();
	List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	 setch.service.familleservice familleservice=new setch.service.familleservice();
	    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	    setch.service.personneservice personneservice=new setch.service.personneservice();
	    setch.service.prixventeservice prixventeservice=new setch.service.prixventeservice();
	    List<setch.beans.prixvente> Listeprixvente = new ArrayList<setch.beans.prixvente>();
	    setch.service.articleservice articleservice=new setch.service.articleservice();
	    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
	    List<setch.beans.personne> listepatient = new ArrayList<setch.beans.personne>();
	    List<setch.beans.personne> listeprescripteur = new ArrayList<setch.beans.personne>();
	    List<setch.beans.facturation> listefacturation = new ArrayList<setch.beans.facturation>();
	     List<String> listefacture = new ArrayList<String>();
	     List<setch.beans.vente>listevente = new ArrayList<setch.beans.vente>();
	  public  static List<setch.beans.vente>listevente1 = new ArrayList<setch.beans.vente>();
	     setch.beans.personne personne=null;
	     setch.beans.entreprise entreprise=null;
	    Double reduc=0.0;
	    boolean executionoperation=false;
	    public static int n;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenteNormale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		option=request.getParameter("option");
		compte = request.getSession();
		 if(option.equals("ListVenteNormale"))
		 {
			 request.setAttribute("print","en attente");
			 String date1=LocalDate.now().toString()+" 00:00:00";
			 String date2=LocalDate.now().toString()+" 23:59:59";
			 listefacture=venteservice.getfacture(date1,date2,"en attente","VN");
			 request.setAttribute("Titre","Factures de vente du"+LocalDate.now() );
			 request.setAttribute("option",option);
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("listefacture",listefacture);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );   
			 
		 }
		 else if(option.equals("AddVenteNormale"))
		 {
			 System.out.println("kk"); 
			 reduc=0.0;
				executionoperation=true;
				listevente=venteservice.getAll("en attente",utilisateur.getId());
				System.out.println(listevente.size());
				 listeprescripteur=personneservice.getAll("prescripteur","actif");
				 listepatient=personneservice.getAll("patient","actif");
				 Listearticle=articleservice.getAll1("actif");
				 request.setAttribute("executionoperation",executionoperation );
				 request.setAttribute("listevente",listevente);
				 request.setAttribute("utilisateur",utilisateur);
				 request.setAttribute("listeprescripteur",listeprescripteur);
				 request.setAttribute("listepatient",listepatient);
				 request.setAttribute("option",option);
				 request.setAttribute("listearticle",Listearticle);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 request.setAttribute("reduction",reduc);
				 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );   
			 
		 }
		 
		 else if(option.equals("PrintVenteNormale"))
		 {
			 listefacturation=facturationservice.getAll();
			 String facture="";
			 String date= LocalDateTime.now().toString();
			 listeentreprise=entrepriseservice.getAll();
			 entreprise=listeentreprise.get(0);
			 //------
			 listefacture=venteservice.getfacture("en attente","VN");
				if(listefacture.size()<=0)
				{
					facture="1/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
					
				}
				else
				{
					String[] b =listefacture.get(0).split("/");
					int annee=Integer.parseInt(b[3]);
					if(LocalDate.now().getYear()==annee)
					{
						int numerotateur=Integer.parseInt(b[0])+1;
						facture=numerotateur+"/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
						
					}
					else
					{
						facture="1/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
						
					}
				}
				executionoperation=venteservice.update(facture,date,"en attente",utilisateur.getId(),"printed");
				if(executionoperation==true) {
					//----------gestion impression
					PrinterJob job = PrinterJob.getPrinterJob();
					 //--------
					 PrintRequestAttributeSet attributs = new HashPrintRequestAttributeSet();
					 PrinterJob travail = PrinterJob.getPrinterJob();
					 PageFormat page = travail.getPageFormat(attributs);
					 Paper papier = new Paper();
					 int k=20; //nombre de ligne
					 papier.setSize(215, 500); // format A4
					 papier.setImageableArea(10, 10, papier.getWidth()-20, papier.getHeight()); // marge de 10 points de par et d'autre de la zone d'impression
					 page.setPaper(papier);
					 page.setOrientation(PageFormat.PORTRAIT); // mode paysage.
					 //------
					 
					    job.setPrintable(new prints(),page);
					    //boolean ok = job.printDialog();
					    if (job.printDialog(attributs)) {
					      try {
					        job.print();
					      } catch (PrinterException ex) {
					     }
					    }
					//---------------
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./VenteNormale?option=ListVenteNormale");
				}
			 
		 }
		 else if(option.equals("DeleteVenteNormale")) {
				 request.setAttribute("print","en attente");
				 setch.beans.vente vente=new vente();
				 Double id=Double.parseDouble(request.getParameter("id"));
				 vente.setId(id);
				 executionoperation=venteservice.delete(vente);
				 if(executionoperation==true){
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./VenteNormale?option=AddVenteNormale");
				 }
				 else {
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./VenteNormale?option=AddVenteNormale");
				 }
		 }
		 else if(option.equals("PrintVenteNormale")) {
			 String numerofacture = request.getParameter("id");
			 listevente1=venteservice.getByfacture1(numerofacture);
			 System.out.println(listevente1.size());
			 n=(312*VenteNormale.listevente1.size())/29;
			 
			 PrinterJob job = PrinterJob.getPrinterJob();
			 //--------
			 PrintRequestAttributeSet attributs = new HashPrintRequestAttributeSet();
			 PrinterJob travail = PrinterJob.getPrinterJob();
			 PageFormat page = travail.getPageFormat(attributs);
			 Paper papier = new Paper();
			 int k=250; //nombre de ligne
			 papier.setSize(15, n); // format A4
			 papier.setImageableArea(10, 10, papier.getWidth()-20, papier.getHeight()); // marge de 10 points de par et d'autre de la zone d'impression
			 page.setPaper(papier);
			
			 page.setOrientation(PageFormat.PORTRAIT); // mode paysage.
			 //------
			
			    job.setPrintable(new prints(),page);
			    //boolean ok = job.printDialog();
			    if (job.printDialog(attributs)) {
			      try {
			        job.print();
			      } catch (PrinterException ex) {
			     }
			    }
	 }
		 else if(option.equals("RechercheVenteNormale"))
			{
				 String date=request.getParameter("date1");
			 	String date1=date+" 00:00:00";
				 String date2=date+" 23:59:59";
				 request.setAttribute("print","en attente");
				 listefacture=venteservice.getfacture(date1,date2,"en attente","VN");
				 request.setAttribute("Titre","Factures de vente du "+date );
				 request.setAttribute("option","ListVenteNormale");
				 request.setAttribute("utilisateur",utilisateur);
				 request.setAttribute("listefacture",listefacture);
				 Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );   
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 if(option.equals("AddVenteNormale")) {
				
			 String patient =request.getParameter("patient");
				personne=personneservice.getByid(patient);
				int article =Integer.parseInt(request.getParameter("article"));
				Listeprixvente=prixventeservice.getAll(article);
				int prescripteur =Integer.parseInt(request.getParameter("prescripteur"));
				Double quantite =Double.parseDouble(request.getParameter("quantite"));
				setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",personne.getId(),prescripteur,article, quantite,Listeprixvente.get(0).getId(),"en attente");
				if(venteservice.getByfacture(article, utilisateur.getId(), "en attente").size()>0)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./VenteNormale?option=AddVenteNormale");
				}
				else {
					executionoperation=venteservice.add(vente);
					System.out.println(executionoperation);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./VenteNormale?option=AddVenteNormale");
						 
						
						
					}
					else {
						System.out.println("false");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./VenteNormale?option=AddVenteNormale");
					}
				}
		}
	}

}
