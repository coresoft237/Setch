<%@ include file="header.jsp" %>
<%@page import="setch.beans.famille"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.contenuachat"%>
<%@page import="setch.service.articleservice"%>
<%
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
articleservice articleservice=new articleservice();
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
              <div class="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading">Contenu Achat</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
         contenuachat contenu=(contenuachat)request.getAttribute("contenu");
         List<famille> Listefamille=(List<famille>)request.getAttribute("listefam");
         List<articles> Listearticle=(List<articles>)request.getAttribute("listearticle");
        %>
        <div class="login-wrap">
        <select class="form-control" placeholder="Famille" name=article required>
        <%
        for(int i=0;i<Listearticle.size();i++)
        {
        	%>
        	<option value="<%out.print(Listearticle.get(i).getId());%>"<%if(Listearticle.get(i).getId()==contenu.getArticle()){%>SELECTED<%} %>><%out.print(Listearticle.get(i).getNom());%></option>
        	<%
        }
        %>
          
        </select>
          <br>
          <input type="number" name="contenu" min="1"  id="prixoffre" value="<%contenu.getQuantite(); %>" class="form-control" placeholder="contenu" min='1' autofocus required>
          <br>
          
          <br>
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