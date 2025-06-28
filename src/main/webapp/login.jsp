<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #ece9e6, #ffffff);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    form {
        background-color: #fdfdfd;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        min-width: 300px;
    }

    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
        color: #333;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #5b10c4;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #4b08a8;
    }

    a {
        color: #0066cc;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    .footer {
        text-align: center;
        margin-top: 15px;
        font-size: 0.9em;
        color: #555;
    }
</style>
</head>
<body>
	<html:form action="/autenticaUsuario">
	<label>Usuario:</label><br>
	<html:text property="login"/><br>
	<label>Senha:</label><br>
	<html:text property="senha"/><br>
	<input type="submit" value="login"/>
	</html:form>
	<label style="margin-left: 30px">Não tem uma conta?</label> <a href="cadastro.jsp" style="margin-left: 15px">cadastre-se aqui</a>
</body>
</html>