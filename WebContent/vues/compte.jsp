<%@ include file="header.jsp" %>
<%
String statututilisateur=(String)request.getAttribute("Titre");
List<setch.beans.autorisation>listeautorisation=(List<setch.beans.autorisation>)request.getAttribute("Listeautorisation");
utilisateur utilisateur=(utilisateur)request.getAttribute("utilisateur");
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
String option = (String)request.getAttribute("option");
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <div id="login-page" >
    <div class="container">
      <form class="form-login compte" method="POST" >
        <h2 class="form-login-heading">${Titre}</h2>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        %>
        <div class="login-wrap">
          <input type="text" name="Name" pattern="[A-Za-z0-9\ }]{1,50}" class="form-control" placeholder="Name" value="<%out.print(utilisateur.getNom()); %>" <% if(option.equals("UpdateUserStatut")){%>readonly<% } else if(option.equals("UpdateUserPwd")){%>readonly<%} %> autofocus>
          <br>
          <input type="text" name="Login" pattern="[A-Za-z0-9\ }]{1,50}" class="form-control" placeholder="Login" value="<%out.print(utilisateur.getLogin()); %>" <% if(option.equals("UpdateUserStatut")){%>readonly<% } else if(option.equals("UpdateUserPwd")){%>readonly<%} %> autofocus>
          <br>
          <input type="password" name="Pwd" pattern="[A-Za-z0-9\ }]{6,20}" class="form-control" placeholder="Nouveau Mot de passe" value="<%if(option.equals("UpdateUserPwd")){out.print("");}else {out.print(utilisateur.getPassword());} %>" <% if(option.equals("UpdateUserStatut")){%>readonly<% } %> autofocus>
          <br>
          <%
          if(option.equals("UpdateUserPwd")){
        	  %>
        	   <input type="password" name="lastPwd" pattern="[A-Za-z0-9\ }]{6,20}" class="form-control" placeholder="Ancien Mot de passe"   autofocus>
        	  <%
          }
          %>
          <br>
          <%
          if(option.equals("UpdateUserPwd")){
        	  
          }
          else{
        	  %>
        	  <select class="form-control autorisation" name="autorisations">
           <%
           for(int i=0;i<listeautorisation.size();i++)
           {
        	   if(utilisateur.getAutorisation()==listeautorisation.get(i).getId())
        	   {
        		   %>
            	   <option value="<%out.print(listeautorisation.get(i).getId()); %>" selected><%out.print(listeautorisation.get(i).getNom()); %></option>
            	   <%    
        	   }
        	   else
        	   {
        	   %>
        	   <option value="<%out.print(listeautorisation.get(i).getId()); %>"><%out.print(listeautorisation.get(i).getNom()); %></option>
        	   <% 
        	   }
           }
           %>
           </select>
           <br>
           <select class="form-control" name=Statut>
                  <option value="actif" <%if(utilisateur.getStatut().equals("actif")){%>selected<%} %>>Actif</option>
                  <option value="nonactif" <%if(utilisateur.getStatut().equals("nonactif")){%>selected<%} %>>Non actif</option>
                  
           </select>
          <br>
        	  <% 
          }
          %>
          <hr>
           <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Connexion</button>
        </div>
       
      </form>
     
    </div>
  </div>
            <!-- fin -->
          </div>
        </div>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
   
      </div>

  
<%@ include file="footer.jsp" %>