package ifmt.cba.projetoGestao.model;

import java.util.Date;

public class Solicitacao {
	public enum Status {
		ABERTA,
		EM_ANDAMENTO,
		FINALIZADA
	}
	
	private int id;
	private String titulo;
	private String descricao;
	private Date data_criacao;
	private Status status;
	private Usuario solicitante;
	private Departamento departamento_responsavel;
	
	//getters e setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getData_criacao() {
		return data_criacao;
	}
	
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Usuario getSolicitante() {
		return solicitante;
	}
	
	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}
	
	public Departamento getDepartamento_responsavel() {
		return departamento_responsavel;
	}
	
	public void setDepartamento_responsavel(Departamento departamento_responsavel) {
		this.departamento_responsavel = departamento_responsavel;
	}
	
	
}
