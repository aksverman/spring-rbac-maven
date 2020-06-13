<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Access Denied</title>
</head>
<body>
  <h3> Sorry! You don't have access to this requested resource. <br/> Try again!
  <b><c:out value="${pageContext.request.remoteUser}"/></b></h3>
  <a href="/rbac-security/"> Home </a>
  <br/><br/>