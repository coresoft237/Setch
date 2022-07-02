<%@ include file="header.jsp" %>
<%@page import="setch.beans.livraisonvente"%>
<%@page import="setch.service.livraisonventeservice"%>
<%@ page import="java.util.List" %>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
List<String>listebl=null;
listebl=(List<String>)request.getAttribute("listebl");
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
          <select class="form-control" placeholder="bon de livraison vente" name=livraisonvente id="livraisonvente" required >
        <%
        for(int i=0;i<listebl.size();i++)
        {
        	%>
        	<option value="<%out.print(listebl.get(i));%>" ><%out.print(listebl.get(i)); %></option>
        	<%
        }
        %>
          
        </select>
           <br>
          <button class="btn btn-theme btn-block" type="button" ><i class="fa fa-lock" OncliCk="val1();"></i> Valider</button>
          <br>
          
         
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