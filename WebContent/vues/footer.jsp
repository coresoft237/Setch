

 <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <p>
          &copy; Copyrights <strong>Core</strong>. All Rights Reserved
        </p>
        <div class="credits">
          <!--
            You are NOT allowed to delete the credit link to TemplateMag with free version.
            You can delete the credit link only if you bought the pro version.
            Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/dashio-bootstrap-admin-template/
            Licensing information: https://templatemag.com/license/
          -->
          Created with Core itech 
        </div>
        <a href="./vues/Dashio/panels.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
          <a href="./vues/Dashio/basic_table.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
        <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification de mot de passe</div></h4>
              </div>
              <div class="modal-body">
                <input type="password" id="ancienPwd" name="ancienPwd" pattern="[A-Za-z0-9\ }]{6,20}" class="form-control" id="pwda" placeholder="Ancien mot de passe" autofocus>
          		<br>
          		<input type="password" id="nouveauPwd" name="nouveauPwd" pattern="[A-Za-z0-9\ }]{6,20}" class="form-control" id="pwdb" placeholder="Nouveau mot de passe" autofocus>
          		<br>
              </div>
              <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme modif" type="button" onClick="modificationpwd();">Submit</button>
              </div>
            </div>
          </div>
        </div>
        </form>
        <!-- modal -->
         <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal1" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Creation patient</div></h4>
              </div>
              <div class="modal-body">
                <input type="text" id="name1" name="name1" pattern="[^'}]{1,100}" value="" class="form-control" placeholder="Name" readonly>
          		<br>
          		<input type="text" id="telephone1" name="telephone" class="form-control" pattern="[0-9]{9}" placeholder="Telephone" value="" autofocus required>
          		<br>
          		<input type="text" id="titre1" name="titre" class="form-control"   value="patient" readonly >
          		<br>
          		<input type="text" id="statut1" name="statut" class="form-control"   value="actif" readonly >
          		<br>
              </div>
              <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme modif" type="button" onClick="creationpatient();">Submit</button>
              </div>
            </div>
          </div>
        </div>
        </form>
         <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal2" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite1">Creation fournisseurs</div></h4>
              </div>
              <div class="modal-body">
                <input type="text" id="name2" name="name2" pattern="[^'}]{1,100}" value="" class="form-control" placeholder="Name" readonly>
          		<br>
          		<input type="text" id="telephone2" name="telephone" class="form-control" pattern="[0-9]{9}" placeholder="Telephone" value="" autofocus required>
          		<br>
          		<input type="text" id="titre2" name="titre" class="form-control"   value="fournisseur" readonly >
          		<br>
          		<input type="text" id="statut2" name="statut" class="form-control"   value="actif" readonly >
          		<br>
              </div>
              <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme modif" type="button" onClick="creationfournisseur();">Submit</button>
              </div>
            </div>
          </div>
        </div>
        </form>
         <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite1">Creation client</div></h4>
              </div>
              <div class="modal-body">
                <input type="text" id="namelivvente2" name="name2" pattern="[^'}]{1,100}" value="" class="form-control" placeholder="Name" readonly>
          		<br>
          		<input type="text" id="telephonelivvente2" name="telephone" class="form-control" pattern="[0-9]{9}" placeholder="Telephone" value="" autofocus required>
          		<br>
          		<input type="text" id="titrelivvente2" name="titre" class="form-control"   value="client" readonly >
          		<br>
          		<input type="text" id="statutlivvente2" name="statut" class="form-control"   value="actif" readonly >
          		<br>
              </div>
              <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                <button class="btn btn-theme modif" type="button" onClick="creationclient();">Submit</button>
              </div>
            </div>
          </div>
        </div>
        </form>
        <!-- modal -->
        
    </footer>
    <!--footer end-->



  
</body>

  <!-- js placed at the end of the document so the pages load faster -->
  <script src="./vues/Dashio/lib/jquery/jquery.min.js"></script>
  <script src="./vues/Dashio/lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="./vues/Dashio/lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="./vues/Dashio/lib/jquery.scrollTo.min.js"></script>
  <script src="./vues/Dashio/lib/jquery.nicescroll.js" type="text/javascript"></script>
  <script src="./vues/Dashio/lib/jquery.sparkline.js"></script>
  <!--common script for all pages-->
  <script src="./vues/Dashio/lib/common-scripts.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/gritter/js/jquery.gritter.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/gritter-conf.js"></script>
  <script type="text/javascript" src="./vues/js/jss.js"></script>
  <!--script for this page-->
  <script src="./vues/Dashio/lib/sparkline-chart.js"></script>
 <script src="./vues/Dashio/lib/zabuto_calendar.js"></script>
  <!--common script for all pages-->
  <script type="text/javascript" src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/i18n/jquery-ui-i18n.min.js">
</script>
 <script src="./vues/Dashio/lib/jquery-ui-1.9.2.custom.min.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-daterangepicker/moment.min.js"></script>
  <script type="text/javascript" src="./vues/Dashio/lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
  <script src="./vues/Dashio/lib/advanced-form-components.js"></script>
 
 <!--BACKSTRETCH-->
  <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
  <script type="text/javascript" src="./vues/Dashio/lib/jquery.backstretch.min.js"></script>
  
  </html>

