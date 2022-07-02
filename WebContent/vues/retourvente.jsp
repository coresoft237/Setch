<%@ include file="header.jsp" %>
<%@page import="setch.service.venteservice"%>
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
venteservice venteservice=new venteservice();
articleservice articleservice=new articleservice();
double total=0;
double totalretour=0;
List<vente>listevente=null;
List<vente>listeventeencours=null;
List<personne>listeprescripteur=null;
List<personne>listepatient=null;
List<articles>listearticle=null;
List<String>detailfacture=null;
List<String>articleglobalretournes=null;
List<String>articleglobalretourness=null;
List<articles>listearticlearetourner=null;
List<vente>listevente1=(List<vente>)request.getAttribute("listevente1");
listevente=(List<vente>)request.getAttribute("listevente");
listeprescripteur=(List<personne>)request.getAttribute("listeprescripteur");
listepatient=(List<personne>)request.getAttribute("listepatient");
listearticle=(List<articles>)request.getAttribute("listearticle");
listearticlearetourner=(List<articles>)request.getAttribute("listearticlearetourner");
String option=(String)request.getAttribute("option");
String numerofacture=(String)request.getAttribute("numerofacture");
int utilisateur=(int)request.getAttribute("utilisateurs");
String a="";
String b="";
String c="";
String e="";
if(listepatient.size()>0)
{
for(int i=1;i<listepatient.size();i++)
{
a=a+"/"+listepatient.get(i).getNom();
}
}
//------
detailfacture=venteservice.getfacture2(numerofacture);
for(int i=0;i<detailfacture.size();i++)
{
b=b+"/"+detailfacture.get(i);
}
//----------tableau retour anterieur
Double idfacture=venteservice.getByfacture1(numerofacture).get(0).getId();
articleglobalretournes=venteservice.getfacture("/"+idfacture+"/cter");
articleglobalretourness=venteservice.getfacture("/"+idfacture+"/cter",articleglobalretournes);
for(int i=0;i<articleglobalretourness.size();i++)
{
e=e+"/"+articleglobalretourness.get(i);
}

//*---
listeventeencours=venteservice.getByfacture(utilisateur,"en attente");
for(int i=0;i<listeventeencours.size();i++)
{
c=c+"/"+listeventeencours.get(i).getIdarticle();
}
Double reduc=(Double)request.getAttribute("reduction");
Double reduc1=(Double)request.getAttribute("reduction1");
personneservice personneservice=new personneservice();
Double val=(100-reduc)/100;
Double val1=(100-reduc1)/100;

