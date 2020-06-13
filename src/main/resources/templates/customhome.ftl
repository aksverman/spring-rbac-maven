<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.rudra.aks.model.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
</head>
<body>
<nav role="navigation">
    
    <#if deleteUsersList??>
    	<#list model["deleteUsersList"]?keys as k>
			${k}
		</#list>
	</#if>

	<#if name??>
		${name}
	</#if>
    
    <#if deleteUsersList??>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">Log out</button>
        </form>
    </#if>
    
</nav>
</body>
</html>