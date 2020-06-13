#rest call 
Clean this application and run task appstart.
Use postman to test spring security with rest.
To make rest call to UserRestController for curd of user service, use below rest calls:

#1. To add a single user {POST}:		localhost:8077/rbac-security/rest-user/addUser
			json for this call :
						{
						"username":"test2",
						"emailid":"test2@gmail.com",
						"password":"test2",
						"role": {
								"name":"DEV"	
							}
						}
						
#2. To delete a user {DELETE}:	localhost:8077/rbac-security/rest-user/deleteUser/{username}
		pass username as request parameter to delete that user.
		
#3. To update an existing user {PUT}:  localhost:8077/rbac-security/rest-user/updateUser
			json for this call :
						{
						"username":"test2",
						"emailid":"test2@gmail.com",
						"password":"test2",
						"role": {
								"name":"DEV"	
							}
						}
#4. To get list of users {GET}:	localhost:8077/rbac-security/rest-user/

#5. To get single user by id {GET}: localhost:8077/rbac-security/rest-user/getUser/{8}
		pass userid as request parameter to get that user details.
		
		