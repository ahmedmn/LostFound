<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:register title="Register | Lost & Found">

    <jsp:attribute name="head">
        <!-- iCheck -->
        <link rel="stylesheet" href="/lostfound/plugins/iCheck/square/blue.css">
    </jsp:attribute>

    <jsp:attribute name="footer">
        <!-- iCheck -->
        <script src="/lostfound/plugins/iCheck/icheck.min.js"></script>
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
        <div class="register-box-body">
            <p class="login-box-msg">Register a new membership</p>

            <form:form method="post" action="${pageContext.request.contextPath}/user/register" 
                       modelAttribute="userRegister">

                <div class="form-group ${username_error?'has-error':''}">
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        <form:input type="text" path="username" class="form-control" placeholder="Username (required)"/>
                        <form:errors path="username" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${email_error?'has-error':''}">
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        <form:input type="text" path="email" class="form-control" placeholder="Email (required)"/>
                        <form:errors path="email" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${phoneNumber_error?'has-error':''}">
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-phone form-control-feedback"></span>
                        <form:input type="text" path="phoneNumber" class="form-control" placeholder="Phone number"/>
                        <form:errors path="phoneNumber" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${password_error?'has-error':''}">
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        <form:input type="password" path="password" class="form-control" placeholder="Password (required)"/>
                        <form:errors path="password" cssClass="help-block"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-8">
                        <a href="${pageContext.request.contextPath}/user/login" class="text-left">I already have a membership</a>
                    </div>
                    <!-- /.col -->
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat right">Sign up</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form:form>


        </div>
        <!-- /.form-box -->
    </jsp:attribute>
</my:register>