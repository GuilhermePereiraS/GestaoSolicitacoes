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
		/*solicitacao.setStatus("ABERTA"); */
		
		if (request.getParameter("departamentoResponsavel") == null	) {
			return new ActionForward("carregaDashboard.do?formularioComElementosVazios=true",true);
		}
		for (Departamento d : listaD) {
			if (d.getId() == Integer.parseInt(request.getParameter("departamentoResponsavel"))) {
				solicitacao.setDepartamento_responsavel(d);
			}
		}
		
		dao.persiste(solicitacao);
		
		return mapping.findForward("dashboard");
	}
}
