<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <title>User Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/bootstrap/css/signin.css"/>" rel="stylesheet">

    <link href="<c:url value="/resources/bootstrap/css/style.css"/>" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/resources/bootstrap/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/bootstrap/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <h1>Hello, ${user.username}</h1>
    <br>
    <table>
        <tr>
            <th><a href="<c:url value="/profile"/>">My info</a></th>
            <th><a href="<c:url value="/profile/orders"/>">My orders</a></th>
        </tr>
        <tr>
            <table>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <table>
                            <tr>
                                <td>
                                    <c:out value="${o.id}"/>
                                </td>
                                <td>
                                    <c:out value="${o.date}"/>
                                </td>
                                <td>
                                    <fmt:formatNumber type="currency" currencySymbol="$" value="${o.price}"/>
                                </td>
                            </tr>
                        </table>
                    </tr>
                    <tr>
                        <table>
                            <tr>
                                <th>Name</th>
                                <th>Number</th>
                                <th>Total price</th>
                            </tr>
                            <c:forEach var="p" items="${o.products}">
                                <tr>
                                    <td>
                                        <c:out value="${p.key.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${p.value}"/>
                                    </td>
                                    <td>
                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${p.key.price}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </tr>
                </c:forEach>
            </table>
        </tr>
    </table>

</div> <!-- /container -->

</body>
</html>
