<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>changing user password</title>
</head>

<body>
 	<h2>Change Your Password</h2>
 	<c:set value="${userid}" var="id"/>
 	<form name="changepass" action="/rbac-security/admin/changepassowrd" method="post">
 		<input type="hidden" name="userid" value="${id}"/>  <br/><br/>
 		
 		<input type="password" name="password"/> <br/><br/>
 		<!-- <input type="password" name="repassword"/> <br/><br/> -->
 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 		<input type = "submit" value="Submit"/>
 	</form>
</body>
</html>