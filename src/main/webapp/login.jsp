<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>   
<%@ taglib prefix="not" uri="/WEB-INF/notificacaoTag.tld" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
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
    
	.aviso {
	  padding: 15px 20px;
	  border-radius: 10px;
	  color: white;
	  font-weight: 600;
	  font-size: 1rem;
	  text-align: center;
	  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	  font-family: Arial, sans-serif;
	}
	
	.aviso button {
	  margin-top: 12px;
	  padding: 8px 16px;
	  border: none;
	  border-radius: 8px;
	  cursor: pointer;
	  font-weight: bold;
	  color: #333;
	  background-color: #f0f0f0;
	  transition: background-color 0.3s ease;
	}
	
	.aviso button:hover {
	  background-color: #ddd;
	}
    
    .notificacoes {
    	top: 10px;
    	left: 50%;
    	transform: translate(-50%);
    	position: fixed;
    	display: flex;
    	flex-direction: column;
    	gap: 1rem;
    }
    
</style>
</head>
<body>
	
	<div class="notificacoes">
		<c:if test="${loginNaoEncontrado}">
			<not:notificacao tipoAlerta="Erro">Usuario não encontrado</not:notificacao>
		</c:if>
		<c:if test="${senhaIncorreta}">
			<not:notificacao tipoAlerta="Aviso">Senha incorreta</not:notificacao>
		</c:if>	
		
		<c:if test="${usuarioCadastrado != null }">
			<c:choose>
				<c:when test="${usuarioCadastrado }">
					<not:notificacao tipoAlerta="Sucesso">Usuario cadastrado com sucesso</not:notificacao>
				</c:when>
				<c:otherwise>
					<not:notificacao tipoAlerta="Erro">Usuario não pode ser cadastrado</not:notificacao>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${usuarioNulo}">
			<not:notificacao tipoAlerta="Aviso">Usuario desconectado</not:notificacao>
		</c:if>
	</div>
	
	
	
	<html:form action="/usuario.do?action=autentica">
		<label>Usuario:</label><br>
		<html:text property="login"/><br>
		<html:errors property="login"/>
		<label>Senha:</label><br>
		<html:password property="senha"/><br>
		<html:submit value="Login"/>
	</html:form>
	
	<html:errors property="senha"/>
	
	<label style="margin-left: 30px">Não tem uma conta?</label> <a href="cadastro.jsp" style="margin-left: 15px">cadastre-se aqui</a>
</body>
</html>