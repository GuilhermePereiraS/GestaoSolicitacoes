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
import ifmt.cba.projetoGestao.model.Usuario.Perfil;
import ifmt.cba.projetoGestao.util.CriptografiaUtil;
import ifmt.cba.projetoGestao.util.ErroDeViolacaoConstraint;

public class UsuarioAction extends DispatchAction {
	
	public ActionForward cadastra(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		Usuario usuarioNovo = new Usuario();
		CadastroForm formPreenchido = (CadastroForm) form;
		
		usuarioNovo.setNome(formPreenchido.getNome());
		usuarioNovo.setLogin(formPreenchido.getLogin());
		usuarioNovo.setSenha(CriptografiaUtil.criptografa(formPreenchido.getSenha()));
		usuarioNovo.setPerfil(Perfil.PADRAO);
		//melhorar a criptografia
		dao.persiste(usuarioNovo);
		
		return new ActionForward("/usuario.do?action=testaCadastro&loginUsuarioNovo=" + usuarioNovo.getLogin(),true);
	}

	public ActionForward atualiza(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		Usuario usuario = (Usuario) dao.buscaPorId("Usuario", Integer.parseInt(request.getParameter("id")));
		
		usuario.setLogin(request.getParameter("login"));
		usuario.setPerfil(request.getParameter("perfil").equals("ADMIN") ? Perfil.ADMIN : Perfil.PADRAO);
		
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
		
		if (usuarioLogado.getPerfil() == Perfil.ADMIN) {
			try {
				dao.deleta(objeto);		
			} catch (ErroDeViolacaoConstraint e) {
				return new ActionForward("/navegacao.do?action=dashboard&usuarioComEntidadesVinculadas=true");
			}
			return mapping.findForward("dashboard");	
		} else {
			return mapping.findForward("dashboard");	
		}
	}
	
	public ActionForward autentica(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		CadastroForm formPreechido = (CadastroForm) form;
		List<Usuario> ListaUsuario = dao.lista("Usuario");
		Usuario usuarioLogado = null;
		String senhaCriptografada = CriptografiaUtil.criptografa(formPreechido.getSenha());
		
		Boolean loginNaoEncontrado = true;
		Boolean senhaIncorreta = true;
		
		if (session.getAttribute("usuarioLogado") != null ) {
			return new ActionForward("/navegacao.do?action=dashboard&usuarioJaLogado=true");
		}
		
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
		
		return mapping.findForward("paginaInicialTeste");
	}
	
	public ActionForward sair(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("usuarioLogado");
		
		return mapping.findForward("paginaInicial");
	}
}
