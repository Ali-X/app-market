<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/bootstrap/css/signin.css"/>" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="/resources/bootstrap/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/bootstrap/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <h3 class="masthead-brand"><a href="<c:url value="/home"/>">Ali-X Spring project</a></h3>

    <spring:form modelAttribute="user" action="/j_spring_security_check" class="form-signin">

        <h2 class="form-signin-heading">Please sign in</h2>

        <spring:input path="username" class="form-control" placeholder="Username"/>
        <spring:input path="password" type="password" class="form-control" placeholder="Password"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

    </spring:form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <p>${error}</p>
        </div>
    </c:if>
    <c:if test="${not empty logout}">
        <div class="alert alert-info" role="alert">
            <p>${logout}</p>
        </div>
    </c:if>

</div> <!-- /container -->

</body>
</html>
