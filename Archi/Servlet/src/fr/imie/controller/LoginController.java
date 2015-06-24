package fr.imie.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.ITransverse.IFactory;
import fr.imie.ITransverse.IService;
import fr.imie.dto.UsagerDTO;

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
		
		IFactory factory = (IFactory) request.getSession().getServletContext().getAttribute("factory");
		IService service = (IService) factory.getService() ;
		
		usagerDTO.setLogin(login);
		usagerDTO.setPassword(password);
		
		UsagerDTO connectedUsager = null;
		try{
			connectedUsager = service.getAuthentifiedUsager(usagerDTO);
		}
		catch(SQLException e){
			request.getSession().setAttribute("erreur", e);
		}
		if(connectedUsager!=null){
			request.getSession().setAttribute("connectedUser", connectedUsager);
			//Pas de redirection, on laisse le filter continuer le traitement après le doChain
			//afin d'aller sur la page demandée avec le log
		}	
	}

}