%>
  
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <div class="cadre1">
              <div class="retour">
    <div class="container container1 contient1 ">
      <form class="form-login" method="POST" >

        <div class="login-wrap login-wrap1">
        <h2 style='font-size:small;'class="form-login-heading bloc"><%if(option.equals("creationretourventenormale")){%>SOINS RETOURNES<%}else if(option.equals("creationretourventespecifique")){%>SOINS RETOURNES<%}%></h2><br>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }

         %>

           <select class="form-control" id="articleretour" placeholder="soins" name=articleretour required >
        <%
        for(int i=0;i<listearticlearetourner.size();i++)
        {
        	%>
        	<option value="<%out.print(listearticlearetourner.get(i).getId());%>"><%out.print(listearticlearetourner.get(i).getNom()); %></option>
        	<%
        }
        %>
          
        </select>
          <br>
          <input type="number" id="qteretour" name="quantiteretour" min="1" value="" class="form-control" placeholder="Quantite" autofocus required>
          <br>
        	<%
        	if(option.equals("creationretourventespecifique"))
            {
            %>
             <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrementretour" type="button" OncliCk="controlretour1('<%out.print(b);%>','<%out.print(numerofacture);%>','<%out.print(c);%>','<%out.print(e);%>');"><i class="fa fa-lock"></i>Enregistrement</button>
            <% 
            }
        	else
        	{
        	%>
        	 <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrementretour" type="button" OncliCk="controlretour('<%out.print(b);%>','<%out.print(numerofacture);%>','<%out.print(c);%>','<%out.print(e);%>');"><i class="fa fa-lock"></i>Enregistrement</button>
        	<%	
        	}
        	%>
          <hr>
        </div>
        
      </form>
    </div>
    <div class="container container1 contient1">
      <form class="form-login" method="POST" >
        <div class="login-wrap login-wrap1">
        <h2 style='font-size:small;'class="form-login-heading bloc"><%if(option.equals("creationretourventenormale")){%>SOINS A PRENDRE<%}else if(option.equals("creationretourventespecifique")){%>SOINS A PRENDRE<%}%></h2><br>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        if(option.equals("creationretourventespecifique"))
        {
        	
        	
        	%>
        	<input type="number" name="reduction" id="reduction" min="1" max="100" value="<%out.print(reduc1);%>" class="form-control" placeholder="pourcentage reduction" autofocus <%if(listevente1.size()>0){%>readonly<% } %>>
          <br>
        	<%
        }
        %>
        
         <select class="form-control" placeholder="prescripteur" name="prescripteur" id="prescripteur" required <%if(listevente1.size()>0){%>readonly<% } %>>
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
          
        
           <select class="form-control" placeholder="soins" name=article id="soins" required >
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
          <input type="number" name="quantite" min="1"  id ="quantiteprise"value="" class="form-control" placeholder="Quantite" autofocus required>
          <br>
        	<%
        	 if(option.equals("creationretourventespecifique"))
             {
        		 %>
        		 <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrementretour" type="button" OncliCk="controlfacturationretour1('<%out.print(numerofacture);%>','<%out.print(c);%>');"><i class="fa fa-lock"></i>Enregistrement</button>
        		 <% 
             }
        	 else
        	 {
        		 %>
        		 <button class="btn btn-theme btn-block" choix="ligneenregistrement" name="button" id="Enregistrement" value="Enregistrementretour" type="button" OncliCk="controlfacturationretour('<%out.print(numerofacture);%>','<%out.print(c);%>');"><i class="fa fa-lock"></i>Enregistrement</button>
        		 <%
        	 }
        	%>
          <hr>
        </div>
      </form>
    </div>
    </div>
        <div class="content-panel">
              <div class="col-md-12">
           
              <h4><i class="fa fa-angle-right"></i>DETAIL DE VENTE RETOUR</h4>
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
                
                prixventeservice prixventeservice=new prixventeservice();
                
                for(int i=0;i<listevente.size();i++)
                {
                	%>
                  <tr>
                  <td><%out.print(articleservice.getByid(listevente.get(i).getIdarticle()).getNom()); %></td>
                  <td><%out.print(listevente.get(i).getQuantite()); %></td>
                  <%
                  if(listevente.get(i).getQuantite()<0)
                  {
                	  %>
                	  <td><%out.print(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val); %></td>
                	  <% 
                  }
                  else
                  {
                	  %>
                	  <td><%out.print(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val1); %></td>
                	  <% 
                  }
                  %>
                   <!-- <td><a href="http://localhost:8011/stock/login?option=modifiervente&id="<%out.print(listevente.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a></td> -->
                   <td> <a href="http://localhost:8011/setch/login?option=supprimerretourventenormale&id=<%out.print(listevente.get(i).getId()); %>" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></a> </td>
                  </tr>
                	<%
                	if(listevente.get(i).getQuantite()<0)
                	{
                	 total=total+(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val*listevente.get(i).getQuantite());
                	totalretour=totalretour+(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val*listevente.get(i).getQuantite());
                	}
                	else
                	{
                		total=total+(prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*val1*listevente.get(i).getQuantite());
                	}
                	
                }
                	
                	
                %>
                  
                </tbody>
              </table>
            <div class="basfacture">
             <input type="number" name="name"  class="form-control totalfacture" placeholder="Total facture" >
           <input type="text" name="name" value="<%out.print(total); %>" class="form-control totalfacture" placeholder="Total facture" readonly>
           <input type="text" name="name"  class="form-control totalfacture" placeholder="Total facture" readonly>
            </div>
            <br>
          
          </div>
          <%
          if(listevente.size()>0 && totalretour<0)
          {
        	  %>
        	 
        	    <br>
          		<button class="btn btn-theme btn-block boutonfacture" name="button"  type="button" OncliCk="val1();"><i class="fa fa-lock"></i>Impression</button>
          		
        	  <%
          }
          %>
         
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