package com.portaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.portaria.bean.Leitor;

public class LeitorDao extends Leitor{
	
	public List<Leitor> listar(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Leitor> leitores = new ArrayList<Leitor>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC leitor.listarLeitores @status=?, @id=?");
			smt.setString(1, String.valueOf(param.get("status") == null ? "" : param.get("status")));
			smt.setString(2, String.valueOf(param.get("id") == null ? 0 : param.get("id")));
			
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Leitor u = new Leitor();
				u.setId(rs.getInt("id"));
				u.setIp(rs.getString("ip"));
				u.setDescricao(rs.getString("descricao"));
				u.setStatus(rs.getString("status"));
				
				leitores.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return leitores;
	}
	
	public Integer criarLeitor(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC leitor.criarLeitor @descricao=?, @ip=?");
			smt.setString(1, String.valueOf(param.get("descricao")));
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
	
	public Integer alterarLeitor(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC leitor.alterarLeitor @id=?");
			smt.setString(1, String.valueOf(param.get("id")));
			
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
	
	public Integer salvarEdicaoLeitor(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC leitor.editarLeitor @descricao=?, @ip=?, @id=?");
			smt.setString(1, String.valueOf(param.get("descricao")));
			smt.setString(2, String.valueOf(param.get("ip")));
			smt.setString(3, String.valueOf(param.get("id")));
			
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
