<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Categories">

	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="/lostfound/plugins/select2/select2.min.css" />
	</jsp:attribute>


	<jsp:attribute name="footer">
                		<!-- Select2 -->
                		<script src="/lostfound/plugins/select2/select2.full.min.js"></script>
                		<script type="text/javascript">
                			$(function()
                			{
                				$(".select2").select2();
                			});
                		</script>
    </jsp:attribute>

	<jsp:attribute name="body">

	    <div class="row">
        			<div class="col-xs-12">
        				<div class="box">
        					<div class="box-header">
        						<my:a href="/category/new" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    New category
                                </my:a>
        					</div>

        					<!-- /.box-header -->
        					<div class="box-body table-responsive no-padding">
        						<table class="table table-hover">
        							<tbody><tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th></th>
        							    </tr>
                                        <c:forEach items="${categories}" var="category">
                                            <tr>
                                                <td>${category.id}</td>
                                                <td><c:out value="${category.name}"/></td>
                                                <td>
                                                    <form method="post"
                                                        action="${pageContext.request.contextPath}/category/delete/${category.id}">
                                                        <button type="submit" class="btn btn-primary">Delete</button>
                                                    </form>
                                                </td>
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