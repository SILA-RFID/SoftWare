package com.portaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.portaria.bean.Cracha;

public class CrachaDao extends Cracha{
	
	
	public List<Cracha> listar(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Cracha> crachas = new ArrayList<Cracha>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC cracha.listarCrachas @status=?, @id=?");
			smt.setString(1, String.valueOf(param.get("status")));
			smt.setString(2, String.valueOf(param.get("id")));
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Cracha u = new Cracha();
				u.setId(rs.getInt("id"));
				u.setNumeracao(rs.getString("numeracao"));
				u.setData(rs.getString("data"));
				u.setStatus(rs.getString("status"));
				
				crachas.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return crachas;
	}
	
	public Integer criarCracha(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC cracha.criarCracha @numecao=?");
			smt.setString(1, String.valueOf(param.get("numeracao")));
			
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
	
	public Integer alterarCracha(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC cracha.alterarCracha @id=?");
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
}
