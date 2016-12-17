<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Lost & Found">

	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
		  <!-- daterange picker -->
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
		  <!-- bootstrap datepicker -->
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
	</jsp:attribute>

	<jsp:attribute name="footer">
		<!-- date-range-picker -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
		<!-- bootstrap datepicker -->
		<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
		<!-- Select2 -->
		<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>

        </jsp:attribute>
	<jsp:attribute name="body">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Users</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<tbody><tr>
								<th>ID</th>
								<th>Username</th>
                                                                <th>Email</th>
								<th>Phone</th>
								<th>Joined date</th>
							</tr>
                                                        <c:forEach items="${users}" var="user">
                                                            <tr>
                                                            <th><c:out value="${user.id}"/></th>
                                                            <th><c:out value="${user.username}"/></th> 
                                                            <th><c:out value="${user.email}"/></th> 
                                                            <th><c:out value="${user.phone}"/></th>
                                                            <th><c:out value="${user.joinedDate}"/></th>
                                                            </tr>
                                                        </c:forEach>

							</tbody></table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>

	</jsp:attribute>
</my:pagetemplate>
