package com.jmrbdev.videoclub.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmrbdev.videoclub.dao.UsuarioDAO;

/**
 * Servlet implementation class ServletUsuarioModificar
 */
@WebServlet("/ServletUsuarioModificar")
public class ServletUsuarioModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUsuarioModificar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDAO usuario = new UsuarioDAO();

		String usuarioStr = usuario.modificar(request.getParameter("username"), request.getParameter("contrasena"),
				request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("email"),
				Double.parseDouble(request.getParameter("saldo")),
				Boolean.parseBoolean(request.getParameter("premium")));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(usuarioStr);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
