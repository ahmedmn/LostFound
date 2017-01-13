<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<my:pagetemplate title="PostDetail">
    
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
                  <li class="time-label">
                    <span class="bg-blue">
                        <c:out value="${post.type}"/>
                    </span>
                  </li>

                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <li>
                      <i class="fa fa-info-circle bg-blue"></i>

                      <div class="timeline-item">
                          <h3 class="timeline-header">${post.user.username}</h3>
                      </div>
                  </li>
                  <li>
                      <i class="fa fa-calendar" aria-hidden="true"></i>
                      <div class="timeline-item">
                          <h3 class="timeline-header">${post.creationDate}</h3>
                      </div>
                  </li>
                  <li>
                      <i class="fa fa-location-arrow bg-red"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Location: <strong><c:out value="${post.location}"/></strong></h3>

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


                  <!-- ITEMS -->                     
                  <li class="time-label">
                    <span class="bg-red">
                      Items
                    </span>
                  </li>

                  <c:forEach items="${post.postItems}" var="postItem">
                  <li>                     
                      <i class="fa fa-folder-o bg-yellow"></i>

                      <div class="timeline-item">

                          <h3 class="timeline-header">Basic information about <strong><c:out value="${postItem.name}"/></strong></h3>

                          <div class="timeline-body">
                              <b>Description: </b>
                                   <c:out value="${postItem.description}"/>
                              <br />
                              <b>Keywords: </b>
                                   <c:out value="${postItem.keywords}"/>
                              <br />
                              <b>Categories: </b>
                                   <c:forEach items="${postItem.categories}" var="cat">
                                        <c:out value="${cat.name}"/>
                                   </c:forEach>
                          </div>
                      </div>                  
                  </li>
                   </c:forEach>
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
              </ul>
          </div>
      </div>
    </jsp:attribute>
</my:pagetemplate>
