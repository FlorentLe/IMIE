package fr.imie.controller;

import fr.imie.ITransverse.IFactory;
import fr.imie.ITransverse.IService;
import fr.imie.dto.UsagerDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class listController
 */
@WebServlet("/usagers")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * On récupère la liste des usagers	
		 */
		IFactory factory = (IFactory) request.getSession().getServletContext().getAttribute("factory");
		IService service = (IService) factory.getService() ;
		
		List<UsagerDTO> usagersDTO = null;
		try{
			usagersDTO = service.readAllUsager();
		}
		catch(SQLException e){
			request.getSession().setAttribute("erreur", e);
		}
		
		/*
		 * On stocke la liste en attribut de request
		 */
		request.getSession().setAttribute("usagersDTO",usagersDTO );
		
		/*
		 * Redirection vers la vue qui se charge de l'affichage
		 */
		request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer row = null;
		if((request.getParameter("delete")!=null)||(request.getParameter("update")!=null)){
			row = Integer.valueOf((String)request.getParameter("row"));
		}
		if(request.getParameter("delete")!=null){
			response.sendRedirect(String.format("confirmation?row=%d",row));
		}
		else if (request.getParameter("update")!=null){
			response.sendRedirect(String.format("edit?row=%d",row));
		}
		else{
			response.sendRedirect("edit");
		}
	}
}
