package ifmt.cba.projetoGestao.actionDispatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.SolicitacaoTabelaForm;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;

public class SolicitacaoAction extends DispatchAction{

	public ActionForward cadastra(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SolicitacaoTabelaForm formPreenchido = (SolicitacaoTabelaForm) form;
		Solicitacao solicitacao = new Solicitacao();
		Date dataAtual = new Date();
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		List<Departamento> listaD = dao.lista("Departamento");
		
		
		solicitacao.setData_criacao(dataAtual);
		solicitacao.setTitulo(formPreenchido.getTitulo());
		solicitacao.setDescricao(formPreenchido.getDescricao());
		solicitacao.setSolicitante(usuarioLogado);
		solicitacao.setStatus("ABERTA");
		
		if (request.getParameter("departamentoResponsavel") == null	) {
			return new ActionForward("navegacao.do?action=dashboard&formularioComElementosVazios=true",true);
		}
		for (Departamento d : listaD) {
			if (d.getId() == Integer.parseInt(request.getParameter("departamentoResponsavel"))) {
				solicitacao.setDepartamento_responsavel(d);
			}
		}
		
		dao.persiste(solicitacao);
		
		return mapping.findForward("dashboard");
	}
	
	public ActionForward atualiza(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		List<Departamento> listaD = dao.lista("Departamento");	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Solicitacao solicitacao = (Solicitacao) dao.buscaPorId("Solicitacao", Integer.parseInt(request.getParameter("id")));
		
		solicitacao.setTitulo(request.getParameter("titulo"));
		solicitacao.setDescricao(request.getParameter("descricao"));
		
		if (!request.getParameter("status").equals("----")) {
			solicitacao.setStatus(request.getParameter("status"));
		} 
		
		int DepartamentoId = request.getParameter("departamentoResponsavelId").equals("----") ? -1 : Integer.parseInt(request.getParameter("departamentoResponsavelId"));
		
		
		if (DepartamentoId != -1) {
			for (Departamento d : listaD) {
				if (d.getId() == DepartamentoId) {
					solicitacao.setDepartamento_responsavel(d);
				}
			}	
		} 
		
		dao.edita(solicitacao);
		
		return mapping.findForward("dashboard");
	}
	
	public ActionForward exclui(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");
		Object objeto = dao.buscaPorId(tipo, id);
		
			if (usuarioLogado.getPerfil().equals("PADRÃO")) {
				Solicitacao solicitacao = (Solicitacao) objeto;	
				if (solicitacao.getSolicitante().getId() == usuarioLogado.getId()) {
					dao.deleta(objeto);	
				}
				return mapping.findForward("dashboard");
			} else if (usuarioLogado.getPerfil().equals("ADMIN")) {
				dao.deleta(objeto);	
				return mapping.findForward("dashboard");
			}
			
		return mapping.findForward("dashboard");
	}
}
