<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.transfert"%>
<%@page import="setch.service.articleservice"%>
<%
transfert transferts=null;
articleservice articleservices=new articleservice();
transferts=(transfert)request.getAttribute("transfert");
articles articles=articleservices.getByid(transferts.getArticle());
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
        <div class="login-wrap">
        
        	<input type="text" name="numero" class="form-control" placeholder="Numero affectation" value ="<% out.print(transferts.getId());%>" autofocus readonly>
          <br>

            <input type="text" name="article"   id="articles" value="<%out.print(articles.getNom()); %>" class="form-control" placeholder="article" autofocus required readonly>  
         

         <br>
            <input type="number" name="quantite"   id="quantitebesoin" value="<%out.print(transferts.getQuantite()); %>" class="form-control" placeholder="quantite" autofocus required>  
         
          <br>
        <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Suivant</button>
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