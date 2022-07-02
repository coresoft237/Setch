<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
List<personne>listepatient=null;
List<String>listebc=null;
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String option=(String)request.getAttribute("option");
String numero=(String)request.getAttribute("number");
  listepatient=(List<personne>)request.getAttribute("Listepersonne");
  String a="";
  if(listepatient.size()>0)
  {
  for(int i=0;i<listepatient.size();i++)
  {
  a=a+"/"+listepatient.get(i).getNom();
  }
  }
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
        
          <input type="text" list ="livreur" id="listelivreur"  name="fournisseur" value="" class="form-control" placeholder="Nom fournisseur" autofocus required onBlur="modalshow2('<%out.print(a);%>');" required>
	         <datalist id="livreur">
	         <%
	         
	         for(int i=0;i<listepatient.size();i++)
	         {
	        	 %>
	        	<option><%out.print(listepatient.get(i).getId()+"-"+listepatient.get(i).getNom());%></option>
	        	 <%
	        	
	         }
        
         %>
         </datalist> 
          <br>
           <input type="text"  name="numerocommande" id="numerocommande" value="<%out.print(numero); %>" class="form-control" placeholder="Numero bon de livraison" required Readonly >
          <br>
           <input type="text"  name="numerolivraison" id="numerolivraison"  class="form-control" placeholder="Numero bon de livraison" required > 
           <br> 
           <input type="date" name="datelivraison" id="datelivraison" class="form-control"  class="datepicker" required autofocus/>
           <br>
          <button class="btn btn-theme btn-block" type="submit" ><i class="fa fa-lock"></i> Valider</button>
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