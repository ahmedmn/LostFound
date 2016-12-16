<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Items">
	<jsp:attribute name="body">
<%-- 
		<my:a href="/item/new" class="btn btn-primary">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New item
    </my:a>
 --%>
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>description</th>
					<th>image</th>
					<th>keywords</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td><c:out value="${item.name}" /></td>
						<td><c:out value="${item.description}" /></td>
						<td><c:out value="${item.image}" /></td>
						<td><c:out value="${item.keywords}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>


	</jsp:attribute>
</my:pagetemplate>