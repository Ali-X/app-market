<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
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

    <title>Admin Protected Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/bootstrap/css/signin.css"/>" rel="stylesheet">

    <link href="<c:url value="/resources/bootstrap/css/style.css"/>" rel="stylesheet">


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

    <h1>Product</h1>
    <h3>Update user</h3>
    <spring:form method="post" modelAttribute="user" action="/admin/user/upd" enctype="multipart/form-data">
        <h4>Username:</h4>
        <spring:input path="username" title="UserName" type="text" name="userName"/>
        <h4>Password:</h4>
        <spring:input path="password" title="Password" type="password" name="password"/>
        <h4>Email:</h4>
        <spring:input path="email" title="Email" type="email" name="email"/>
        <br>
        <h4>Image:</h4>
        <input type="file" name="file" title="select image..."/>
        <br>
        <input type="hidden" name="id" value="${id}">
        <input type="submit" value="Submit">
        <br>
    </spring:form>
    <a href="<c:url value="/home"/> ">Home</a>

</div> <!-- /container -->

</body>
</html>
