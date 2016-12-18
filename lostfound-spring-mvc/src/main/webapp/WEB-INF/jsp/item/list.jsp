<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Items">

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

        					<!-- /.box-header -->
        					<div class="box-body table-responsive no-padding">
        						<table class="table table-hover">
        							<tbody>
                                        <tr>
                                            <th>id</th>
                                            <th>name</th>
                                            <th>description</th>
                                            <th>keywords</th>

                                        </tr>
                                        <c:forEach items="${items}" var="item">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td><c:out value="${item.name}" /></td>
                                                <td><c:out value="${item.description}" /></td>
                                                <td><c:out value="${item.keywords}" /></td>
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