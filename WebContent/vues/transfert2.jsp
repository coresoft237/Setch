<%@ include file="header.jsp" %>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.transfertservice"%>
<%@page import="setch.service.serviceservice"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.transfert"%>
<%@ page import="java.util.List" %>
<%
	Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
double total=0;
List<transfert>listetransfert=null;
List<articles>listearticle=null;
listetransfert=(List<transfert>)request.getAttribute("listetransfert");
listearticle=(List<articles>)request.getAttribute("listearticle");
String datemouvement=(String)request.getAttribute("date");
int emetteur=(int)request.getAttribute("emetteur");
int recepteur=(int)request.getAttribute("recepteur");
articleservice articleservice=new articleservice();
serviceservice serviceservice=new serviceservice();
%>
  
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <div class="cadre1">
                <div class="cadre11 ">
      <form class="form-login" method="POST" >

        <div class="login-wrap login-wrap1">
        <h2 class="form-login-heading bloc">TRANSFERTS </h2><br>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }     
          %>
          <br>
           <select class="form-control" placeholder="soins" id="articlebesoin" name=article required >
        <%
        for(int i=0;i<listearticle.size();i++)
        {
        	%>
        	<option value="<%out.print(listearticle.get(i).getId());%>"><%out.print(listearticle.get(i).getNom()); %></option>
        	<%
        }
        %>
          
        </select>
          <br>
           <input type="number" name="quantite"   id="quantitebesoin" value="" class="form-control" placeholder="quantite" autofocus required>  
         
          <br>
        	
          <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrement" type="submit" ><i class="fa fa-lock"></i>Enregistrement</button>
          <hr>
          
         
        </div>
        
      </form>
    </div>
        <!-- tableau facture -->
    <div class="content-panel">
              <div class="col-md-12">
           
              <h4><i class="fa fa-angle-right"></i>TRANSFERT - DU <%out.print(datemouvement); %>:<%out.print(serviceservice.getByid(emetteur).getNom()); %> vers <%out.print(serviceservice.getByid(recepteur).getNom()); %></h4>
              <hr>
              <table class="table">
                <thead>
                  <tr>
                    <th>Article</th>
                    <th>Quantite</th>
                  </tr>
                </thead>
                <tbody>
                <%
                for(int i=0;i<listetransfert.size();i++)
                {
                	%>
                  <tr>
                  <td><%out.print(articleservice.getByid(listetransfert.get(i).getArticle()).getNom()); %></td>
                  <td><%out.print(listetransfert.get(i).getQuantite()); %></td>
                   <td> <a href="./login?option=supprimertransfert&id=<%out.print(listetransfert.get(i).getId()); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>      
                  </tr>
                	<% 
                	
                	
                }
                	
                	
                %>
                  
                </tbody>
              </table>
              
            <br>
          
          </div>
          <div style='margin-top:28%;'>
          <%
          if(listetransfert.size()>0)
          {
        	  %>
        	  
        	   <form method="POST">
          		<button class="btn btn-theme btn-block boutonfacture" name="button" id="buttontransfert" value="impression" type="button"><i class="fa fa-lock"></i>Impression</button>
          		</form>
          		
        	  <%
          }
          %>
          </div>
         
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