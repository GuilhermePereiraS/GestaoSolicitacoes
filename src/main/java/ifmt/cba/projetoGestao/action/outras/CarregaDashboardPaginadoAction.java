package ifmt.cba.projetoGestao.action.outras;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;

public class CarregaDashboardPaginadoAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		int itemsPorPagina = 5;
		
		if (usuarioLogado.getPerfil().equals("ADMIN")) {
			
			int paginaAtualUsuario = Integer.parseInt(request.getParameter("paginaUsuario"));
			int paginaAtualSolicitacao = Integer.parseInt(request.getParameter("paginaSolicitacao"));
			int paginaAtualDepartamento = Integer.parseInt(request.getParameter("paginaDepartamento"));
			
			
			int totalPaginasUsuario = (int) Math.ceil((double) dao.contarTotal("Usuario"));
			int totalPaginasSolicitacao = (int) Math.ceil((double) dao.contarTotal("Solicitacao"));
			int totalPaginasDepartamento = (int) Math.ceil((double) dao.contarTotal("Departamento"));
			
			request.setAttribute("totalPaginasUsuario", totalPaginasUsuario);
			request.setAttribute("paginaAtualUsuario", paginaAtualUsuario);
			
			request.setAttribute("totalPaginasSolicitacao", totalPaginasSolicitacao);
			request.setAttribute("paginaAtualSolicitacao", paginaAtualSolicitacao);
			
			request.setAttribute("totalPaginasDepartamento", totalPaginasDepartamento);
			request.setAttribute("paginaAtualDepartamento", paginaAtualDepartamento);
			
			
			
			List<Usuario> usuarios = dao.listaPaginada("Usuario", itemsPorPagina, paginaAtualUsuario);
			List<Departamento> departamentos = dao.listaPaginada("Departamento",itemsPorPagina, paginaAtualDepartamento  );
			List <Solicitacao> solicitacoes = dao.listaPaginada("Solicitacao", itemsPorPagina, paginaAtualSolicitacao);
			List<Usuario> usuariosAdmin = new ArrayList(usuarios);
			
			usuariosAdmin.removeIf(u -> u.getPerfil().equals("PADRÃO"));
			
			
			request.setAttribute("usuariosAdmin", usuariosAdmin);
			request.setAttribute("solicitacoes", solicitacoes);
			request.setAttribute("departamentos", departamentos);
			request.setAttribute("usuarios",usuarios);
			return mapping.findForward("dashboardAdmin");
		} else {
			int paginaAtualSolicitacao = Integer.parseInt(request.getParameter("paginaSolicitacao"));
			int totalPaginasSolicitacao = (int) Math.ceil((double) dao.contarTotal("Solicitacao"));
			
			List<Departamento> departamentos = dao.lista("Departamento");
			
			List <Solicitacao> solicitacoes = dao.listaPaginada("Solicitacao", itemsPorPagina, paginaAtualSolicitacao);
			request.setAttribute("departamentos", departamentos);
			return mapping.findForward("dashboardPadrao");
		}
	}
}
