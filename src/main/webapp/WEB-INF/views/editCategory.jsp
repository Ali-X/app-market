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

    <h1>Category</h1>
    <h3>Update category</h3>
    <spring:form modelAttribute="category" method="post" action="/admin/category/upd">
        New Category Name:<br>
        <spring:input path="name" title="New Category Name" type="text" name="new_c_name"/>
        <br><br>
        <input type="hidden" name="id" value="${id}">
        <input type="submit" value="Submit">
    </spring:form>

</div> <!-- /container -->

</body>
</html>
