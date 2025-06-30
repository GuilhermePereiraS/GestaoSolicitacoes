package ifmt.cba.projetoGestao.action.usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.LoginForm;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.util.CriptografiaUtil;

public class AutenticaUsuarioAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		LoginForm formPreechido = (LoginForm) form;
		List<Usuario> ListaUsuario = dao.lista("Usuario");
		Usuario usuarioLogado = null;
		String senhaCriptografada = CriptografiaUtil.criptografa(formPreechido.getSenha());
		
		
		
		for (Usuario u : ListaUsuario) {
			if (u.getLogin().equals(formPreechido.getLogin())) {
				//desativa uma flag usuario não encontrado
				for (Usuario u1 : ListaUsuario) {
					if (u1.getSenha().equals(senhaCriptografada)) {
						//desativa a flag senha não encontrada
						usuarioLogado = u1;
					} else {
						//ativa alguma flag
					}
				}
			} else {
				
			}
		}
		
		if (usuarioLogado != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuarioLogado);
		} else {
			return mapping.findForward("paginaInicial");
		}
		
		return mapping.findForward("carregaDashboard");
	}
}
