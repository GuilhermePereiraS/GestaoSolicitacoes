package ifmt.cba.projetoGestao.action.departamento;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.form.DepartamentoTabelaForm;
import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Usuario;

public class AdicionaDepartamentoAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DepartamentoTabelaForm formPreenchido = (DepartamentoTabelaForm) form;
		Departamento departamento = new Departamento();
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		
		
		if (formPreenchido.getNome().equals("")) {
			return new ActionForward("carregaDashboard.do?nomeDepartamentoNaoSelecionado=true",true);
		} else {			
			departamento.setNome(formPreenchido.getNome());
		}
		
		int responsavelFormId = request.getParameter("responsavelId") == null ? -1 : Integer.parseInt(request.getParameter("responsavelId"));
		
		if (responsavelFormId != -1) {
			for (Usuario u : listaU) {
				if (u.getId() == responsavelFormId) {  
					departamento.setResponsavel(u);
				}
			}
		} else {
			return new ActionForward("carregaDashboard.do?responsavelNaoSelecionado=true",true);
		}
		
		dao.persiste(departamento);
		
		departamento.setResponsavel(null);
		
		return mapping.findForward("dashboard");
	}
}