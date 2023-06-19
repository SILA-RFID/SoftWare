package com.portaria.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.portaria.dao.CrachaDao;
import com.portaria.dao.UsuarioDao;
import com.portaria.util.HttpService;

@WebServlet(name = "Dados do cracha", urlPatterns = {"/cracha", "/crachaListar", "/criarCracha", "/salvarCracha", "/alterarCracha"})
public class CrachaServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getRequestURI().indexOf("cracha") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/cracha/viewCracha.jsp");
			} else if(req.getRequestURI().indexOf("criarCracha") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/cracha/criarCracha.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().indexOf("crachaListar") > 0) {
			try { 
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				requisicaoDadosPost(req, resp, HttpService.HTTP_POST, dadosJson, "/jsp/cracha/listarCracha.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarCracha") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarCracha(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("alterarCracha") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				editarCracha(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} 
	}
	
	private void requisicaoDadosGet(HttpServletRequest req, HttpServletResponse resp, String method, String json, String URL) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new UsuarioDao().listar(new HttpService().requestToRegistro(req, false)));
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void requisicaoDadosPost(HttpServletRequest req, HttpServletResponse resp, String method, String json, String URL) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new CrachaDao().listar(new HttpService().requestToRegistro(req, false)));
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void salvarCracha(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new  CrachaDao().criarCracha(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void editarCracha(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new  CrachaDao().alterarCracha(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private static final long serialVersionUID = 1L;
}
