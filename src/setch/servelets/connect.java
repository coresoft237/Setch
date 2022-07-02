package setch.servelets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import setch.service.cryptpwds;
import setch.service.erreurservice;

/**
 * Servlet implementation class connect
 */
@WebServlet("/connect")
public class connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	cryptpwds a=new cryptpwds();
	public  setch.beans.autorisation autorisation;
	public  setch.beans.utilisateur utilisateur;
	public static setch.beans.utilisateur use;
	setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
	setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
       boolean executionoperation=false;
       private javax.servlet.http.HttpSession compte;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		compte = request.getSession();
		compte.invalidate();
		request.setAttribute("print","en attente");
		executionoperation=true;
		request.setAttribute("Titre","Connexion" );
		request.setAttribute("executionoperation",executionoperation );
		this.getServletContext().getRequestDispatcher( "/vues/connexion.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		compte = request.getSession();
		String login=request.getParameter("Login");
		String pwd=request.getParameter("Pwd");
		
		utilisateur=utilisateurservice.verifcompte1(login,pwd);
		use=utilisateur;
		compte.setAttribute("utilisateur",utilisateur);
		if(utilisateur==null) {
			//----------erreur
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
			}
			String hote=""+addr;
			String dateerreur=""+LocalDateTime.now();
			erreurservice.remplir1(dateerreur,hote,"Mot de passe<<"+pwd+">> ou login<"+login+"> incorrect");
			//--------------
			request.setAttribute("print","en attente");
			request.setAttribute("Titre","Connexion" );
			executionoperation=false;
			request.setAttribute("executionoperation",executionoperation );
			this.getServletContext().getRequestDispatcher( "/vues/connexion.jsp" ).forward( request, response );
		}
		else {
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location","./login?option=connexion");
		}
	}

}
