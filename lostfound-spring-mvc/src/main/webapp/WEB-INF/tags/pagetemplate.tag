<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lostfound" %>
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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/post/list" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>L&F</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Lost & Found</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <c:choose>
                        <c:when test="${not empty authenticatedUser && !authenticatedUser.admin}">
                            <p>${authenticatedUser.username}</p>
                            <p>Member</p>
                        </c:when>
                        <c:when test="${not empty authenticatedUser && authenticatedUser.admin}">
                            <p>${authenticatedUser.username}</p>
                            <p>Admin</p>
                        </c:when>
                        <c:otherwise>
                            <p>Guest</p>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>


            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">Menu</li>
                <li><lostfound:a href="/"><i class="fa fa-link"></i> <span>Lost &amp; Found</span></lostfound:a></li> <!--for active: li class="active" -->
                <c:choose>
                    <c:when test="${not empty authenticatedUser}">
                        <li><lostfound:a href="/post/new"><i class="fa fa-link"></i> <span>New Post</span></lostfound:a></li>
                        <li><lostfound:a href="/user/logout"><i class="fa fa-link"></i> <span>Logout</span></lostfound:a></li>
                    </c:when>
                    <c:otherwise>
                        <li><lostfound:a href="/user/register"><i class="fa fa-link"></i> <span>Register</span></lostfound:a></li>
                        <li><lostfound:a href="/user/login"><i class="fa fa-link"></i> <span>Login</span></lostfound:a></li>
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty authenticatedUser && authenticatedUser.admin}">
                        <li class="header">Administration</li>
                        <li><lostfound:a href="/user/list"><i class="fa fa-link"></i> <span>Users</span></lostfound:a></li>
                        <li><lostfound:a href="/category/list"><i class="fa fa-link"></i> <span>Categories</span></lostfound:a></li>
                        <li><lostfound:a href="/item/list"><i class="fa fa-link"></i> <span>Items</span></lostfound:a></li>
                </c:if>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <c:out value="${title}"/>
                <small><c:out value="${description}"/></small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

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


            <!-- Your Page Content Here -->
            <jsp:invoke fragment="body"/>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">Lost &amp; Found</a>.</strong> All rights reserved.
    </footer>

    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

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
