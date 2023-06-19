package com.portaria.bean;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String matricula;
	private String dataNascimento;
	private int idCracha;
	private String status;
	private String observacao;
	private String dataNascimentoF;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getDataNascimentoF() {
		return dataNascimentoF;
	}
	
	public void setDataNascimentoF(String dataNascimentoF) {
		this.dataNascimentoF = dataNascimentoF;
	}
	
	public int getIdCracha() {
		return idCracha;
	}
	
	public void setIdCracha(int idCracha) {
		this.idCracha = idCracha;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", matricula="
				+ matricula + ", dataNascimento=" + dataNascimento + ", idCracha=" + idCracha + "]";
	}
	
	
}
