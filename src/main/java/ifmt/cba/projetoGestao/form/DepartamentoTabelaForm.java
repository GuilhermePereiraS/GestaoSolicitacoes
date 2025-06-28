package ifmt.cba.projetoGestao.form;

import org.apache.struts.action.ActionForm;

import ifmt.cba.projetoGestao.model.Usuario;

public class DepartamentoTabelaForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private Usuario responsavel;
	
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
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	

	
	
	
	
}
