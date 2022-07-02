<%@ include file="header.jsp" %>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.offreservice"%>
<%@page import="setch.service.besoinservice"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.besoin"%>
<%@page import="setch.beans.offre"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@page import="setch.beans.personne"%>
<%@ page import="java.util.List" %>
<%
	Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
double total=0;
List<besoin>listecommande=null;
List<articles>listearticle=null;
List<offre>listeoffre=null;
personne personnes=null;
listecommande=(List<besoin>)request.getAttribute("listebesoin");
listearticle=(List<articles>)request.getAttribute("listearticle");
int familles=(int)request.getAttribute("famille");
String date=(String)request.getAttribute("date");
articleservice articleservice=new articleservice();
prixventeservice prixventeservice=new prixventeservice();
offreservice offreservice=new offreservice();
personneservice personneservice=new personneservice();
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
        <h2 class="form-login-heading bloc">BESOIN </h2><br>
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
          <input type="number" name="quantite" min="1"  id="quantitebesoin" value="" class="form-control" placeholder="quantite" autofocus required>
          <br>
        	
          <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrement" type="submit" ><i class="fa fa-lock"></i>Enregistrement</button>
          <hr>
          
         
        </div>
        
      </form>
    </div>
        <!-- tableau facture -->
    <div class="content-panel">
              <div class="col-md-12">
           
              <h4><i class="fa fa-angle-right"></i>DETAIL BESOIN  DU <%out.print(date); %></h4>
              <hr>
              <table class="table">
                <thead>
                  <tr>
                    <th>Soins</th>
                    <th>Quantite</th>
                    <th>Prix unit</th>
                    <th>Fournisseur</th>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody>
                <%
              total=0;
                for(int i=0;i<listecommande.size();i++)
                {
				listeoffre=offreservice.getAll3(listecommande.get(i).getIdarticle(),"actif");
				personnes=personneservice.getByid(listeoffre.get(0).getIdfournisseur());
				double t=listeoffre.get(0).getPrixvente()*listecommande.get(i).getQuantite();
				
                	%>
                  <tr>
                  <td><%out.print(articleservice.getByid(listecommande.get(i).getIdarticle()).getNom()); %></td>
                  <td><%out.print(listecommande.get(i).getQuantite()); %></td>
                  <td><%out.print(listeoffre.get(0).getPrixvente()); %></td>
                  <td><%out.print(personnes.getNom()); %></td>
                  <td><%out.print(t); %></td>
                   <td> <a href="./Besoin?option=DeleteBesoin&id=<%out.print(listecommande.get(i).getId()); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>      
                  </tr>
                	<% 
                	
                	total=total+t;
                }
                	
                	
                %>
                  
                </tbody>
              </table>
              
            <br>
           <div class="basfacture">
             <input type="number" name="name" value="<%out.print(total); %>" class="form-control totalfacture" placeholder="Total facture" readonly >
           
            </div>
          </div>
          <div style='margin-top:28%;'>
          <%
          if(listecommande.size()>0)
          {
        	  %>
        	  
        	   <form method="POST">
          		<button class="btn btn-theme btn-block boutonfacture" name="button" value="impression" type="button" OncliCk="besoin2()"><i class="fa fa-lock"></i>Save</button>
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