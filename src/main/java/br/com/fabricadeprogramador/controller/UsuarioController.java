package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	System.out.println("Entrou");
}
 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuarioDoController = new Usuario();
		usuarioDoController.setNome(nome);
		usuarioDoController.setLogin(login);
		usuarioDoController.setSenha(senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		usuDAO.salvar(usuarioDoController);
		resp.getWriter().println("Salvo Com sucesso");
	}
 
 @Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
 
 
}
