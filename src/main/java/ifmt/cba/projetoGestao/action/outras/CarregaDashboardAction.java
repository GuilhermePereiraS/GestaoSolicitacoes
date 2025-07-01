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
import ifmt.cba.projetoGestao.model.Usuario;

public class CarregaDashboardAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado.getPerfil().equals("ADMIN")) {
			List<Usuario> usuarios = dao.lista("Usuario");
			List<Usuario> usuariosAdmin = usuarios;
			
			usuariosAdmin.removeIf(u -> u.getPerfil().equals("PADRÃO"));
			
			request.setAttribute("usuariosAdmin", usuariosAdmin);
			request.setAttribute("solicitacoes", dao.lista("Solicitacao"));
			request.setAttribute("departamentos", dao.lista("Departamento"));
			request.setAttribute("usuarios",usuarios);
			return mapping.findForward("dashboardAdmin");
		} else {
			request.setAttribute("solicitacoes", dao.lista("Solicitacao"));
			return mapping.findForward("dashboardPadrao");
		}
	}
}
