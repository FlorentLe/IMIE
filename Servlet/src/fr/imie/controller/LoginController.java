package fr.imie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.model.UsagerDAO;
import fr.imie.model.UsagerDTO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		UsagerDTO usagerDTO = new UsagerDTO();
		UsagerDAO usagerDAO = new UsagerDAO();
		
		usagerDTO.setLogin(login);
		usagerDTO.setPassword(password);
		
		UsagerDTO connectedUsager = usagerDAO.getAuthentifiedUsager(usagerDTO);
		if(connectedUsager!=null){
			request.getSession().setAttribute("connectedUser", connectedUsager);
			//Pas de redirection, on laisse le filter continuer le traitement après le doChain
			//afin d'aller sur la page demandée avec le log
		}	
	}

}
