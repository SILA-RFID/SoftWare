package com.portaria.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portaria.util.HttpService;

@WebServlet(name = "template servlet")
public class TemplateServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> param = new HttpService().requestToRegistro(req, false);
		Map<String, Object> retorno = new HashMap<>();
		//DAO dao = new DAO(); COLOCAR DAOOO
		
		try {
			//dao.conecta();
			Map<String, Object> processo = buscarDados(param, req.getRequestURI());
			retorno.putAll(processo);
		} catch (Exception e) {
			System.err.println(":: erro :: " + e.getClass().getName());
			System.err.println(":: erro token :: " + e.getMessage());												
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} 
		
		//HttpUtils.flushJSON(resp.getOutputStream(), retorno);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, Object> param = new HttpService().requestToRegistro(req, false);
		Map<String, Object> retorno = new HashMap<>();
		//DAO dao = new DAO();
		try {
			//dao.conecta();
			
			Map<String, Object> processo = processarDados(param, req.getRequestURI());
			retorno.putAll(processo);

		} catch (Exception e) {
			
			System.err.println(":: erro :: " + e.getClass().getName());
			System.err.println(":: erro token :: " + e.getMessage());												
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} 
		//HttpUtils.flushJSON(resp.getOutputStream(), retorno);
	}
	
	public Map<String, Object> buscarDados(Map<String, Object> param, String uri){
		Map<String, Object> ret = new HashMap<>();	
		ret.put("MSG", "METODO GET NAO IMPLEMENTADO");
		return ret ;
	}
	
	public Map<String, Object> processarDados(Map<String, Object> param, String uri){
		Map<String, Object> ret = new HashMap<>();	
		ret.put("MSG", "METODO POST NAO IMPLEMENTADO");
		return ret ;
	}
	
	private static final long serialVersionUID = 1L;
}
