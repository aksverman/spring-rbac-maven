<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <title> Welcome User </title>
</head>
<body>

<h1> Welcome!  RBAC Securities !</h1>
	    <p> Hello User</p>
	    <b><c:out value="${pageContext.request.remoteUser}"/></b>
	    <hr color="red"/>

	<%-- <table cellspacing='12px'> 
	    <tr>
	    	<th rowspan="2"> <a href = "/rbac-security/user/updateForm"> Update User </a> </th> 
	    	<th rowspan="2"> <a href = "/rbac-security/user/listUsers"> List Users </a> </th> 
			<th rowspan="2"> <a href = "/rbac-security/user/deleteUser/${param.usertodelete}"> Delete User </a> </th> 
		</tr>
	</table> --%>
	
	<c:out value="${msg}"/><br/><br/>
	<a href = "/rbac-security/admin/changePasswordform/?userid=${userid}&token=dummytoken"> Change Password</a>
	
	<c:forEach items="${ privileges }" var = "privilege">
		<table cellspacing='12px'> <tr>
			<c:if test = "${privilege.name == 'EDIT' }"> 
	    		<th rowspan="2"><a href = "/rbac-security/user/updateForm"> Update User </a> </th> 
			</c:if>
			<c:if test = "${privilege.name == 'VIEW' }"> 
	    		<th rowspan="2"> <a href = "/rbac-security/user/listUsers"> List Users </a> </th> 
			</c:if>
			<c:if test = "${privilege.name == 'DELETE' }"> 
				<th rowspan="2"> 
				<form:form method="GET" modelAttribute="userform" action="/rbac-security/user/deleteUser">
						<form:select path = "username">
							<c:forEach items="${ deleteUsersList }" var = "userupdateid">
								<form:option path = "username" value="${userupdateid}"> ${userupdateid} </form:option>
							</c:forEach>
						</form:select> <br/> <br/>
						<input type = "submit"	value = "Delete"/>
				</form:form>
				</th> 
			</c:if>
		</tr></table>
	</c:forEach>
	 
	
	
	<form  action="/rbac-security/logout" method="post">
	      <input type="submit" value="Log out" />
	      <!--	HTTP ERROR 403
				Problem accessing /security-java/logout. Reason:
				Could not verify the provided CSRF token because your session was not found. -->
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
</body>
</html>