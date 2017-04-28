package br.com.fabricadeprogramador;

import java.awt.List;
import java.util.ArrayList;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {
	
	public static void main(String[] args){
		testBuscaTodos();
	}
	private static void testBuscaTodos() {
		// TODO Auto-generated method stub
		UsuarioDAO usuDAO = new UsuarioDAO();
		ArrayList<Usuario> lista = usuDAO.buscaTodos();
		for(Usuario u: lista){
			System.out.println(u);
		}
		
	}
	
	private static void testBuscaPorId() {
		// TODO Auto-generated method stub
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuario = usuDAO.buscaPorId(1);
		
		System.out.println(usuario);
	}


	public static void testCadastrar(){
		//criando o usuario
		Usuario usu = new Usuario();
		usu.setNome("HenriqueTeste1");
		usu.setLogin("teste1");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso");
		
	}
	
	public static void testAlterar(){
		//criando o usuario
		Usuario usu = new Usuario();
		usu.setId(2);
		usu.setNome("FernandaAlterada");
		usu.setLogin("teste1");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso");
		
	}
	
	public static void testExcluir(){
		//criando o usuario
		Usuario usu = new Usuario();
		usu.setId(3);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso");
		
	}
	
	public static void testSalvar(){
		Usuario usu = new Usuario();
		//usu.setId(2);
		usu.setNome("Katiane");
		usu.setLogin("kat");
		usu.setSenha("123");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.salvar(usu);
		
		System.out.println("Salvo com sucesso");
	}
	
	
}
