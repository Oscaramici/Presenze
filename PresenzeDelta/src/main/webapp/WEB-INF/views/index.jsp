<%-- 
    Document   : index
    Created on : 31-dic-2018, 15.44.00
    Author     : AdminDSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
  <body>
	<h1>Spring MVC 5 + Spring Security 5 + Hibernate 5 example</h1>
	<h2>${message}</h2>
	
	<form action="/logout" method="post">
		<input value="Logout" type="submit">
	</form>
</body>
</html>