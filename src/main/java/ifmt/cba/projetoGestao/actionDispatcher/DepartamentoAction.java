package ifmt.cba.projetoGestao.actionDispatcher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.DepartamentoTabelaForm;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.model.Usuario.Perfil;
import ifmt.cba.projetoGestao.util.ErroDeViolacaoConstraint;

public class DepartamentoAction extends DispatchAction{
	
	public ActionForward cadastra(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DepartamentoTabelaForm formPreenchido = (DepartamentoTabelaForm) form;
		Departamento departamento = new Departamento();
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		
		
		if (formPreenchido.getNome().equals("")) {
			return new ActionForward("navegacao.do?action=dashboard&nomeDepartamentoNaoSelecionado=true",true);
		} else {			
			departamento.setNome(formPreenchido.getNome());
		}
		
		int responsavelFormId = request.getParameter("responsavelId") == null ? -1 : Integer.parseInt(request.getParameter("responsavelId"));
		
		if (responsavelFormId != -1) {
			for (Usuario u : listaU) {
				if (u.getId() == responsavelFormId) {  
					departamento.setResponsavel(u);
				}
			}
		} else {
			return new ActionForward("navegacao.do?action=dashboard&responsavelNaoSelecionado=true",true);
		}
		
		dao.persiste(departamento);
		
		departamento.setResponsavel(null);
		
		return mapping.findForward("dashboard");
	}
	
	public ActionForward atualiza(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		Departamento departamento = (Departamento) dao.buscaPorId("Departamento", Integer.parseInt(request.getParameter("id")));
		Usuario usuarioResponsavel = request.getParameter("usuarioResponsavelId").equals("----") ? null : (Usuario) dao.buscaPorId("Usuario", Integer.parseInt(request.getParameter("usuarioResponsavelId")));
		
		departamento.setNome(request.getParameter("nome"));
		
		if (usuarioResponsavel != null) {
			departamento.setResponsavel(usuarioResponsavel);
		}
		
		dao.edita(departamento);
		
		
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
		
			if (usuarioLogado.getPerfil() == Perfil.ADMIN) {
				try {
					dao.deleta(objeto);	
				} catch (ErroDeViolacaoConstraint e) {
					return new ActionForward("/navegacao.do?action=dashboard&departamentoComEntidadesVinculadas=true");
				}
				return mapping.findForward("dashboard");
			}
		
			return mapping.findForward("dashboard");
	}
}
