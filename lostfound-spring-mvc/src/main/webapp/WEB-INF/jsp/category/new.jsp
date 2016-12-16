<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Create new category">

	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
	</jsp:attribute>

	<jsp:attribute name="body">

        <div class="row">
            <div class="col-md-12">
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/category/create" modelAttribute="categoryCreate">
                    <div class="box box-primary" id="info-about-category">
                        <div class="box-header with-border">
                            <h3 class="box-title">Enter category details</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-group ${name_error?'has-error':''}">
                                <label>Name</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                                    <form:input type="text" path="name" class="form-control" placeholder="Category name" />
                                </div>
                            </div>
                        </div>


                        <!-- /.box-body -->
                        <div class="box-body">
                            <button type="submit" class="btn btn-info">Submit</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
	</jsp:attribute>


</my:pagetemplate>
