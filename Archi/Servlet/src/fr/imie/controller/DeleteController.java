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
 * Servlet implementation class deleteController
 */
@WebServlet("/confirmation")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer row = Integer.valueOf((String)request.getParameter("row"));
		
		@SuppressWarnings("unchecked")
		List<UsagerDTO> usagerDTO = (List<UsagerDTO>) request.getSession().getAttribute("usagersDTO");
		UsagerDTO userToDelete = usagerDTO.get(row);
		
		request.setAttribute("userToDelete", userToDelete);
		request.setAttribute("row", row);
		request.getRequestDispatcher("WEB-INF/delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("apply")!=null){
			
			Integer row = Integer.valueOf((String)request.getParameter("row"));
			
			@SuppressWarnings("unchecked")
			List<UsagerDTO> usagerDTO = (List<UsagerDTO>) request.getSession().getAttribute("usagersDTO");
			UsagerDTO userToDelete = usagerDTO.get(row);
			
			IFactory factory = (IFactory) request.getSession().getServletContext().getAttribute("factory");
			IService service = (IService) factory.getService() ;
			try{
				service.delete(userToDelete);
				request.getSession().setAttribute("notification","Suppression effectuée");
			}
			catch(SQLException e){
				request.getSession().setAttribute("erreur", e);
			}
		}
		response.sendRedirect("usagers");
	}

}
