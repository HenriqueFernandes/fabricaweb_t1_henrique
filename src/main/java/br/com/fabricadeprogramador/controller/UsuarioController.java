package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
	resp.setContentType("text/html");
	String acao = req.getParameter("acao");
	if(acao.equals("exc")){
		String id = req.getParameter("id");
		Usuario usu= new Usuario();
		if(id != null) usu.setId(Integer.parseInt(id));
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usu);
		resp.sendRedirect("usucontroller.do?acao=lis");
	}else if (acao.equals("lis")){
		//implementar a lista
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<Usuario> lista = usuarioDAO.buscaTodos();
		
		req.setAttribute("lista", lista);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
		dispatcher.forward(req, resp);
	}else if(acao.equals("alt")){
		String id = req.getParameter("id");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorId(Integer.parseInt(id));
		req.setAttribute("usu", usuario);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
		dispatcher.forward(req,resp);
		
	}else if (acao.equals("cad")){
		Usuario usuario = new Usuario();
		usuario.setId(0);
		usuario.setNome("");
		usuario.setLogin("");
		usuario.setSenha("");
		req.setAttribute("usu", usuario);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
		dispatcher.forward(req,resp);
	}
}
 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuarioDoController = new Usuario();
		usuarioDoController.setId(Integer.parseInt(id));
		usuarioDoController.setNome(nome);
		usuarioDoController.setLogin(login);
		usuarioDoController.setSenha(senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		usuDAO.salvar(usuarioDoController);
		resp.getWriter().println("Salvo Com sucesso");
		resp.sendRedirect("usucontroller.do?acao=lis");
	}
 
 @Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
 
 
}
