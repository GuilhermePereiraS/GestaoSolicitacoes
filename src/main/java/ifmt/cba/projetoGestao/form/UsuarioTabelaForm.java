package ifmt.cba.projetoGestao.form;

import org.apache.struts.action.ActionForm;

public class UsuarioTabelaForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String senha;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
