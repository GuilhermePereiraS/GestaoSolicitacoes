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
import ifmt.cba.projetoGestao.form.CadastroForm;
import ifmt.cba.projetoGestao.form.LoginForm;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.util.CriptografiaUtil;

public class UsuarioAction extends DispatchAction {
	
	public ActionForward cadastra(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		Usuario usuarioNovo = new Usuario();
		CadastroForm formPreenchido = (CadastroForm) form;
		
		usuarioNovo.setNome(formPreenchido.getNome());
		usuarioNovo.setLogin(formPreenchido.getLogin());
		usuarioNovo.setSenha(CriptografiaUtil.criptografa(formPreenchido.getSenha()));
		usuarioNovo.setPerfil("PADRÃO");
		//melhorar a criptografia
		dao.persiste(usuarioNovo);
		
		return new ActionForward("usuario.do?action=testaCadastro&loginUsuarioNovo=" + usuarioNovo.getLogin(),true);
	}

	public ActionForward atualiza(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		Usuario usuario = (Usuario) dao.buscaPorId("Usuario", Integer.parseInt(request.getParameter("id")));
		
		usuario.setLogin(request.getParameter("login"));
		usuario.setPerfil(request.getParameter("perfil"));
		
		dao.edita(usuario);
		
		
		//atualizausuario(user);
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
		
		if (usuarioLogado.getPerfil().equals("ADMIN")) {
			dao.deleta(objeto);		
			return mapping.findForward("dashboard");	
		} else {
			return mapping.findForward("dashboard");	
		}
	}
	
	public ActionForward autentica(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		CadastroForm formPreechido = (CadastroForm) form;
		List<Usuario> ListaUsuario = dao.lista("Usuario");
		Usuario usuarioLogado = null;
		String senhaCriptografada = CriptografiaUtil.criptografa(formPreechido.getSenha());
		
		Boolean loginNaoEncontrado = true;
		Boolean senhaIncorreta = true;
		
		
		for (Usuario u : ListaUsuario) {
			if (u.getLogin().equals(formPreechido.getLogin())) {
				loginNaoEncontrado = false;
				request.setAttribute("senhaIncorreta", senhaIncorreta);
				if (u.getSenha().equals(senhaCriptografada)) {
					senhaIncorreta = false;
					usuarioLogado = u;
					break;
				} 
			} 
		}
		
		request.setAttribute("loginNaoEncontrado", loginNaoEncontrado);
		
		if (usuarioLogado != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuarioLogado);
		} else {
			return mapping.findForward("paginaInicial");
		}
		
		return mapping.findForward("dashboard");
	}
	
	public ActionForward testaCadastro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
