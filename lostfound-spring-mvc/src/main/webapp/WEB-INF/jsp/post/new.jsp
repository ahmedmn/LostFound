<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lostfound"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<lostfound:pagetemplate title="Create new post">
    <jsp:attribute name="head">
            <!-- Select2 -->
            <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
    </jsp:attribute>


    <jsp:attribute name="body">
        <div class="row">
            <div class="col-md-12">
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/post/create"
                           modelAttribute="postCreate">

                    <div class="box box-primary" id="info-about-item">
                        <div class="box-header with-border">
                            <h3 class="box-title">Info about post</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-group ${location_error?'has-error':''}"">
                                <label>Location</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-location-arrow"></i></span>
                                    <form:input path="location" cssClass="form-control" placeholder="Brno"/>
                                </div>
                                <form:errors path="location" cssClass="help-block"/>
                            </div>

                            <div class="form-group ${type_error?'has-error':''}"">
                               <label cssClass="control-label">Type</label>
                               <div class="input-group">
                                   <form:select path="type" cssClass="form-control">
                                       <c:forEach items="${postTypes}" var="postType">
                                           <form:option value="${postType}">${postType}</form:option>
                                       </c:forEach>
                                   </form:select>
                               </div>
                               <form:errors path="type" cssClass="help-block"/>
                            </div>
                        </div>
                        <!-- /.box-body -->


                        <!-- /.box (Item info) -->
                        <div class="box box-success" id="item-info">
                              <div class="box-header with-border">
                                  <h3 class="box-title">Item Info</h3>
                              </div>
                              <div class="box-body">

                                    <div class="form-group ${name_error?'has-error':''}"">
                                        <label>Name</label>
                                        <div class="input-group">
                                             <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                                             <form:input path="name" cssClass="form-control" placeholder="Write name of item"/>
                                        </div>
                                        <form:errors path="name" cssClass="help-block"/>
                                    </div>

                                    <div class="form-group ${description_error?'has-error':''}"">
                                        <label>Description</label>
                                        <div class="input-group">
                                             <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                                             <textarea class="form-control" rows="3" path="description" placeholder="Write your descrition of item"></textarea>
                                        </div>
                                        <form:errors path="description" cssClass="help-block"/>
                                    </div>

                                    <div class="form-group ${keywords_error?'has-error':''}"">
                                        <label>Keywords</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                                            <form:textarea class="form-control" rows="3" path="keywords" placeholder="Keyword per line"></form:textarea>
                                        </div>
                                        <form:errors path="keywords" cssClass="help-block"/>
                                    </div>


                                    <div class="form-group ${category_error?'has-error':''}"">
                                        <label>Category</label>
                                        <div class="input-group">
                                            <form:select path="categoryId" cssClass="form-control">
                                                <c:forEach items="${categories}" var="c">
                                                    <form:option value="${c.id}"><c:out value="${c.name}"/></form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                        <form:errors path="categoryId" cssClass="help-block"/>
                                    </div>
                              <!-- /.box-body -->
                              </div>
                        </div>

                    </div>
                    <!-- /.box-primary -->

                <!-- /.box -->
                <div class="box box-success" id="info-actions">
                    <div class="box-header with-border">
                        <h3 class="box-title">Actions</h3>
                    </div>
                    <div class="box-body">
                        <button type="submit" class="btn btn-info">Submit</button>
                    </div>
                </div>
                </form:form>

            </div>
            <!-- /.col-md-12 -->
        </div>
        <!-- /.row -->
    </jsp:attribute>

</lostfound:pagetemplate>
