<%@ include file="header.jsp" %>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.vente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
double total=0;
List<vente>listevente=null;
List<personne>listeprescripteur=null;
List<personne>listepatient=null;
List<articles>listearticle=null;
listevente=(List<vente>)request.getAttribute("listevente");
listeprescripteur=(List<personne>)request.getAttribute("listeprescripteur");
listepatient=(List<personne>)request.getAttribute("listepatient");
listearticle=(List<articles>)request.getAttribute("listearticle");
String option=(String)request.getAttribute("option");
String a="";
if(listepatient.size()>0)
{
listepatient.get(0).getNom();
for(int i=0;i<listepatient.size();i++)
{
a=a+"/"+listepatient.get(i).getNom();
}
}
Double reduc=(Double)request.getAttribute("reduction");
personneservice personneservice=new personneservice();
Double val=(100-reduc)/100;
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
        <h2 class="form-login-heading bloc"><%if(option.equals("AddVenteNormale")){%>VENTE<%}else if(option.equals("creationventespecifique")){%>VENTE SPECIFIQUE<%}%></h2><br>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        if(option.equals("creationventespecifique"))
        {

        	%>
        	<input type="number" name="reduction" min="1" max="100" value="<%out.print(reduc);%>" class="form-control" placeholder="pourcentage reduction" autofocus <%if(listevente.size()>0){%>readonly<% } %>>
          <br>
        	<%
        }
        %>
        
         <select class="form-control" placeholder="prescripteur" name=prescripteur required <%if(listevente.size()>0){%>readonly<% } %>>
        <%
        for(int i=0;i<listeprescripteur.size();i++)
        {
        	%>
        	<option value="<%out.print(listeprescripteur.get(i).getId());%>" <%if(listevente.size()>0){if(listeprescripteur.get(i).getId()==listevente.get(0).getIdprescripteur()){%>selected<%}} %>><%out.print(listeprescripteur.get(i).getNom()); %></option>
        	<%
        }
        %>
          
        </select>
          <br>
          <%
          if(listevente.size()>0)
          {
        	  
        	  %>
        	  <input type="text"  name="patient" value="<%out.println(personneservice.getByid(listevente.get(0).getIdpatient()).getNom()); %>" class="form-control" placeholder="Nom Patient" autofocus required <%if(listevente.size()>0){%>readonly<% } %>>  
        	  <%  
          }
          else
          {
	          %>
	          <input type="text" list ="patient" id="listepatient" name="patient" value="" class="form-control" placeholder="Nom Patient" autofocus required <%if(listevente.size()>0){%>readonly<% } %> onBlur="modalshow('<%out.print(a);%>');">
	         <datalist id="patient">
	         <%
	         
	         for(int i=0;i<listepatient.size();i++)
	         {
	        	 %>
	        	<option value="<%out.print(listepatient.get(i).getNom());%>">
	        	 <%
	        	
	         }
        }
         %>
         </datalist>
          <br>
           <select class="form-control" placeholder="soins" name=article required >
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
          <input type="number" name="quantite" min="1" value="" class="form-control" placeholder="Quantite" autofocus required>
          <br>
        	
          
          <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrement" type="submit"><i class="fa fa-lock"></i>Enregistrement</button>
          <hr>
          
         
        </div>
        
      </form>
    </div>
            <div class="content-panel">
              <div class="col-md-12">
           
              <h4><i class="fa fa-angle-right"></i>DETAIL DE VENTE</h4>
              <hr>
              <table class="table">
                <thead>
                  <tr>
                    <th>Soins</th>
                    <th>Quantite</th>
                    <th>Prix Unit</th>
                  </tr>
                </thead>
                <tbody>
                <%
                articleservice articleservice=new articleservice();
                prixventeservice prixventeservice=new prixventeservice();
                
                for(int i=0;i<listevente.size();i++)
                {

                	%>
                  <tr>
                  <td><%out.print(articleservice.getByid(listevente.get(i).getIdarticle()).getNom()); %></td>
                  <td><%out.print(listevente.get(i).getQuantite()); %></td>
                  <td><%out.print(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val); %></td>
                   <%
                   if(option.equals("creationventespecifique"))
                   {
                	   %>
                	    <td> <a href="./login?option=supprimerventespecifique&id=<%out.print(listevente.get(i).getId()); %>&reduction=<%out.print(reduc); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>
                	   <% 
                	   
                   }
                   else
                   {
                	   %>
                	    <td> <a href="./VenteNormale?option=DeleteVenteNormale&id=<%out.print(listevente.get(i).getId()); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>
                	   <% 
                   }
                   
                   %>
                   
                  </tr>
                	<% 
                	double total1=prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*listevente.get(i).getQuantite();
                	total=total+total1;
                }
                	
                	
                %>
                  
                </tbody>
              </table>
              <div class="basfacture">
              <%
              if(option.equals("creationventecredit")){
            	  %>
            	   <input type="text" name="name" value="<%out.print(total*val); %>" class="form-control totalfacture" placeholder="Total facture" readonly>
            	  <%
              }
              else{
            	  %>
           	    <input type="number" name="name" value="<%out.print(total*val); %>" class="form-control totalfacture" placeholder="Total facture" >
           <input type="text" name="name" value="<%out.print(total*val); %>" class="form-control totalfacture" placeholder="Total facture" readonly>
           <input type="text" name="name" value="<%out.print(total*val); %>" class="form-control totalfacture" placeholder="Total facture" readonly>
           	  <%
              }
              %>
            
            </div>
            <br>
          
          </div>
          <div style='margin-top:28%;'>
          <%
          if(listevente.size()>0)
          {
        	  %>
        	  
        	
        	    <br>
          		<button class="btn btn-theme btn-block boutonfacture" name="button"  type="button" OncliCk="val1();"><i class="fa fa-lock"></i>Impression</button>
          		
          		
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