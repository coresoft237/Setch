<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.offre"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String option=(String)request.getAttribute("option");
  offre Offre=(offre)request.getAttribute("offre");
  List<articles>Listearticle=(List<articles>)request.getAttribute("listearticle");
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
          <br>
           <select class="form-control" name=article required>
           <%
           for(int i=0;i<Listearticle.size();i++)
           {
        	   %>
        	   <option value="<%out.print(Listearticle.get(i).getId()); %>" <%if(Listearticle.get(i).getId()==Offre.getIdarticle()){%>SELECTED<%} %>><%out.print(Listearticle.get(i).getNom()); %></option>
        	   <%
           }
           %>
                </select>
          <br>
        	<input type="number"  min ="1" name="pv" value="<%out.print(Offre.getPrixvente()); %>" class="form-control" placeholder="Prix Achat" autofocus required>
          <br>
           </select>
          <br>
           <select class="form-control" name=Statut>
              <option value="actif" <%if(Offre.getStatut().equals("actif")){%>SELECTED<%} %>>Actif</option>
              <option value="nonactif" <%if(Offre.getStatut().equals("nonactif")){%>SELECTED<%} %>>Non actif</option>
           </select>
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