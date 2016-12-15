<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="New item">
	<jsp:attribute name="body">

		<form:form method="post"
			action="${pageContext.request.contextPath}/item/create"
			modelAttribute="itemCreate" cssClass="form-horizontal">
			<div class="form-group">
				<form:label path="categoryId" cssClass="col-sm-2 control-label">Category</form:label>
				<div class="col-sm-10">
					<form:select path="categoryId" cssClass="form-control">
						<c:forEach items="${categories}" var="c">
							<form:option value="${c.id}">${c.name}</form:option>
						</c:forEach>
					</form:select>
					<p class="help-block">
						<form:errors path="categoryId" cssClass="error" />
					</p>
				</div>
			</div>
			<div class="form-group ${name_error?'has-error':''}">
				<form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
				<div class="col-sm-10">
					<form:input path="name" cssClass="form-control" />
					<form:errors path="name" cssClass="help-block" />
				</div>
			</div>
			<div class="form-group ${description_error?'has-error':''}">
				<form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
				<div class="col-sm-10">
					<form:textarea cols="80" rows="20" path="description"
						cssClass="form-control" />
					<form:errors path="description" cssClass="help-block" />
				</div>
			</div>

			<button class="btn btn-primary" type="submit">Create item</button>
		</form:form>

	</jsp:attribute>
</my:pagetemplate>