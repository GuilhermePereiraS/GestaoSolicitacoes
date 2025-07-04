package ifmt.cba.projetoGestao.action.outras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.util.ErroDeViolacaoConstraint;
import net.sf.hibernate.JDBCException;

public class ExcluiAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");
		
		Object objeto = dao.buscaPorId(tipo, id);
		
		
		
		try {
			dao.deleta(objeto);			
		} catch (ErroDeViolacaoConstraint e) {
			System.out.println("aaa");
			e.getCause();
		}
		
		return mapping.findForward("dashBoardAdmin");
	}
}
