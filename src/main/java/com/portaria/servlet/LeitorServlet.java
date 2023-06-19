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
import com.portaria.dao.LeitorDao;
import com.portaria.dao.UsuarioDao;
import com.portaria.util.HttpService;

@WebServlet(name = "Dados do leitor", urlPatterns = {"/leitor", "/leitorListar", "/criarLeitor", "/salvarLeitor", 
														"/alterarLeitor", "/editarLeitor", "/salvarEdicaoLeitor"})
public class LeitorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getRequestURI().indexOf("/leitor") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/leitor/viewLeitor.jsp");
			} else if(req.getRequestURI().indexOf("criarLeitor") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/leitor/criarLeitor.jsp");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().indexOf("leitorListar") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				requisicaoDadosPost(req, resp, HttpService.HTTP_POST, dadosJson, "/jsp/leitor/listarLeitor.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarLeitor") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarLeitor(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("alterarLeitor") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				editarLeitor(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("editarLeitor") > 0) {
			try {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_POST, "", "/jsp/leitor/editarLeitor.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarEdicaoLeitor") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarEdicaoLeitor(req, resp, HttpService.HTTP_POST, dadosJson);
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
		retorno.put("DATA", new LeitorDao().listar(new HttpService().requestToRegistro(req, false)));
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void salvarLeitor(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new LeitorDao().criarLeitor(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void editarLeitor(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new  LeitorDao().alterarLeitor(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void salvarEdicaoLeitor(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new LeitorDao().salvarEdicaoLeitor(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private static final long serialVersionUID = 1L;
}
