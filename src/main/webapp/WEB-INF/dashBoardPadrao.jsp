<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f4fa;
        margin: 20px;
    }

    h3 {
        color: #5b10c4;
        font-size: 24px;
        margin-bottom: 15px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }

    th {
        background-color: #5b10c4;
        color: white;
        padding: 12px;
        text-align: left;
    }

    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    tr:hover {
        background-color: #f1eafe;
    }

    a {
        text-decoration: none;
        color: #5b10c4;
        font-weight: bold;
    }

    a:hover {
        color: #4b08a8;
        text-decoration: underline;
    }

</style>
</head>
<body>

	<h3>Solicitações</h3>
	<div>
	<table>
		<thead>
			<tr>
				<th>Departamento responsavel</th>
				<th>Titulo</th>
				<th>Descrição</th>
				<th>Solicitante</th>
				<th>Data de criação</th>
				<th>Status</th>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${empty  solicitacoes}"> <!-- mudar pra c:choose otherwise depois -->
				<tr>
					<td colspan="6" style="text-align:center; color: #999;">Nenhuma solicitação disponivel</td>
					<td style="position: relative;">aa<td/>					
				</tr>		
			</c:if>
			
			<c:forEach items="${solicitacoes}">
			
			</c:forEach>
		</tbody>
	</table>
	</div>

</body>
</html>