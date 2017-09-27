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

    <title>Category Settings</title>

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
<h3 class="masthead-brand"><a href="<c:url value="/home"/>">Ali-X Spring project</a></h3>
<div class="container">
<h1>Category Settings</h1>
<br>
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>settings</th>
                </tr>
                <tr>
                    <spring:form method="post"  modelAttribute="category" action="/admin/category/add">
                        <td>
                            <input readonly path="id" class="inputTable" title="Category id" type="text" name="c_id" value="null"/>
                        </td>
                        <td>
                            <spring:input path="name" class="inputTable" title="Category Name" type="text" name="c_name"/>
                        </td>
                        <td>
                            <input class="inputTable" type="submit" value="add">
                        </td>
                    </spring:form>
                </tr>
                <c:forEach var="cat" items="${categories}">
                    <tr>
                        <td>
                            <c:out value="${cat.id}"/>
                        </td>
                        <td>
                            <c:out value="${cat.name}"/>
                        </td>
                        <td>
                            <a href="
                                <c:url value="/admin/category/upd">
                                    <c:param name="c_id" value="${cat.id}" />
                                </c:url>
                            ">edit</a>
                        </td>
                        <td>
                            <a href="
                                <c:url value="/admin/category/del">
                                    <c:param name="c_id" value="${cat.id}" />
                                </c:url>
                            ">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
<br>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a class="pagination" href="
            <c:url value="/admin/category">
                 <c:param name="page" value="${currentPage - 1}" />
            </c:url>
        ">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<c:forEach begin="1" end="${noOfPages}" var="i">
    <c:choose>
        <c:when test="${currentPage eq i}">
            <td>${i}</td>
        </c:when>
        <c:otherwise>
            <td><a class="pagination" href="
                            <c:url value="/admin/category">
                                <c:param name="page" value="${i}" />
                            </c:url>
                        ">${i}</a></td>
        </c:otherwise>
    </c:choose>
</c:forEach>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a class="pagination" href="
            <c:url value="/admin/category">
                 <c:param name="page" value="${currentPage + 1}" />
            </c:url>
        ">Next</a></td>
</c:if>
</div>
</body>
</html>
