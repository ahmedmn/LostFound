<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Items">
	<jsp:attribute name="body">

		<my:a href="/item/new" class="btn btn-primary">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New item
    </my:a>

		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>added</th>
					<th>item name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${item}" var="item">
					<tr>
						<td>${item.id}</td>
						<td><c:out value="${item.name}" /></td>
						<td><my:a href="/item/view/${item.id}" class="btn btn-primary">View</my:a></td>
						<td>
							<form method="post"
								action="${pageContext.request.contextPath}/item/delete/${item.id}">
								<button type="submit" class="btn btn-primary">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</jsp:attribute>
</my:pagetemplate>