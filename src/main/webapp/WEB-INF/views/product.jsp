<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pr" value="${product}"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><c:out value="${pr.name}"/></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/bootstrap/css/cover.css"/>" rel="stylesheet">

    <!-- For list -->
    <link href="<c:url value="/resources/bootstrap/css/style.css"/>" rel="stylesheet">

</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand"><a href="<c:url value="/home"/>">Ali-X Spring project</a></h3>
                    <nav>
                        <ul class="nav masthead-nav">
                            <li class="active"><a href="<c:url value="/profile"/>">Profile</a></li>
                            <li class="active"><a href="<c:url value="/login"/>">Login</a></li>
                            <li class="active"><a href="<c:url value="/registration"/>">Registration</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="inner cover">
                <h1><c:out value="${pr.name}"/>:</h1>
                <br>
                <h2>Name:</h2>
                <h4><c:out value="${pr.name}"/></h4>
                <h2>Description:</h2>
                <h4><c:out value="${pr.description}"/></h4>
                <h2>Price:</h2>
                <h4><fmt:formatNumber type="currency" currencySymbol="$" value="${pr.price}"/></h4>
                <a href="<c:url value="/product/addtocart">
                            <c:param name="c_id" value="${param.c_id}" />
                            <c:param name="p_id" value="${pr.id}" />
                          </c:url>">Add To Cart</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

