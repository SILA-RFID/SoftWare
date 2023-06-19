package com.portaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.portaria.bean.Permissao;

public class PermissaoDao extends Permissao{
	
	public List<Permissao> listar(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Permissao> permissoes = new ArrayList<Permissao>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC permissao.listarPermissoes @usuario=?, @cracha=?, @leitora=?, @status=?, @diaSemana=?");
			smt.setString(1, String.valueOf(param.get("usuario") == null ? 0 : param.get("usuario")));
			smt.setString(2, String.valueOf(param.get("cracha") == null ? 0 : param.get("cracha")));
			smt.setString(3, String.valueOf(param.get("leitora") == null ? 0 : param.get("leitora")));
			smt.setString(4, String.valueOf(param.get("status") == null ? "" : param.get("status")));
			smt.setString(5, String.valueOf(param.get("diaSemana") == null ? "" : param.get("diaSemana")));
			
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Permissao u = new Permissao();
				u.setId(rs.getInt("id"));
				u.setUsuario(rs.getString("usuario"));
				u.setCracha(rs.getString("cracha"));
				u.setLeitora(rs.getString("leitora"));
				u.setStatus(rs.getString("status"));
				u.setData(rs.getString("data"));
				u.setDiasSemana(rs.getString("diasSemana"));
				
				permissoes.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return permissoes;
	}
	
	public Integer criarPermissao(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC permissao.criarPermissao @usuario=?, @cracha=?, @leitora=?, @diaSemana=?");
			smt.setString(1, String.valueOf(param.get("usuario")));
			smt.setString(2, String.valueOf(param.get("cracha")));
			smt.setString(3, String.valueOf(param.get("leitora")));
			smt.setString(4, String.valueOf(param.get("diaSemana")));
			
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
	
	public Integer alterarPermissao(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC permissao.alterarPermissao @id=?");
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
	
	public Integer salvarEdicaoPermissao(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC permissao.editarPermissao @diaSemana=?, @id=?");
			smt.setString(1, String.valueOf(param.get("diasSemana")));
			smt.setString(2, String.valueOf(param.get("id")));
			
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
	
	public List<Permissao> listarDias(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Permissao> permissoes = new ArrayList<Permissao>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC permissao.diasPermissaoId @id=?");
			smt.setString(1, String.valueOf(param.get("id")));
			
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Permissao u = new Permissao();
				u.setDiasSemana(rs.getString("dias"));
				
				permissoes.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return permissoes;
	}
}
