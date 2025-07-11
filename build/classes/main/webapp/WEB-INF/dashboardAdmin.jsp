<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="box" uri="/WEB-INF/bomboxTag.tld" %>
<%@ taglib prefix="pag" uri="/WEB-INF/paginasTag.tld" %>
<%@ taglib prefix="not" uri="/WEB-INF/notificacaoTag.tld" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard admin</title>
<style>

select {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: white;
    color: #333;
    font-size: 14px;
    appearance: none; /* remove aparência nativa (no Firefox) */
    -webkit-appearance: none; /* remove aparência nativa (no Chrome) */
    -moz-appearance: none;
    background-image: url("data:image/svg+xml;utf8,<svg fill='%235b10c4' height='14' viewBox='0 0 24 24' width='14' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
    background-repeat: no-repeat;
    background-position: right 10px center;
    background-size: 14px 14px;
    cursor: pointer;
    transition: border-color 0.2s ease, box-shadow 0.2s ease;
    min-width: 150px;
}

select:focus {
    border-color: #5b10c4;
    box-shadow: 0 0 0 2px rgba(91, 16, 196, 0.2);
    outline: none;
}
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
    
    .more {
  cursor: pointer;
  border: none;
  background: transparent;
}

		.more span {
  display: block;
  width: .25rem;
  height: .25rem;
  background: #363636;
  border-radius: 50%;
  pointer-events: none;
}

.more span:not(:last-child) {
  margin-bottom: .125rem;
}

.menu-btn {
    background: none;
    border: none;
    font-size: 18px;
    cursor: pointer;
    padding: 4px;
}

.menu-popup {
    display: none;
    position: absolute;
    right: 35px;
    bottom: 20px;
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    z-index: 10;
    min-width: 95px;
    padding: 5px;
     z-index: 9999;
}

.menu-popup a {
	font-size: 15px;
    display: block;
    padding: 2px;
    text-decoration: none;
    color: #333;
}

.menu-popup a:hover {
    background-color: #f1eafe;
}

.botaoSalvar, .botaoAdicionar {
        padding: 10px;
        background-color: #5b10c4;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    
.botaoSalvar:hover, .botaoAdicionar:hover {
    	background-color: #4b08a8;
    }


 	input[type="text"] {
        min-width: 5rem;
        padding: 10px;
        margin-bottom: 5px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        padding: 10px;
        background-color: #5b10c4;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s ease;
        margin-bottom: 15px;
    }

    input[type="submit"]:hover {
        background-color: #4b08a8;
    }
    
    .formularioDepartamento select {
    	margin-bottom: 5px;
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
    
    .filtroDiv {
    	text-align: right;	
    	margin-bottom:5px;
    }

	tbody tr {
	    height: 50px;
	}
	
	header {
		margin-left: 98%;
	}
	
	header a {
		cursor: pointer;
	}
	

</style>
</head>
<body>
	<header><a href="/usuario.do?action=sair">sair</a></header>
	<div class="notificacoes">
		<c:if test="${param.responsavelNaoSelecionado == 'true'}">
				<not:notificacao tipoAlerta="Erro">Responsavel não selecionado</not:notificacao>
		</c:if>
		<c:if test="${param.formularioComElementosVazios == 'true'}">
				<not:notificacao tipoAlerta="Erro">Departamento não selecionado</not:notificacao>
		</c:if>
		<c:if test="${param.nomeDepartamentoNaoSelecionado == 'true'}">
				<not:notificacao tipoAlerta="Erro">Nome do departamento não selecionado</not:notificacao>
		</c:if>
		<c:if test="${param.usuarioComEntidadesVinculadas == 'true'}">
				<not:notificacao tipoAlerta="Erro">Usuário com entidades vinculadas</not:notificacao>
		</c:if>
		<c:if test="${param.departamentoComEntidadesVinculadas == 'true'}">
				<not:notificacao tipoAlerta="Erro">Departamento com entidades vinculadas</not:notificacao>
		</c:if>
		<c:if test="${param.usuarioJaLogado == 'true'}">
				<not:notificacao tipoAlerta="Aviso">Usuario já logado em: <c:out value="${usuarioLogado.login}"></c:out>, saia para entrar em outra conta!</not:notificacao>
		</c:if>
	</div>
	
	
	<div>
		<h3>Solicitações <button class="botaoAdicionar" style="height: 30px; line-height: 7px">+</button></h3>
		<div class="formularioAdiciona" style="display: none;">
			<html:form action="/solicitacao.do?action=cadastra">
				<label>Departamento responsavel:</label><br>
				<box:bombox atributoName="departamentoResponsavel" lista="${todosDepartamentos}"/><br>
				<label>Titulo:</label><br>
				<html:text property="titulo" /><br>
				<label>Descrição:</label><br>
				<html:text property="descricao" /><br>
				<html:submit value="Adicionar"/>
			</html:form>
		</div>
		<div class="filtroDiv">
			<span style="margin-bottom: 5px;">Filtrar por: </span>
			<select style='height: 36px' class="filtro1">
				<option>Nenhum</option>
				<c:forEach items="${todosDepartamentos}" var="departamento">
					<option>${departamento.nome}</option>
				</c:forEach>
			</select>
			<select style='height: 36px' class="filtro2">
				<option>Nenhum</option>
				<option>Aberta</option>
				<option>Em andamento</option>
				<option>Finalizada</option>
			</select>
		</div>
	</div>
	<div>
	<table style='margin-bottom: 5px;' class="tabelaSolicitacao">
		<thead>
			<tr>
				<th>Departamento responsavel</th>
				<th>Titulo</th>
				<th>Descrição</th>
				<th>Solicitante</th>
				<th>Data de criação</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${empty  solicitacoes}"> <!-- mudar pra c:choose otherwise depois -->
				<tr>
					<td colspan="7" style="text-align:center; color: #999;">Nenhuma solicitação disponivel</td>
						
				</tr>		
			</c:if>
			
			 <c:forEach items="${solicitacoes}" var="solicitacao">
				<tr>
					<td data-id="${solicitacao.departamento_responsavel.id}"><c:out value="${solicitacao.departamento_responsavel.nome}"></c:out></td>
					<td><c:out value="${solicitacao.titulo}"></c:out></td>
					<td><c:out value="${solicitacao.descricao}"></c:out></td>
					<c:choose>
						<c:when test="${solicitacao.solicitante == null}">
							<td><i>[solicitante excluído]</i></td>
						</c:when>
						<c:otherwise>
							<td data-id="${solicitacao.solicitante.id}"><c:out value="${solicitacao.solicitante.nome}"></c:out></td>
						</c:otherwise>
					</c:choose>
					<td><fmt:formatDate value="${solicitacao.data_criacao}" pattern="dd/MM/yyyy" /></td>
					<c:choose>
						<c:when test="${solicitacao.status == 'EM_ANDAMENTO' }">
							<td>EM ANDAMENTO</td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${solicitacao.status}"></c:out></td>
						</c:otherwise>
					</c:choose>
					<td style="display: none;" class="idSolicitacao"><c:out value="${solicitacao.id}"></c:out></td>
					<td class = "tdBotaoMenu" style="position: relative; overflow: visible; text-align: right;">
				        <button class="menu-btn">⋯</button>
				        <div class="menu-popup">
				            <a href="#" class="editarSolicitacaoLink">Editar</a>
				            <a href="#" class="exluirLink">Excluir</a>
				        </div>
    				</td>	
				</tr>
			</c:forEach> 
		</tbody>
	</table>
		<pag:pagination totalPaginas="${totalPaginasSolicitacao}" classe="Solicitacao" paginaAtual="${paginaAtualSolicitacao}"/>
	</div>
	
	<div>
	<h3>Departamentos <button class="botaoAdicionar" style="height: 30px; line-height: 7px">+</button></h3>
		<div class="formularioAdiciona" style="display: none;">
		<html:form action="/departamento.do?action=cadastra" styleClass="formularioDepartamento">
		  <label>Nome do departamento:</label><br>
		  <input type="text" name="nome"/><br>
		  <label>Reponsavel:</label><br>
		  <box:bombox atributoName="responsavelId" lista="${todosUsuarios}"/><br>
		  <html:submit value="Adicionar"/>
		</html:form>
		</div>
		</div>
	<div>
	<table style='margin-bottom: 5px;'>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Responsavel</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty  departamentos}"> <!-- mudar pra c:choose otherwise depois -->
				<tr>
					<td colspan="7" style="text-align:center; color: #999;">Nenhum departamento disponivel</td>
						
				</tr>		
			</c:if>
			
			 <c:forEach items="${departamentos}" var="departamento">
				<tr>
					<td><c:out value="${departamento.nome}"></c:out></td>
					<c:choose>
						<c:when test="${departamento.responsavel == null }">
							<td><i>[responsavel excluído]</i></td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${departamento.responsavel.nome}"></c:out></td>
						</c:otherwise>
					</c:choose>
					<td style="display: none;" class="idDepartamento"><c:out value="${departamento.id}"></c:out></td>
					<td class = "tdBotaoMenu" style="position: relative; overflow: visible; text-align: right;">
				        <button class="menu-btn">⋯</button>
				        <div class="menu-popup">
				            <a href="#" class="editarDepartamentoLink">Editar</a>
				            <a href="#" class="exluirLink">Excluir</a>
				        </div>
    				</td>	
				</tr>
			</c:forEach> 
		</tbody>
	</table>
	<pag:pagination totalPaginas="${totalPaginasDepartamento}" classe="Departamento" paginaAtual="${paginaAtualDepartamento}"/>
	</div>
	
		<div>
	<h3>Usuarios </h3>
	</div>
		<div>
	<table style='margin-bottom: 5px;'>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Perfil</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty  usuarios}"> <!-- mudar pra c:choose otherwise depois -->
				<tr>
					<td colspan="7" style="text-align:center; color: #999;">Nenhum usuário disponivel</td>
						
				</tr>		
			</c:if>
			
			 <c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td><c:out value="${usuario.nome}"></c:out></td>
					<td><c:out value="${usuario.login}"></c:out></td>
					<td ><c:out value="${usuario.perfil}"></c:out></td>
					<td style="display: none;" class="idUsuario"><c:out value="${usuario.id}"></c:out></td>
					<td class = "tdBotaoMenu" style="position: relative; overflow: visible; text-align: right;">
					    <c:if test="${ usuario.perfil != 'ADMIN' || usuarioLogado.id == usuario.id}">
						    <button class="menu-btn">⋯</button>
						    <div class="menu-popup">
						       <a href="#" class="editarUsuarioLink">Editar</a>
						       <a href="#" class="exluirLink">Excluir</a>
						    </div>
					    </c:if>
	    			</td>	
				</tr>
			</c:forEach> 
		</tbody>
	</table>
	<pag:pagination totalPaginas="${totalPaginasUsuario}" classe="Usuario" paginaAtual="${paginaAtualUsuario}"/>
	</div>
	
	<form class="formSolicitacaoEdicao" method="post" action="/solicitacao.do?action=atualiza" style="display: none;">
	  <input type="hidden" name="id" />
	  <input type="hidden" name="titulo" />
	  <input type="hidden" name="descricao" />
	  <input type="hidden" name="status" />
	  <input type="hidden" name="departamentoResponsavelId" />
	</form>
	
	<form class="formUsuarioEdicao" method="post" action="/usuario.do?action=atualiza" style="display: none;">
	  <input type="hidden" name="nome" />
	  <input type="hidden" name="login" />
	  <input type="hidden" name="perfil" />
	  <input type="hidden" name="id" />
	</form>
	
	<form class="formDepartamentoEdicao" method="post" action="/departamento.do?action=atualiza" style="display: none;">
	  <input type="hidden" name="nome" />
	  <input type="hidden" name="usuarioResponsavelId" />
	  <input type="hidden" name="id" />
	</form>
	
	<form class="formExclusaoUsuario" method="post" action="/usuario.do?action=exclui" style="display: none;">
	  <input type="hidden" name="id" />
	  <input type="hidden" name="tipo" />
	</form>
	
	<form class="formExclusaoDepartamento" method="post" action="/departamento.do?action=exclui" style="display: none;">
	  <input type="hidden" name="id" />
	  <input type="hidden" name="tipo" />
	</form>
	
	<form class="formExclusaoSolicitacao" method="post" action="/solicitacao.do?action=exclui" style="display: none;">
	  <input type="hidden" name="id" />
	  <input type="hidden" name="tipo" />
	</form>
	
	
	
	<script>
	
	selectsFiltra = document.querySelector(".filtroDiv").querySelectorAll("select")
	selectsFiltra.forEach(select => {
		select.addEventListener('change', function () {
			trs = document.querySelector(".tabelaSolicitacao").querySelector("tbody").querySelectorAll("tr")
			let filtros = []
			selectsFiltra.forEach(select => {
				if (select.value == "Nenhum") {
					
				} else {
					filtros.push(select.value)
				}
				
			})
			trs.forEach(tr => {
				if (tr) {
						if (filtros.every(filtro => tr.textContent.toUpperCase().includes(filtro.toUpperCase())) == false) {
							tr.style.display = "none";
						}  else {
							tr.style.display = "";
						}
				}
			})
			
		})
	})

	
	document.querySelectorAll(".exluirLink").forEach(link => {
		link.addEventListener('click',function(e) {
			e.preventDefault();
			const tr = this.closest('tr');
			const tds = tr.querySelectorAll('td');
			
			for (const td of tds) {
				if (td.classList.contains("idUsuario") ) {
					let form = document.querySelector(".formExclusaoUsuario");
					let id = form.querySelector('[name="id"]');
					let tipo = form.querySelector('[name="tipo"]');
			
					id.value = td.textContent.trim();
					tipo.value = "Usuario";
					form.submit();
					break;
				} else if (td.classList.contains("idDepartamento")) {
					let form = document.querySelector(".formExclusaoDepartamento");
					let id = form.querySelector('[name="id"]');
					let tipo = form.querySelector('[name="tipo"]');
					
					id.value = td.textContent.trim();
					tipo.value = "Departamento"; 
					form.submit();
					break;
				} else if (td.classList.contains("idSolicitacao")) {
					let form = document.querySelector(".formExclusaoSolicitacao");
					let id = form.querySelector('[name="id"]');
					let tipo = form.querySelector('[name="tipo"]');
					
					id.value = td.textContent.trim();
					tipo.value = "Solicitacao"; 
					form.submit();
					break;
				}
			}
			
			
		 });
	});
	
	formularios = document.querySelectorAll(".formularioAdiciona");
	document.querySelectorAll(".botaoAdicionar").forEach(button => {
	    button.addEventListener("click", () => {
	        
	        const container = button.closest("div");

	       
	        const form = container.querySelector(".formularioAdiciona");

	        if (form) {
	            const isVisible = window.getComputedStyle(form).display === "block";
	            form.style.display = isVisible ? "none" : "block";
	        }
	    });
	});

	//solicitacao
	document.querySelectorAll(".editarSolicitacaoLink").forEach(link => {
		link.addEventListener('click',function(e) {
			e.preventDefault();
			
			const tr = this.closest('tr');
			const tds = tr.querySelectorAll('td');
			function tdParaInput(td) {
	            const texto = td.textContent;
	            const input = document.createElement('input');
	            input.type = 'text';
	            input.value = texto;
	            td.textContent = '';
	            td.appendChild(input);
	        }
			
			tds[0].innerHTML = `
				<box:bombox atributoName="departamento" lista="${todosDepartamentos}"/>
			`
			
			 // mudar para bombox
			tdParaInput(tds[1]);
			tdParaInput(tds[2]);
			tds[5].innerHTML = `
				<select>
					<option>----</option>
					<option>ABERTA</option>
					<option>EM ANDAMENTO</option>
					<option>FINALIZADA</option>
				</select>
			`
			
			let botaoSalvar = tr.querySelector(".tdBotaoMenu").innerHTML = `<button class="botaoSalvar">salvar</button>`;
			botaoSalvar = tr.querySelector("button");
			botaoSalvar.addEventListener('click', ()=> {
				const departamentoResponsavelId = tds[0].querySelector('select').value;
				const titulo = tds[1].querySelector('input').value;
				const descricao = tds[2].querySelector('input').value;
				const status = tds[5].querySelector('select').value;
				const id = tds[6].textContent;
				
				const form = document.querySelector(".formSolicitacaoEdicao");
				form.querySelector('[name="departamentoResponsavelId"]').value= departamentoResponsavelId;
				form.querySelector('[name="titulo"]').value= titulo;
				form.querySelector('[name="descricao"]').value= descricao;
				form.querySelector('[name="status"]').value= status;
				form.querySelector('[name="id"]').value= id;
				
				form.submit();
				
			})
		})
	})
	
			
			
	
	//usuario
	document.querySelectorAll(".editarUsuarioLink").forEach(link => {
		link.addEventListener('click',function(e) {
			e.preventDefault();
			
			const tr = this.closest('tr');
			const tds = tr.querySelectorAll('td');
			
			function tdParaInput(td) {
	            const texto = td.textContent;
	            const input = document.createElement('input');
	            input.type = 'text';
	            input.value = texto;
	            td.textContent = '';
	            td.appendChild(input);
	        }
			
			tdParaInput(tds[0]); // mudar para bombox
			tdParaInput(tds[1]);
			tds[2].innerHTML = `
				<select>
					<option disabled>----</option>
					<option>PADRÃO</option>
					<option>ADMIN</option>
				</select>	
			`
			
			let botaoSalvar = tr.querySelector(".tdBotaoMenu").innerHTML = `<button class="botaoSalvar">salvar</button>`;
			botaoSalvar = tr.querySelector("button");
			botaoSalvar.addEventListener('click', ()=> {
				const nome = tds[0].querySelector('input').value;
				const login = tds[1].querySelector('input').value;
				const perfil = tds[2].querySelector('select').value; //mudar para bombox
				const id = tds[3].textContent;
				
				
				const form = document.querySelector(".formUsuarioEdicao");
				form.querySelector('[name="nome"]').value= nome;
				form.querySelector('[name="login"]').value= login;
				form.querySelector('[name="perfil"]').value= perfil;
				form.querySelector('[name="id"]').value= id;
				
				form.submit();
				
			})
		})
	})

	//departamento
	document.querySelectorAll(".editarDepartamentoLink").forEach(link => {
		link.addEventListener('click',function(e) {
			e.preventDefault();
			
			const tr = this.closest('tr');
			const tds = tr.querySelectorAll('td');
			
			function tdParaInput(td) {
	            const texto = td.textContent;
	            const input = document.createElement('input');
	            input.type = 'text';
	            input.value = texto;
	            td.textContent = '';
	            td.appendChild(input);
	        }
			
			tdParaInput(tds[0]); 
			
			tds[1].innerHTML = `
				<box:bombox atributoName="usuario" lista="${todosUsuarios}"/>
			`	
			
			
			let botaoSalvar = tr.querySelector(".tdBotaoMenu").innerHTML = `<button class="botaoSalvar">salvar</button>`;
			botaoSalvar = tr.querySelector("button");
			botaoSalvar.addEventListener('click', ()=> {
				const nome = tds[0].querySelector('input').value;
				const usuarioResponsavelId = tds[1].querySelector('select').value;
				const id = tds[2].textContent;
				
				const form = document.querySelector(".formDepartamentoEdicao");
				form.querySelector('[name="nome"]').value= nome;
				form.querySelector('[name="usuarioResponsavelId"]').value= usuarioResponsavelId;
				form.querySelector('[name="id"]').value= id;
				
				form.submit();
				
			})
		})
	})

	
		document.addEventListener('DOMContentLoaded', function () {
		    document.querySelectorAll('.menu-btn').forEach(button => {
		        button.addEventListener('click', function (event) {
		            event.stopPropagation(); // <- impede que o clique propague e feche o menu
	
		            const menu = this.nextElementSibling;
	
		            // Fecha outros menus
		            document.querySelectorAll('.menu-popup').forEach(m => {
		                if (m !== menu) m.style.display = 'none';
		            });
	
		            // Alterna o menu clicado
		            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
		        });
		    });
	
		    // Fecha todos os menus se clicar fora
		    document.addEventListener('click', function () {
		        document.querySelectorAll('.menu-popup').forEach(m => m.style.display = 'none');
		    });
		    
		});
	</script>
</body>
</html>