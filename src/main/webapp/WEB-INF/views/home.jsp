<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Home Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">

   <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/bootstrap/css/cover.css"/>" rel="stylesheet">

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
                            <li class="active"><a href="<c:url value="/personalPage"/>">Profile</a></li>
                            <li class="active"><a href="<c:url value="/login"/>">Login</a></li>
                            <li class="active"><a href="<c:url value="/registration"/>">Registration</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="inner cover">
                <h1 class="cover-heading">Search about ...</h1>
                <p class="lead">Choose category from list, choose product and read description.</p>
                <p class="lead">
                    <a href="<c:url value="/categories"/>" class="btn btn-lg btn-default">Here we go!</a>
                </p>
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

