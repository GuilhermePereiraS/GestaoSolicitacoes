<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<style>

table {
	border-collapse: collapse;
	border: solid 2px #b8dbd0;
}


tr,td,th {
	border: 2px, solid;
	padding: 5px;
	border-radius: 5px;
}

a {
	padding: 2px;
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
	</table>
	</div>
	
	<h3>Usuarios</h3>
		<div>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Perfil</th>
			</tr>
		</thead>
	</table>
	</div>
	
	<h3>Departamentos</h3>
		<div>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Responsavel</th>
			</tr>
		</thead>
	</table>
	</div>
</body>
</html>