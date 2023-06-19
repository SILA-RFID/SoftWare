package com.portaria.bean;

public class Permissao {

	private int id;
	private String usuario;
	private String cracha;
	private String leitora;
	private String status;
	private String data;
	private String diasSemana;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCracha() {
		return cracha;
	}
	public void setCracha(String cracha) {
		this.cracha = cracha;
	}
	public String getLeitora() {
		return leitora;
	}
	public void setLeitora(String leitora) {
		this.leitora = leitora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}
	@Override
	public String toString() {
		return "Permissao [id=" + id + ", usuario=" + usuario + ", cracha=" + cracha + ", leitora=" + leitora
				+ ", status=" + status + ", data=" + data + ", diasSemana=" + diasSemana + "]";
	}
}
