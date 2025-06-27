<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>    
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<html:form action="/identificaUsuario">
	<label>Usuario:</label><br>
	<input type="text"/><br>
	<label>Senha:</label><br>
	<input type="text"><br>
	<button>submit</button>
	</html:form>
</body>
</html>