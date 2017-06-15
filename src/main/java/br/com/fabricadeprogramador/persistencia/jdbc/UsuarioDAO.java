package br.com.fabricadeprogramador.persistencia.jdbc;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		// TODO Auto-generated method stub
		String sql = "insert into usuario (nome,login,senha) values(?,?,?)";
		try {
			//Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.executeUpdate();//executa comando no banco
			preparador.close();//fecha o objeto preparador
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?,login=?,senha=? where id=?";
		try {
			//Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.executeUpdate();//executa comando no banco
			preparador.close();//fecha o objeto preparador
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";
		try {
			//Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			
			preparador.executeQuery();//executa comando no banco
			preparador.close();//fecha o objeto preparador
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void salvar(Usuario usu){
		if(usu.getId() !=null && usu.getId()!=0){
			alterar(usu);
			
		}else{
			cadastrar(usu);
		}
		
	}
/**
 * 
 * @param id
 * @return
 */
	public Usuario buscaPorId(Integer id){
		Usuario usuRetorno = null;
		
		String sql = "select * from usuario where id=?";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			//retorno da consulta.
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()){
				//instancia o objeto Usuario.
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				preparador.close();
				return usuRetorno;
			}
		}catch(SQLException e ){
			throw new RuntimeException();
		}
		return null;
	}
	
	/**
	 * Realiza a busca de todos os elementos 
	 * @return retorna uma lista com os objetos, ou zero
	 */
	public ArrayList<Usuario> buscaTodos(){
		Usuario usuRetorno = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			
			//Return da consulta.
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()){
				//instancia o objeto Usuario.
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				lista.add(usuRetorno);
			}
			
			preparador.close();
			
		}catch(SQLException e ){
			throw new RuntimeException();
		}
		return lista;
	}

	public Usuario autentica(Usuario usu) {
		// TODO Auto-generated method stub
		String sql = "select * from usuario where login=? and senha=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getLogin());
			preparador.setString(2, usu.getSenha());
			
			ResultSet result = preparador.executeQuery();
			if(result.next()){
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt(1));
				usuario.setNome(result.getString("nome"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				return usuario;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
