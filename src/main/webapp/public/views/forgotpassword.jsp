<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Password Reset Form</title>
</head>

<body>
 	<h2>Password Reset Form</h2>
 	<form action="/rbac-security/admin/resetpassowrd" method="get">
 		<input type="text" name="username"/> Username <br/><br/>
 		<input type = "submit" value="Submit"/>
 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 	</form>
 	<%-- <form:form method="POST" modelAttribute="resetform" action="/rbac-security/admin/resetpassowrd">
					
		<label for="username">User Name</label>
			<form:input type="text" path="emailid" id="emailid"/> <br/> <br/>
		
		<label for="role">User-Role</label>
			<form:select path="role">
				<form:option path="role" value="DEV"> DEV </form:option>
				<form:option path="role" value="QA">   QA </form:option>
				<form:option path="role" value="PMT"> PMT </form:option> <br/> <br/>
			</form:select>
		
		<input type = "submit"	value = "Submit"/>
	</form:form> --%>
</body>			
</html>
					