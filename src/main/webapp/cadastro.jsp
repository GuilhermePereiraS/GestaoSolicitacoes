<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<html:form action="/cadastraUsuario">
	<label>Insira seu nome</label>
	<html:text property="nome"/>
	<label>Senha:</label>	
	<html:text property="senha"/><br>
	<label>Usuario</label>
	<html:text property="login"/><br>
	<input type="submit" value="Cadastre-se"/>
</html:form>

<!-- se o cadastro tiver dado certo ou errado, mostra pop up de erro ou confirmação com link para o login -->


</body>
</html>