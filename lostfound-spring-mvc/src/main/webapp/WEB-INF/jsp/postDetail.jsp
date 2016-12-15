<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Item | Lost & Found">

	<jsp:attribute name="body">

      <!-- Your Page Content Here -->

            <!-- row -->
      <div class="row">
          <div class="col-md-12">

              <ul class="timeline">
                  <!-- timeline time label -->
                  <li class="time-label">
                    <span class="bg-red">
                      Information
                    </span>
                  </li>

                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <li>
                      <i class="fa fa-info-circle bg-blue"></i>

                      <div class="timeline-item">
                          <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

                          <h3 class="timeline-header"><a href="#">Peter Kovac</a> <strong>lost item</strong></h3>
                      </div>
                  </li>
                  <li>
                      <i class="fa fa-location-arrow bg-red"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Location: <strong>Brno</strong></h3>

                          <div class="timeline-body">
                              <iframe
                                      width="800"
                                      height="350"
                                      frameborder="0" style="border:0"
                                      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBgMECpwmnrjMLds0IsiO93isB2YInxev0
                      &q=Brno" allowfullscreen>
                              </iframe>
                          </div>
                      </div>
                  </li>

                  <!-- ITEM 0 -->
                  <li>
                      <i class="fa fa-folder-o bg-yellow"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Basic information about <strong>NAME OF ITEM</strong></h3>

                          <div class="timeline-body">
                              <b>Description: </b>
                              Take me to your leader!
                              Switzerland is small and neutral!
                              We are more like Germany, ambitious and misunderstood!
                              <br />
                              <b>Keywords: </b>
                              Take me to your leader!
                              Switzerland is small and neutral!
                              We are more like Germany, ambitious and misunderstood!
                              <br />
                              <b>Categories: </b>
                              Electronics, Children, Cars, Tech
                          </div>
                      </div>
                  </li>
                  <li>
                      <i class="fa fa-camera bg-purple"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Photos</h3>

                          <div class="timeline-body">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                          </div>
                      </div>
                  </li>
                  <!-- End ITEM 0 -->
                  <!-- ITEM 1-->
                  <li>
                      <i class="fa fa-folder-o bg-yellow"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Basic information about <strong>NAME OF ITEM</strong></h3>

                          <div class="timeline-body">
                              <b>Description: </b>
                              Take me to your leader!
                              Switzerland is small and neutral!
                              We are more like Germany, ambitious and misunderstood!
                              <br />
                              <b>Keywords: </b>
                              Take me to your leader!
                              Switzerland is small and neutral!
                              We are more like Germany, ambitious and misunderstood!
                              <br />
                              <b>Categories: </b>
                              Electronics, Children, Cars, Tech
                          </div>
                      </div>
                  </li>
                  <li>
                      <i class="fa fa-camera bg-purple"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Photos</h3>

                          <div class="timeline-body">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                              <img src="http://placehold.it/150x100" alt="..." class="margin">
                          </div>
                      </div>
                  </li>
                  <!-- End ITEM 1 -->
                  <li>
                      <i class="fa fa-clock-o bg-gray"></i>
                  </li>
              </ul>

          </div>
      </div>

	</jsp:attribute>
</my:pagetemplate>
