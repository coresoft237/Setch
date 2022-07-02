package setch.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UniteVente
 */
@WebServlet("/UniteVente")
public class UniteVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpSession compte; 
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
	setch.service.uniteventeservice uniteventeservice=new setch.service.uniteventeservice();
	List<setch.beans.unitevente> Listeunitevente = new ArrayList<setch.beans.unitevente>();
	setch.beans.unitevente unitevente=null;
	setch.beans.articles article=null;
	 List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	 setch.service.familleservice familleservice=new setch.service.familleservice();
	    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
	    setch.service.articleservice articleservice=new setch.service.articleservice();
	    int matricule=0;
	     boolean executionoperation=false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UniteVente() {
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
		 if(option.equals("ListUniteVente"))
		 {
			 request.setAttribute("print","en attente");
			 Listeunitevente=uniteventeservice.getAll();
			 request.setAttribute("Titre","Liste des unites de vente" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listeunitevente);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
			 
		 }
		 else if(option.equals("AddUniteVente"))
		 {
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listearticle=articleservice.getAll("actif");
			 unitevente=new setch.beans.unitevente();
			unitevente.setDate("");
			unitevente.setIdarticle(0);
			unitevente.setUnitevente(0);
			 request.setAttribute("unitevente",unitevente );
			 request.setAttribute("Listearticle",Listearticle );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Creation unite vente" );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/unitevente.jsp" ).forward( request, response ); 
		 }
		 else if(option.equals("UpdateUniteVente"))
		 {
			 request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 unitevente=uniteventeservice.getByid(matricule);
			 article=articleservice.getByid(unitevente.getIdarticle());
			 Listearticle.add(article);
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );					 
			 request.setAttribute("unitevente",unitevente );
			 request.setAttribute("Listearticle",Listearticle );
			 request.setAttribute("Titre","Modifier unite de vente" );
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/unitevente.jsp" ).forward( request, response ); 
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(option.equals("AddUniteVente"))
		 {
				String date =request.getParameter("date");
				int article = Integer.parseInt(request.getParameter("article"));
				double pv = Double.parseDouble(request.getParameter("pv"));
				unitevente=new setch.beans.unitevente(utilisateur.getId(), date,1,article,pv);
				if(uniteventeservice.getByid(article, date)==null)
				{
					executionoperation=uniteventeservice.add(unitevente);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./UniteVente?option=ListUniteVente");
					}
					else
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./UniteVente?option=AddUniteVente"); 
					}	
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./UniteVente?option=AddUniteVente");
				}
		 }
		 else if(option.equals("UpdateUniteVente"))
		 {
				String date =request.getParameter("date");
				int articles = Integer.parseInt(request.getParameter("article"));
				double pv = Double.parseDouble(request.getParameter("pv"));
				unitevente=new setch.beans.unitevente(utilisateur.getId(), date,1,articles,pv);
				/*if(uniteventeservice.getByid(articles, date).getId()==matricule)
				{*/
					executionoperation=uniteventeservice.update(matricule, unitevente);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./UniteVente?option=ListUniteVente");
					}
					else
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./UniteVente?option=UpdateUniteVente&&id="+matricule+""); 
					}
				
		 }
	}

}
