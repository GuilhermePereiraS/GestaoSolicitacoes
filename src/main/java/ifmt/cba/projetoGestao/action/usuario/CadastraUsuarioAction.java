package ifmt.cba.projetoGestao.action.usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.CadastroForm;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.util.CriptografiaUtil;

public class CadastraUsuarioAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		
		if (usuarioNovo.getPerfil().equals("ADMIN")) {
			return mapping.findForward("dashBoardAdmin");
		} else {
			return mapping.findForward("paginaInicial");
		}
	}
}
