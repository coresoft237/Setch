<%@ include file="header.jsp" %>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.offre"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
double total=0;
List<offre>listeoffre=null;
List<personne>listeprescripteur=null;
List<personne>listepatient=null;
List<articles>listearticle=null;
listeoffre=(List<offre>)request.getAttribute("listeoffre");
listearticle=(List<articles>)request.getAttribute("listearticle");
personne fournisseur=(personne)request.getAttribute("fournisseur");
String numero=(String)request.getAttribute("numero");
String date=(String)request.getAttribute("date");
personneservice personneservice=new personneservice();
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
       
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
<div class="cadre1">
    <div class="cadre11">
      <form class="form-login" method="POST" >

        <div class="login-wrap login-wrap1">
        <h2 class="form-login-heading bloc">OFFRE </h2><br>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }     
          %>
          <br>
           <select class="form-control" placeholder="soins" id="articleoffre" name=article required >
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
          <input type="number" name="prix" min="1"  id="prixoffre" value="" class="form-control" placeholder="prix" min='1' autofocus required>
          <br>
        	
           <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Creation</button>
         <!--  <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrement" type="button" OncliCk="offre3()"><i class="fa fa-lock"></i>Enregistrement</button>-->
          <hr>
          
         
        </div>
        
      </form>
    </div>
        <div class="content-panel">
              <div class="col-md-12">
           
              <h4><i class="fa fa-angle-right"></i>DETAIL OFFRE N <%out.print(numero); %>  DE <%out.print(fournisseur.getNom()); %> DU <%out.print(date); %></h4>
              <hr>
              <table class="table">
                <thead>
                  <tr>
                    <th>Soins</th>
                    <th>Prix Unit</th>
                  </tr>
                </thead>
                <tbody>
                <%
                articleservice articleservice=new articleservice();
                prixventeservice prixventeservice=new prixventeservice();
                
                for(int i=0;i<listeoffre.size();i++)
                {

                	%>
                  <tr>
                  <td><%out.print(articleservice.getByid(listeoffre.get(i).getIdarticle()).getNom()); %></td>
                  <td><%out.print(listeoffre.get(i).getPrixvente()); %></td>
                   <%
                  
                	   %>
                	    <td> <a href="./OffrePrixAchat?option=DeleteOffre&id=<%out.print(listeoffre.get(i).getId()); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>
                	   <% 
                   
                   
                   %>
                   
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
          if(listeoffre.size()>0)
          {
        	  %>
        	  
        	   <form method="POST">
          		 <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrement" type="button" OncliCk="offre2()"><i class="fa fa-lock"></i>Sauvegarder</button>
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