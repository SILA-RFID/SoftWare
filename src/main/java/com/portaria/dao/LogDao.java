package com.portaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portaria.bean.Log;
import com.portaria.bean.Usuario;

public class LogDao extends Log{
	
	public List<Log> listar(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Log> logs = new ArrayList<Log>();
		Connection con = new ConnectionFactory().getConnection();
		try {
			PreparedStatement smt = con.prepareStatement("EXEC log.listarLogs @dataInicio=?, @dataFim=?, @leitora=?, @usuario=?, @status=?");
			smt.setString(1, String.valueOf(param.get("dataInicio")));
			smt.setString(2, String.valueOf(param.get("dataFim")));
			smt.setString(3, String.valueOf(param.get("leitora") == null ? 0 : param.get("leitora")));
			smt.setString(4, String.valueOf(param.get("usuario") == null ? 0 : param.get("usuario")));
			smt.setString(5, String.valueOf(param.get("status") == null ? "" : param.get("status")));

			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Log u = new Log();
				u.setLeitora(rs.getInt("leitora"));
				u.setData(rs.getString("data"));
				u.setCracha(rs.getString("cracha"));
				u.setLiberado(rs.getString("liberado"));
				u.setMotivo(rs.getString("motivo"));
				
				logs.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return logs;
	}
	
	public Integer criarLogs(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC log.verificarEntrada @numeracao=?, @ip=?");
			smt.setString(1, String.valueOf(param.get("numeracao")));
			smt.setString(2, String.valueOf(param.get("ip")));
			
			rs = smt.executeUpdate();
			
			if(rs > 0)
				con.commit();
			smt.close();
		}catch (SQLException e) {
			//throw new RuntimeException(e);
			return -2;
		} finally {
			con.close();
		}
		return rs;
	}
}
