<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title> Users List Page </title>
</head>
<body>
  <h2>welcome  !!!</h2>
  <a href="/rbac-security//user/successlogin"> Home </a><br/><br/>
  <table cellspacing="10px">
  		<tr align="center"><th>User Name</th>
  			<th>User' Role</th>
  			<th>Email Id</th></tr>
  <c:forEach items="${userlistmodel}" 	var = "user">
  	
  		<tr align="center"><td>${user.username}</td>
  		<td>${user.role}</td>
  		<td>${user.emailid}</td></tr>
  	
  </c:forEach>
  </table>
</body>
</html>