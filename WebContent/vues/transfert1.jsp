<%@ include file="header.jsp" %>
<%@page import="setch.beans.service"%>
<%@page import="setch.beans.recaptransfert"%>
<%

List<service>listeservice=null;
List<recaptransfert>listerecaptransfert=null;
listeservice=(List<service>)request.getAttribute("Listeservice");
listerecaptransfert=(List<recaptransfert>)request.getAttribute("listerecaptransfert");
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
        <%
        if(listerecaptransfert.size()>0){
        	%>
        	<input type="text" name="numero" class="form-control" placeholder="Numero affectation" value ="<% out.print(listerecaptransfert.get(0).getNumero());%>" autofocus readonly>
          <br>
        	<%
        }
        %>
        <%
        if(listerecaptransfert.size()>0){
        	%>
        	<input type="date" name="datetransfert" class="form-control" placeholder="Date transfert" value ="<%out.print(listerecaptransfert.get(0).getDate()); %>" autofocus>
          <br>
        	<%
        }
        else{
        	%>
        	<input type="date" name="datetransfert" class="form-control" placeholder="Date transfert" value ="" autofocus>
          <br>
        	<%
        }
        %>
          <select class="form-control" placeholder="Service emetteur" name=emetteur required>
      		 <%
      		 for(int i=0;i<listeservice.size();i++)
             {if(listerecaptransfert.size()>0){
            	 
   				%>
            	<option value="<%out.print(listeservice.get(i).getId());%>" <%if(listeservice.get(i).getId()==listerecaptransfert.get(0).getEmetteur()){%>SELECTED<%} %> ><%out.print(listeservice.get(i).getNom()); %></option>
            	<%	
  			}
  			else{
  				%>
             	<option value="<%out.print(listeservice.get(i).getId());%>" ><%out.print(listeservice.get(i).getNom()); %></option>
             	<%
  				
  			}
             }
       		 %>
        	
        </select>
         <br>
          <select class="form-control" placeholder="Service recepteur" name=recepteur required>
      		 <%
      		for(int i=0;i<listeservice.size();i++)
            {
      			if(listerecaptransfert.size()>0){
      				%>
                	<option value="<%out.print(listeservice.get(i).getId());%>" <%if(listeservice.get(i).getId()==listerecaptransfert.get(0).getRecepteur()){%>SELECTED<%} %> ><%out.print(listeservice.get(i).getNom()); %></option>
                	<%	
      			}
      			else{
      				%>
                 	<option value="<%out.print(listeservice.get(i).getId());%>" ><%out.print(listeservice.get(i).getNom()); %></option>
                 	<%
      				
      			}
            	
            }
       		 %>
        	
        </select>
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