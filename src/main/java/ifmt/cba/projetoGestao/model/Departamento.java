package ifmt.cba.projetoGestao.model;

public class Departamento {
	private int id;
	private String nome;
	private Usuario responsavel;
	
	
	
	//getters e setters
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
