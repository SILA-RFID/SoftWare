package com.portaria.bean;

public class Leitor {

	private int id;
	private String ip;
	private String descricao;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Permissao [id=" + id + ", ip=" + ip + ", decricao=" + descricao + ", status=" + status + "]";
	}	
}
