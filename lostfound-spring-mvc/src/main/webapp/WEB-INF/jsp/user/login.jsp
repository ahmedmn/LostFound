<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:login title="Login | Lost & Found">

	<jsp:attribute name="head">
        <!-- iCheck -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
	</jsp:attribute>

	<jsp:attribute name="footer">
        <!-- iCheck -->
        <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' // optional
                });
            });
        </script>
	</jsp:attribute>

	<jsp:attribute name="body">
    <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form:form action="${pageContext.request.contextPath}/user/login" method="post" modelAttribute="userLogin">

        <div class="form-group ${username_error?'has-error':''}">
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <form:input type="text" path="username" class="form-control" placeholder="Username"/>
                <form:errors path="username" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${password_error?'has-error':''}">
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                <form:input type="password" path="password" class="form-control" placeholder="Password"/>
                <form:errors path="password" cssClass="help-block"/>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-8">
                <a href="${pageContext.request.contextPath}/user/register" class="text-center">Register a new membership</a>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div>
        </div>
    </form:form>


    </jsp:attribute>
</my:login>