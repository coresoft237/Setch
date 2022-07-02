<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
List<String>listepatient=null;
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String option=(String)request.getAttribute("option");
  listepatient=(List<String>)request.getAttribute("listefacture");
%>
  
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
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
        
          <select class="form-control" placeholder="Numero besoin" name="numerobesoin" id="numerocommande" required>
        <%
        for(int i=0;i<listepatient.size();i++)
        {
        	%>
        	<option value="<%out.print(listepatient.get(i));%>"><%out.print(listepatient.get(i)); %></option>
        	<%
        }
        %>
          
        </select>
          <br>
          <button class="btn btn-theme btn-block" type="button" OncliCk="commande2()"><i class="fa fa-lock"></i> Valider</button>
          <hr>        
        </div>
        
      </form>
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