<%@ include file="header.jsp" %>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String action =(String)request.getAttribute("action");
System.out.println(action);
String option=(String)request.getAttribute("option");
personne personnes=null;
int matricule=0;

  personnes=(personne)request.getAttribute("personne");

%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
             <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading"> ${Titre}</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        %>
        <div class="login-wrap">
        
        	<input type="text" name="name"pattern="[^'}]{1,100}" value="<%out.print(personnes.getNom());  %>" class="form-control" placeholder="Name" autofocus required <%if(action.equals("creationoffreachat")||action.equals("creationlivraison")){%>readonly<%} %>>
          <br>
          <input type="text" name="telephone" class="form-control" pattern="[0-9]{9}" placeholder="Telephone" value="<%out.print(personnes.getPhone()); %>" autofocus required>
          <br>
          <%if(action.equals("creationoffreachat")||action.equals("creationlivraison")){%><%}
          else {
        	  %>
        	   <select class="form-control" name=titre <%if(action.equals("creationoffreachat")||action.equals("creationlivraison")){%>readonly<%} %>>
                  <option value="patient" <%if(personnes.getTitre().equals("patient")){%>selected<%} %>>Patient</option>
                  <option value="prescripteur" <%if(personnes.getTitre().equals("prescripteur")){%>selected<%} %>>Prescripteur</option>
                   <option value="fournisseur" <%if(personnes.getTitre().equals("fournisseur")){%>selected<%} %>>Fournisseur</option>
                   <option value="client" <%if(personnes.getTitre().equals("client")){%>selected<%} %>>Client</option>
           </select>
          <br>
           <select class="form-control" name=statut>
                  <option value="actif" <%if(personnes.getStatut().equals("actif")){%>selected<%} %>>Actif</option>
                  <option value="nonactif" <%if(personnes.getStatut().equals("nonactif")){%>selected<%} %>>Non actif</option>
                </select>
          <br>
        	  <%
        	  
          } %>
          
        	
          
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Creation</button>
          <hr>
          
         
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