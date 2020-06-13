<html>
<head>
   <title> Adding Roles for User </title>
</head>
<body>
<h2> Add new ROLE </h2>

	<fieldset>
		<form name = "roleform" action="${rc.contextPath}/role/addRole" method="POST">
			Role-Name   : <input type = "text" name="name"> 
			Privileges  : 
				<input type = "checkbox" name = "privileges" value = "READ"> READ <br/>
				<input type = "checkbox" name = "privileges" value = "WRITE"> WRITE <br/>
				<input type = "checkbox" name = "privileges" value = "DELETE"> DELETE <br/>
				<input type = "checkbox" name = "privileges" value = "ALL"> ALL <br/>
				
			
			<input type = "submit" value=" Add Role "/>
		</form>
	</fieldset>
</body>
</html>