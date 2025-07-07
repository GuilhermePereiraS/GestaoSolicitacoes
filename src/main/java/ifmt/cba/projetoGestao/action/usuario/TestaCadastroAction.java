package ifmt.cba.projetoGestao.action.usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Usuario;

public class TestaCadastroAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		String loginUsuarioNovo = request.getParameter("loginUsuarioNovo");
		
		
		if (listaU.stream().anyMatch(u -> u.getLogin().equals(loginUsuarioNovo))) {
			request.setAttribute("usuarioCadastrado", true);
		} else {
			request.setAttribute("usuarioCadastrado", false);
		}
		
		return mapping.findForward("paginaInicial");
	}
}
