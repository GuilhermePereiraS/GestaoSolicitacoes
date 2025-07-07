package ifmt.cba.projetoGestao.action.departamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Usuario;

public class AtualizaDepartamentoAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			Dao dao = new Dao();
			Departamento departamento = (Departamento) dao.buscaPorId("Departamento", Integer.parseInt(request.getParameter("id")));
			Usuario usuarioResponsavel = request.getParameter("usuarioResponsavelId").equals("----") ? null : (Usuario) dao.buscaPorId("Usuario", Integer.parseInt(request.getParameter("usuarioResponsavelId")));
			
			departamento.setNome(request.getParameter("nome"));
			
			if (usuarioResponsavel != null) {
				departamento.setResponsavel(usuarioResponsavel);
			}
			
			dao.edita(departamento);
			
			
			return mapping.findForward("dashboard");
	}
}
