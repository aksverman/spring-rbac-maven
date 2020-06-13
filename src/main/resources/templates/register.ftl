
<html>
<head>
   <title> Register a New User </title>
</head>
<body>
<h2> Add new User </h2>

	<fieldset>
		<form name = "userform" action="${rc.contextPath}/user/addUser" method="POST">
			Username  : <input type="text" name="username"> <br/>
			Email-ID  : <input type="text" name="emailid">  <br/>
			Password  : <input type="text" name="password">  <br/>
			
			
			<input type = "submit" value=" Add User "/>
		</form>
	</fieldset>
</body>
</html>