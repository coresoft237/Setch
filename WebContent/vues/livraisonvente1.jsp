<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%
List<personne>listepatient=null;
String date=LocalDate.now().toString();
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String option=(String)request.getAttribute("option");
  listepatient=(List<personne>)request.getAttribute("Listepersonne");
  String a="";
  if(listepatient.size()>0)
  {
  for(int i=0;i<listepatient.size();i++)
  {
  a=a+"/"+listepatient.get(i).getNom();
  System.out.println("a"+a);
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
        
          <input type="text" list ="livreurvente" id="listelivreurvente"  name="client" value="" class="form-control" placeholder="Nom Client" autofocus required onBlur="modalshowlivvente('<%out.print(a);%>');" required>
	         <datalist id="livreurvente">
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
           <input type="date" name="datelivraisonvente" id="datelivraisonvente" class="form-control" Max=<%out.print(date); %>  class="datepicker" required autofocus/>
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