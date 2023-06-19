package com.portaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portaria.bean.Usuario;

public class UsuarioDao extends Usuario{
	
	public List<Usuario> listar(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC usuario.listarUsuarios @id=?, @status=?");
			smt.setString(1, String.valueOf(param.get("userId") == null ? 0 : param.get("userId")));
			smt.setString(2, String.valueOf(param.get("status") == null ? "" : param.get("status")));
			
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("user_id"));
				u.setNome(rs.getString("user_nome"));
				u.setEmail(rs.getString("user_email"));
				u.setTelefone(rs.getString("user_telefone"));
				u.setMatricula(rs.getString("user_matricula"));
				u.setDataNascimento(rs.getString("user_dataNascimento"));
				u.setDataNascimentoF(rs.getString("dataNascimentoF"));
				u.setIdCracha(rs.getInt("user_craId"));
				u.setStatus(rs.getString("user_status"));
				u.setObservacao(rs.getString("observacao"));
				
				usuarios.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return usuarios;
	}
	
	public Integer criarUsuario(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC usuario.criarUsuario @nome=?, @email=?, @telefone=?, @data=?, @matricula=?, @cracha=?, @observacao=?");
			smt.setString(1, String.valueOf(param.get("nome")));
			smt.setString(2, String.valueOf(param.get("email")));
			smt.setString(3, String.valueOf(param.get("telefone")));
			smt.setString(4, String.valueOf(param.get("data")));
			smt.setString(5, String.valueOf(param.get("matricula")));
			smt.setString(6, String.valueOf(param.get("cracha")));
			smt.setString(7, String.valueOf(param.get("observacao")));
			
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
	
	public List<Usuario> listarCracha(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement smt = con.prepareStatement("EXEC listarCrachasUsuarios @id=?");
			smt.setString(1, String.valueOf(param.get("cra_id") == null ? 0 : param.get("cra_id")));
			
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				
				usuarios.add(u);
			}
			
			rs.close();
			smt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
		
		return usuarios;
	}
	
	public Integer alterarUsuario(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC usuario.alterarUsuario @id=?");
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
	
	public Integer salvarEdicaoUsuario(Map<String, Object> param) throws SQLException, ClassNotFoundException{
		Connection con = new ConnectionFactory().getConnection();
		Integer rs = -1;
		try {
			PreparedStatement smt = con.prepareStatement("EXEC usuario.editarUsuario @nome=?, @email=?, @telefone=?, @data=?, @matricula=?, @cracha=?, @observacao=?, @id=?");
			smt.setString(1, String.valueOf(param.get("nome")));
			smt.setString(2, String.valueOf(param.get("email")));
			smt.setString(3, String.valueOf(param.get("telefone")));
			smt.setString(4, String.valueOf(param.get("data")));
			smt.setString(5, String.valueOf(param.get("matricula")));
			smt.setString(6, String.valueOf(param.get("cracha")));
			smt.setString(7, String.valueOf(param.get("observacao")));
			smt.setString(8, String.valueOf(param.get("id")));
			
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
