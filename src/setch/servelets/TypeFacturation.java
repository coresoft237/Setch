package setch.servelets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TypeFacturation
 */
@WebServlet("/TypeFacturation")
public class TypeFacturation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
	boolean executionoperation=false;
    private javax.servlet.http.HttpSession compte;
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.service.facturationservice facturationservice=new setch.service.facturationservice();
    List<setch.beans.facturation> listefacturation = new ArrayList<setch.beans.facturation>();
    setch.beans.facturation facturation=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeFacturation() {
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
		if(option.equals("ListTypeFacturation")) {
			 request.setAttribute("print","en attente");
			 listefacturation=facturationservice.getAll();
			 request.setAttribute("Titre","Historique types facturations" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",listefacturation);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("AddTypeFacturation")){
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 request.setAttribute("Titre","Type facturation" );
			 request.setAttribute("executionoperation",executionoperation );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/facturation.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(option.equals("AddTypeFacturation")){
			
				String date=LocalDateTime.now().toString();
				String type=request.getParameter("Statut");
				facturation=new setch.beans.facturation(date,type);
				executionoperation=facturationservice.add(facturation);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./TypeFacturation?option=ListTypeFacturation");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./TypeFacturation?option=AddTypeFacturation");   
				}
		}
	}

}
