package com.portaria.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

public class HttpService {
	public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT  = "PUT";
    public static final String HTTP_GET  = "GET";
	public static final String DATA_JSON = "application/json";
	public static final String DATA_XML  = "application/xml";
	public static final String HTTP_HEADER_AUTH = "Authorization";
	public static final String HTTP_HEADER_BEARER = "Bearer";
	
	public static Map<String, Object> requestToRegistro(HttpServletRequest request, boolean isToLog) {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			Enumeration<?> param = request.getParameterNames();
			while (param.hasMoreElements()) {
				String name = (String) param.nextElement();
				String[] values = request.getParameterValues(name);

				// se eh lista de objetos
				if ( name.contains("[") ) {
					name = name.substring(0, name.indexOf("["));
				}
				
				if (values.length > 1) {					
					ret.put(name, values);
					if ( isToLog ) {
						System.out.print("\n::" + name + "::");
						for( int i = 0 ; i < values.length; i++ ) 
							System.out.print(String.format(" %s | ",values[i]));
					}
					
				} else {
					ret.put(name, (values[0]).trim());
					if ( isToLog ) System.out.print("\n::" + name + "::" + values[0]);
				}
			}
			if ( isToLog ) System.out.print("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String sendData(String uri, String method, String dataType, String strData) {
		JSONObject jo = new JSONObject();

		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL (uri);
			con = (HttpURLConnection)url.openConnection();
			if ( "PATH".equalsIgnoreCase(method) ) {
				return ""; // sendDataAsPatch(uri, dataType, strData, auth);
			} else {
				con.setRequestMethod(method);
			}			
			con.setRequestProperty("Content-Type", dataType);
			con.setRequestProperty("Accept", dataType);
			con.setDoOutput(true);

			// envia dados
			if ( strData != null && !strData.isEmpty() ) {
				try(OutputStream os = con.getOutputStream()) {
				    byte[] input = strData.getBytes("utf-8");
				    os.write(input, 0, input.length);			
				}
			}
			
			// identifica canal de retorno conforme codigo de erro
			int code = con.getResponseCode();
			InputStream in = null;
			if ( code >= 400) {
			   in = con.getErrorStream();
			}  
			else {
			   in = con.getInputStream();
			}
			
			// le retorno
			try(BufferedReader br = new BufferedReader(
			  new InputStreamReader(in, "utf-8"))) {
			    StringBuilder response = new StringBuilder();
			    String responseLine = null;
			    while ((responseLine = br.readLine()) != null) {
			        response.append(responseLine.trim());
			    }
			    jo.put(Constantes.COD, con.getResponseCode());
			    jo.put(Constantes.MSG, "SUCCESS" );
			    jo.put("DATA", ( response != null ? response.toString() : "" ));
			} catch (Exception e) {
				//  handle exception
				System.err.println("ERRO DE LEITURA");
				jo.put(Constantes.COD, con.getResponseCode());
			    jo.put(Constantes.MSG, e.getMessage() );
			}
						
			con.disconnect();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("\n::: erro HttpService :::");
			e.printStackTrace();
		    try {
				jo.put(Constantes.COD, con.getResponseCode());
				jo.put(Constantes.MSG, e.getMessage() );
			} catch (IOException e1) {
				jo.put(Constantes.COD, -1);
				jo.put(Constantes.MSG, e1.getMessage() );
				System.err.println("\n::: erro IOEX:::");
				e1.printStackTrace();
			}
		    jo.put(Constantes.MSG, e.getMessage());
		}
		return jo.toString();
	}

	public static HashMap<String, Object> parseJSONListStringToHashMap(String JSON) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> registros = mapper.readValue(JSON, new TypeReference<HashMap<String, Object>>(){});
		return registros;
	}
	

	public static String requestToJson(HttpServletRequest request) {
		JSONObject retorno = new JSONObject();
		try {
			 Map<String, Object> registro = requestToRegistro(request, false);
			 retorno = new JSONObject(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONWriter.valueToString(retorno);
	}


}
