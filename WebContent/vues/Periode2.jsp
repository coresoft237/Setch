<%@page import="setch.beans.famille"%>
<%@ include file="header.jsp" %>

      <section id="main-content">
      <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i>Choix periodes</h3>
        <div class="row mt">
          <!--  DATE PICKERS -->
          <div class="col-lg-12 ">
            <div class="form-panel">
              <form action="#" class="form-horizontal style-form">
                
                <div class="form-group">
                  <label class="control-label col-md-3">Date debut</label>
                  <div class="col-md-3 col-xs-11 ">
                    <div data-date-viewmode="years" data-date-format="dd-mm-yyyy" data-date="01-01-2020" class="input-append date dpYears">
                      <input type="text" readonly="" value="01-01-2020" size="16" class="form-control">
                      <span class="input-group-btn add-on ">
                        <button class="btn btn-theme" type="button"><i class="fa fa-calendar"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3">Date fin</label>
                  <div class="col-md-3 col-xs-11 ">
                    <div data-date-viewmode="years" data-date-format="dd-mm-yyyy" data-date="31-01-2020" class="input-append date dpYears">
                      <input type="text" readonly="" value="31-01-2020" size="16" class="form-control">
                      <span class="input-group-btn add-on">
                        <button class="btn btn-theme" type="button"><i class="fa fa-calendar"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3">Horaire initiale</label>
                  <div class="col-md-4 f">
                    <div class="input-group bootstrap-timepicker">
                      <input type="text" class="form-control timepicker-24">
                      <span class="input-group-btn">
                        <button class="btn btn-theme04" type="button"><i class="fa fa-clock-o"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3">Horaire finale</label>
                  <div class="col-md-4 f">
                    <div class="input-group bootstrap-timepicker">
                      <input type="text" class="form-control timepicker-24">
                      <span class="input-group-btn">
                        <button class="btn btn-theme04" type="button"><i class="fa fa-clock-o"></i></button>
                        </span>
                    </div>
                    <br>
                    <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Recherche</button>
                  </div>
                 
                </div>
                
          		
              </form>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>


        <!-- row -->

        <!-- row -->
      </section>
      <!-- /wrapper -->
    </section>
  
<%@ include file="footer.jsp" %>
