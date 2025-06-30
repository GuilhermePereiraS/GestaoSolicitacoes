package ifmt.cba.projetoGestao.action.solicitacao;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		List<Departamento> listaD = dao.lista("Departamento");
		
		
		
		solicitacao.setId(Integer.parseInt(request.getParameter("id")));
		solicitacao.setData_criacao(sdf.parse(request.getParameter("dataCriacao")));
		solicitacao.setTitulo(request.getParameter("titulo"));
		solicitacao.setStatus(request.getParameter("status")); //mudar pra bombox
		
		
		return mapping.findForward("dashBoardAdmin");
	}
}
