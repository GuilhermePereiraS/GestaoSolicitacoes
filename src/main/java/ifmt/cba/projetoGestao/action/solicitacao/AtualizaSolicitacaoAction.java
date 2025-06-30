package ifmt.cba.projetoGestao.action.solicitacao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;

public class AtualizaSolicitacaoAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Solicitacao solicitacao = new Solicitacao();
		Dao dao = new Dao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		solicitacao.setId(Integer.parseInt(request.getParameter("id")));
		solicitacao.setTitulo(request.getParameter("titulo"));
		solicitacao.setDescricao(request.getParameter("descricao"));
		solicitacao.setData_criacao(sdf.parse(request.getParameter("dataCriacao")));
		solicitacao.setStatus(request.getParameter("status"));
		
		int solicitanteId = Integer.parseInt(request.getParameter("solicitanteId"));
		int DepartamentoId = Integer.parseInt(request.getParameter("departamentoResponsavelId"));
		
		List<Usuario> listaU = dao.lista("Usuario");
		List<Departamento> listaD = dao.lista("Departamento");
		
		for (Usuario u : listaU) {
			if (u.getId() == solicitanteId) {
				solicitacao.setSolicitante(u);
			}
		}
		
		for (Departamento d : listaD) {
			if (d.getId() == DepartamentoId) {
				solicitacao.setDepartamento_responsavel(d);
			}
		}
		
		dao.edita(solicitacao);
		
		return mapping.findForward("dashBoard");
	}
}
