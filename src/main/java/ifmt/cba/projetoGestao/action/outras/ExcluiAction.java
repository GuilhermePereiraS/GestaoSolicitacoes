package ifmt.cba.projetoGestao.action.outras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Solicitacao;
import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.util.ErroDeViolacaoConstraint;
import net.sf.hibernate.JDBCException;

public class ExcluiAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Dao dao = new Dao();
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");
		
		Object objeto = dao.buscaPorId(tipo, id);
		
		
		if (tipo.equals("Usuario")) {
			if (usuarioLogado.getPerfil().equals("ADMIN")) {
				dao.deleta(objeto);		
				return mapping.findForward("dashboard");
			} else {
				return mapping.findForward("dashboard");
			}
		}
		
		if (tipo.equals("Solicitacao")) {
			if (usuarioLogado.getPerfil().equals("PADRAO")) {
				Solicitacao solicitacao = (Solicitacao) objeto;	
				if (solicitacao.getSolicitante().getId() == usuarioLogado.getId()) {
					dao.deleta(objeto);	
				}
				return mapping.findForward("dashboard");
			} else if (usuarioLogado.getPerfil().equals("ADMIN")) {
				dao.deleta(objeto);	
				return mapping.findForward("dashboard");
			}
		}
		
		dao.deleta(objeto);	
		
		return mapping.findForward("dashboard");
	}
}
