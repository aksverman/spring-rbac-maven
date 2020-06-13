<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <title> RBAC Security </title>
</head>
<body>
<h1> Welcome!  RBAC Securities !</h1>
	    <p> Hello User</p>
	    <b><c:out value="${username}"/></b>
	    <hr color="red"/>
	<c:url value="${pageContext.request.contextPath}" var="contextpath"/>
	<table cellspacing='12px'> 
	    <tr>
	     	<th rowspan="2"> <a href = "/rbac-security/user/registerForm"> Create User </a> </th>
			<th rowspan="2"> <a href = "/rbac-security/login"> Login </a> </th>
			<th rowspan="2"> <a href = "/rbac-security/admin/resetpasswordform"> Reset Password</a> </th>
		</tr>
	</table>
	
	<c:out value="${msg}"/>
	<form  action="/rbac-security/logout" method="post">
	      <input type="submit" value="Log out" />
	      <!--	HTTP ERROR 403
				Problem accessing /security-java/logout. Reason:
				Could not verify the provided CSRF token because your session was not found. -->
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>