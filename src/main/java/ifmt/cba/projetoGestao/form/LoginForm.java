package ifmt.cba.projetoGestao.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	
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
	
	
	
	
}
