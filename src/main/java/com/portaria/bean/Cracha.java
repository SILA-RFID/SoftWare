package com.portaria.bean;

public class Cracha {

	private int id;
	private String numeracao;
	private String data;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeracao() {
		return numeracao;
	}
	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Cracha [id=" + id + ", numeracao=" + numeracao + ", data=" + data + ", status=" + status + "]";
	}
	
	
}
