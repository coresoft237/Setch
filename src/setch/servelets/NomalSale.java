package setch.servelets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NomalSale
 */
@WebServlet("/NomalSale")
public class NomalSale extends HttpServlet {
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
    public NomalSale() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
						System.out.println("true");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						System.out.println("trues");
						response.setHeader("Location","./VenteNormale?option=ListVenteNormale");
						System.out.println(response.getStatus());
						 
						
						
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
