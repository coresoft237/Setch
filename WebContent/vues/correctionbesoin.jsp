<%@ include file="header.jsp" %>
<%@page import="setch.beans.besoin"%>
<%@page import="setch.beans.articles"%>
<%
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
besoin besoins = (besoin)request.getAttribute("besoin");
articles articles = (articles)request.getAttribute("article");
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
              <div class="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading">Modification besoin</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        
        %>
        <div class="login-wrap">
       <input type="text" id="numerobesoin" name="numerobesoin" value="<%out.print(besoins.getNumero()); %>"  class="form-control"  autofocus readonly>
         <br>
          <input type="number" id="idbesoin" name="idbesoin" value="<%out.print(besoins.getId()); %>"  class="form-control"   autofocus readonly>
          <br>
          <input type="text" id="idarticlebesoin" name="article" value="<%out.print(articles.getNom()); %>" class="form-control"  autofocus readonly>
          <br>
          <input type="number" id="quantitebesoin" name="quantitebesoin" value="<%out.print(besoins.getQuantite()); %>"  class="form-control"  autofocus required>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Modification</button>
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