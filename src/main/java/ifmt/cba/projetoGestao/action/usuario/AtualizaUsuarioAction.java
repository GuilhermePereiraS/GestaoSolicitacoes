package ifmt.cba.projetoGestao.action.usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.model.Usuario.Perfil;

public class AtualizaUsuarioAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		Usuario usuario = (Usuario) dao.buscaPorId("Usuario", Integer.parseInt(request.getParameter("id")));
		
		usuario.setLogin(request.getParameter("login"));
		usuario.setPerfil(request.getParameter("perfil").equals("PADRAO") ? Perfil.PADRAO : Perfil.ADMIN);
		
		dao.edita(usuario);
		
		
		//atualizausuario(user);
		return mapping.findForward("dashboard");	
	}
}
