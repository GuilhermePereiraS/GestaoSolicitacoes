package ifmt.cba.projetoGestao.action.usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CadastraUsuarioAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String usuario = "admin";
		if (usuario.equals("admin")) {
			return mapping.findForward("dashBoardAdmin");
		} else {
			
			//verifica se usuario foi cadastrado com sucesso
			System.out.println("usuario cadastrado com sucesso");
			return mapping.findForward("cadastro.jsp");
		}
	}
}
