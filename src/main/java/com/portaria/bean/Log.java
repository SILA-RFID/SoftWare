package com.portaria.bean;

public class Log {

	private int leitora;
	private String data;
	private String cracha;
	private String liberado;
	private String motivo;
	public int getLeitora() {
		return leitora;
	}
	public void setLeitora(int leitora) {
		this.leitora = leitora;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCracha() {
		return cracha;
	}
	public void setCracha(String cracha) {
		this.cracha = cracha;
	}
	public String getLiberado() {
		return liberado;
	}
	public void setLiberado(String liberado) {
		this.liberado = liberado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	@Override
	public String toString() {
		return "Log [leitora=" + leitora + ", data=" + data + ", cracha=" + cracha + ", liberado=" + liberado
				+ ", motivo=" + motivo + "]";
	}
}
