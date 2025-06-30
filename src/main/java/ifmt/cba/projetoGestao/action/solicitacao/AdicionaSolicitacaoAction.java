package ifmt.cba.projetoGestao.action.solicitacao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.SolicitacaoTabelaForm;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;

public class AdicionaSolicitacaoAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		Solicitacao solicitacao = new Solicitacao();
		SolicitacaoTabelaForm formPreenchido = (SolicitacaoTabelaForm) form;
		List<Departamento> listaD = dao.lista("Departamento");
		Date dataAtual = new Date();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		for (Departamento d : listaD) {
			if (d.getNome().equals(request.getParameter("departamentoResponsavel"))) {
				solicitacao.setDepartamento_responsavel(d);
			}
		}
		solicitacao.setData_criacao(dataAtual);
		solicitacao.setDescricao(formPreenchido.getDescricao());
		solicitacao.setSolicitante(usuarioLogado);
		solicitacao.setTitulo(formPreenchido.getTitulo());
		solicitacao.setStatus("EM PROCESSO");
		
		dao.persiste(solicitacao);
		
		return mapping.findForward("dashBoardAdmin");
	}
}
