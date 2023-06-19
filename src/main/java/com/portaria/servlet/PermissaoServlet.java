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

import com.portaria.bean.Cracha;
import com.portaria.dao.CrachaDao;
import com.portaria.dao.LeitorDao;
import com.portaria.dao.PermissaoDao;
import com.portaria.dao.UsuarioDao;
import com.portaria.util.HttpService;

@WebServlet(name = "Dados do permissao", urlPatterns = {"/permissao", "/permissaoListar", 
												"/criarPermissao", "/salvarPermissao", "/alterarPermissao",
												"/editarPermissao", "/salvarEdicaoPermissao"})
public class PermissaoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getRequestURI().indexOf("/permissao") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/permissao/viewPermissao.jsp");
			} else if(req.getRequestURI().indexOf("criarPermissao") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/permissao/criarPermissao.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().indexOf("permissaoListar") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				requisicaoDadosPost(req, resp, HttpService.HTTP_POST, dadosJson, "/jsp/permissao/listarPermissao.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarPermissao") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarPermissao(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("alterarPermissao") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				editarPermissao(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("editarPermissao") > 0) {
			try {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGetDias(req, resp, HttpService.HTTP_POST, "", "/jsp/permissao/editarPermissao.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarEdicaoPermissao") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarEdicaoPermissao(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void requisicaoDadosGet(HttpServletRequest req, HttpServletResponse resp, String method, String json, String URL) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("USER", new UsuarioDao().listar(new HttpService().requestToRegistro(req, false))); 
		retorno.put("LET", new LeitorDao().listar(new HttpService().requestToRegistro(req, false))); 
		retorno.put("CRA", new UsuarioDao().listarCracha(new HttpService().requestToRegistro(req, false))); 
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void requisicaoDadosPost(HttpServletRequest req, HttpServletResponse resp, String method, String json, String URL) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new PermissaoDao().listar(new HttpService().requestToRegistro(req, false)));
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void salvarPermissao(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new PermissaoDao().criarPermissao(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void editarPermissao(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new  PermissaoDao().alterarPermissao(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void salvarEdicaoPermissao(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new PermissaoDao().salvarEdicaoPermissao(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void requisicaoDadosGetDias(HttpServletRequest req, HttpServletResponse resp, String method, String json, String URL) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new PermissaoDao().listarDias(new HttpService().requestToRegistro(req, false))); 
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private static final long serialVersionUID = 1L;
}
