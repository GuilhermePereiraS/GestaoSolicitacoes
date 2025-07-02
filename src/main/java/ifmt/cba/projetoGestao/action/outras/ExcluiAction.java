package ifmt.cba.projetoGestao.action.outras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;

public class ExcluiAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");
		
		Object objeto = dao.buscaPorId(tipo, id);
		
		dao.deleta(objeto);
		
		return mapping.findForward("dashBoardAdmin");
	}
}
