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

import com.portaria.dao.PermissaoDao;
import com.portaria.dao.UsuarioDao;
import com.portaria.util.HttpService;

@WebServlet(name = "Dados do usuário", urlPatterns = {"/usuario", "/usuarioListar", "/criarUsuario", 
												"/salvarUsuario", "/alterarUsuario", "/editarUsuario",
												"/salvarEdicaoUsuario"})
public class UsuarioServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getRequestURI().indexOf("usuario") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/usuario/viewUsuarios.jsp");
			} else if(req.getRequestURI().indexOf("criarUsuario") > 0) {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_GET, "", "/jsp/usuario/criarUsuario.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().indexOf("usuarioListar") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				requisicaoDadosPost(req, resp, HttpService.HTTP_POST, dadosJson, "/jsp/usuario/listarUsuarios.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarUsuario") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarUsuario(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("alterarUsuario") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				editarUsuario(req, resp, HttpService.HTTP_POST, dadosJson);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("editarUsuario") > 0) {
			try {
				req.setAttribute("data", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				requisicaoDadosGet(req, resp, HttpService.HTTP_POST, "", "/jsp/usuario/editarUsuario.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(req.getRequestURI().indexOf("salvarEdicaoUsuario") > 0) {
			try {
				JSONObject retorno = new JSONObject();
				Map<String, Object> registro = new HttpService().requestToRegistro(req, false);
				retorno = new JSONObject(registro);
				String dadosJson = JSONWriter.valueToString(retorno);
				salvarEdicaoUsuario(req, resp, HttpService.HTTP_POST, dadosJson);
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
		retorno.put("DATA", new UsuarioDao().listar(new HttpService().requestToRegistro(req, false)));
		retorno.put("PARAMETROS", new HttpService().requestToRegistro(req, false));
		req.setAttribute("dados", HttpService.parseJSONListStringToHashMap(retorno.toString()));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF" + URL);
	    dispatcher.forward(req, resp);
	}
	
	private void salvarUsuario(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new UsuarioDao().criarUsuario(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void editarUsuario(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new  UsuarioDao().alterarUsuario(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
	private void salvarEdicaoUsuario(HttpServletRequest req, HttpServletResponse resp, String method, String json) throws ServletException, IOException, SQLException, JSONException, ClassNotFoundException{
		JSONObject retorno = new JSONObject();
		retorno.put("DATA", new UsuarioDao().salvarEdicaoUsuario(new HttpService().requestToRegistro(req, false)));		
		
		String jsonValue = retorno.toString();
		resp.getOutputStream().write(jsonValue.getBytes());
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	private static final long serialVersionUID = 1L;
}
