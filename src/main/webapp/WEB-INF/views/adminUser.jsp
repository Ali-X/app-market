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

    <title>User settings</title>

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
<h3 class="masthead-brand"><a href="<c:url value="/home"/>">Ali-X Spring project</a></h3>
<div class="container">
    <h1>User settings</h1>
    <br>
    <table>
        <tr>
            <td>
                <h3>Users</h3>
                <table>
                    <tr>
                        <th>id</th>
                        <th>username</th>
                        <th>password</th>
                        <th>email</th>
                        <th>settings</th>
                    </tr>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>
                                <c:out value="${u.id}"/>
                            </td>
                            <td>
                                <c:out value="${u.username}"/>
                            </td>
                            <td>
                                <c:out value="${u.password}"/>
                            </td>
                            <td>
                                <c:out value="${u.email}"/>
                            </td>
                            <td>
                                <a href="
                                <c:url value="/admin/user/upd">
                                    <c:param name="u_id" value="${u.id}" />
                                </c:url>
                            ">edit</a>
                            </td>
                            <td>
                                <a href="
                                <c:url value="/admin/user/del">
                                    <c:param name="u_id" value="${u.id}" />
                                </c:url>
                            ">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <h3>Roles</h3>
                <table>
                    <tr>
                        <th>username</th>
                        <th>role</th>
                        <th>settings</th>
                    </tr>
                    <tr>
                        <spring:form modelAttribute="user" method="post" action="/admin/user/role/add">
                            <td>
                                <spring:select multiple="false" class="inputTable" name="u_name" path="username">
                                    <c:forEach var="u" items="${users}">
                                        <option value="${u.username}">${u.username}</option>
                                    </c:forEach>
                                </spring:select>
                            </td>
                            <td>
                                <select multiple="false" class="inputTable" name="r_name">
                                    <c:forEach var="r" items="${roles}">
                                            <option value="${r.name}">${r.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input class="inputTable" type="submit" value="add">
                            </td>
                        </spring:form>
                    </tr>
                    <c:forEach var="u" items="${users}">
                        <c:forEach var="r" items="${u.roles}">
                        <tr>
                            <td>
                                <c:out value="${u.username}"/>
                            </td>
                            <td>
                                <c:out value="${r.name}"/>
                            </td>
                            <td>
                                <a href="
                                        <c:url value="/admin/user/role/del">
                                            <c:param name="u_name" value="${u.username}" />
                                            <c:param name="r_name" value="${r.name}" />
                                        </c:url>
                                    ">delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
