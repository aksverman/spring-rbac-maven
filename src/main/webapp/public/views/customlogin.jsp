<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Custom User Login Form</title>
</head>

<body>

 <form action="/rback-security/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label for="username">Username</label>
    <input type="text" name="username" id="username" required autofocus/>

    <label for="password">Password</label>
    <input type="password" name="password" id="password" required/>

    <label for="remember-me">Remember me</label>
    <input type="checkbox" name="remember-me" id="remember-me"/>

    <input type="submit" value = "Login">
</form>
</body>
</html>

