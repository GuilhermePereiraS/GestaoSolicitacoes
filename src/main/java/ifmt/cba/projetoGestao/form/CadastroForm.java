package ifmt.cba.projetoGestao.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CadastroForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String login;
	private String senha;
	private String perfil;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors erros = new ActionErrors();
		if (login == null || login.trim().equals("")) {
			erros.add("login", new ActionMessage("erros.login.obrigatorio"));
		}
		if (senha == null || senha.trim().equals("")) {
			erros.add("senha", new ActionMessage("erros.senha.obrigatorio"));
		}
		return erros;
	}
	
	
	
}
