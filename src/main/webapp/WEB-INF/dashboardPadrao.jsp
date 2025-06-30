<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
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

</style>
</head>
<body>

	<h3>Solicitações <button class="botaoAdicionar" style="height: 30px; line-height: 7px">+</button></h3>
	<div class="formularioAdiciona" style="display: block;">
		<html:form action="/adicionaSolicitacao">
		  <label>Departamento responsavel:</label><br>
		  <input type="text" name="departamentoResponsavel"/><br>
		  <label>Titulo:</label><br>
		  <html:text property="titulo" /><br>
		  <label>Descrição:</label><br>
		  <html:text property="descricao" /><br>
		  <html:submit value="Adicionar"/>
		</html:form>
	</div>
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
					<td data-id="${solicitacao.solicitante.id}"><c:out value="${solicitacao.solicitante.nome}"></c:out></td>
					<td><fmt:formatDate value="${solicitacao.data_criacao}" pattern="dd/MM/yyyy" /></td>
					<td><c:out value="${solicitacao.status}"></c:out></td>
					<td style="display: none;"><c:out value="${solicitacao.id}"></c:out></td>
					<td class = "tdBotaoMenu" style="position: relative; overflow: visible; text-align: right;">
				        <button class="menu-btn">⋯</button>
				        <div class="menu-popup">
				            <a href="#" class="editarLink">Editar</a>
				            <a href="#">Excluir</a>
				        </div>
    				</td>	
				</tr>
			</c:forEach> 
		</tbody>
	</table>
	</div>
	
	<form class="formEdicao" method="post" action="/atualizaSolicitacao.do" style="display: none;">
	  <input type="hidden" name="id" />
	  <input type="hidden" name="titulo" />
	  <input type="hidden" name="descricao" />
	  <input type="hidden" name="dataCriacao" />
	  <input type="hidden" name="status" />
	  <input type="hidden" name="solicitanteId" />
	  <input type="hidden" name="departamentoResponsavelId" />
	</form>

<script>

document.querySelector(".botaoAdicionar").addEventListener("click", ()=> {
	document.querySelector(".formularioAdiciona").style.display = document.querySelector(".formularioAdiciona").style.display == "block" ? "none" : "block" 
})

document.querySelectorAll(".editarLink").forEach(link => {
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
		tdParaInput(tds[2]);
		tdParaInput(tds[3]);
		tdParaInput(tds[4]); // mudar para caixa de calendario
		tdParaInput(tds[5]); // mudar para bombox
		
		let botaoSalvar = tr.querySelector(".tdBotaoMenu").innerHTML = `<button class="botaoSalvar">salvar</button>`;
		botaoSalvar = tr.querySelector("button");
		botaoSalvar.addEventListener('click', ()=> {
			const departamentoResponsavelId = tds[0].getAttribute("data-id");
			const titulo = tds[1].querySelector('input').value;
			const descricao = tds[2].querySelector('input').value;
			const solicitanteId = tds[3].getAttribute("data-id");
			const dataCriacao = tds[4].querySelector('input').value;
			const status = tds[5].querySelector('input').value;
			const id = tds[6].textContent;
			
			const form = document.querySelector(".formEdicao");
			form.querySelector('[name="departamentoResponsavelId"]').value= departamentoResponsavelId;
			form.querySelector('[name="titulo"]').value= titulo;
			form.querySelector('[name="descricao"]').value= descricao;
			form.querySelector('[name="solicitanteId"]').value= solicitanteId;
			form.querySelector('[name="dataCriacao"]').value= dataCriacao;
			form.querySelector('[name="status"]').value= status;
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