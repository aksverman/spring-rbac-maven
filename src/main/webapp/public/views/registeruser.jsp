<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
</head>

<body>
 	<h2>User Registration Form</h2>
 	<form:form method="POST" modelAttribute="userform" action="/rbac-security/user/addUser">
		<form:input type="hidden" path="userid"/>
		
		<label for="username">UserName</label>
				<form:input type="text" path="username" id="username"/> <br/> <br/>
		
		<label for="password">Password</label>
				<form:input type="password" path="password" id="password"/> <br/> <br/>
		
		<label for="emailid">Email-ID</label>
				<form:input type="text" path="emailid" id="emailid"/> <br/> <br/>
		
		<label for="role">User-Role</label>
			<form:select path="role">
				<form:option path="role" value="DEV"> DEV </form:option>
				<form:option path="role" value="QA">   QA </form:option>
				<form:option path="role" value="PMT"> PMT </form:option> <br/> <br/>
			</form:select>
		
		<input type = "submit"	value = "Submit"/>
	</form:form>
</body>			
</html>
					