<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><c:out value="${title}"/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/skin-blue.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <jsp:invoke fragment="head"/>
</head>
<body class="hold-transition login-page">
<div class="wrapper">

    <body class="hold-transition register-page">
    <div class="login-box">
        <div class="login-logo">
            <a href="#">${title}</a>
        </div>

        <!-- alerts -->
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <c:out value="${alert_danger}"/></div>
        </c:if>
        <c:if test="${not empty alert_info}">
            <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
        </c:if>
        <c:if test="${not empty alert_success}">
            <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
        </c:if>

        <jsp:invoke fragment="body" />
    </div>
    <!-- /.register-box -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<jsp:invoke fragment="footer"/>
</body>
</html>
