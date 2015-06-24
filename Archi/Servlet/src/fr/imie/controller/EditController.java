package fr.imie.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class edit
 */
@WebServlet("/edit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("row")!=null){
			Integer row = Integer.valueOf((String)request.getParameter("row"));
			
			@SuppressWarnings("unchecked")
			List<UsagerDTO> usagersDTO = (List<UsagerDTO>) request.getSession().getAttribute("usagersDTO");
			UsagerDTO usagerDTO = usagersDTO.get(row);
			
			request.setAttribute("userToUpdate", usagerDTO);
			request.setAttribute("row", row);
		}
		else {
			request.setAttribute("userToUpdate", null);
		}
		
		request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("apply")!=null){
			
			UsagerDTO usagerToUpdate = new UsagerDTO();
			IFactory factory = (IFactory) request.getSession().getServletContext().getAttribute("factory");
			IService service = (IService) factory.getService() ;
			
			usagerToUpdate.setNom(request.getParameter("nom"));
			usagerToUpdate.setPrenom(request.getParameter("prenom"));
			
			String mail= null;
			if(request.getParameter("mail")!=null && request.getParameter("mail").compareTo("")!=0) {
				mail = request.getParameter("mail");
			}
			usagerToUpdate.setEmail(mail);
			
			Date dOB = null;
			if(request.getParameter("dOB")!=null && request.getParameter("dOB").compareTo("")!=0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dOB = sdf.parse(request.getParameter("dOB"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			usagerToUpdate.setDateNaiss(dOB);
			
			usagerToUpdate.setLogin(request.getParameter("loginEdit"));
			usagerToUpdate.setPassword(request.getParameter("passwordEdit"));
			
			if(request.getParameter("row")!=null){
				
				Integer row = Integer.parseInt(request.getParameter("row"));
				
				@SuppressWarnings("unchecked")
				List<UsagerDTO> usagersDTO = (List<UsagerDTO>) request.getSession().getAttribute("usagersDTO");
				UsagerDTO usagerDTO = usagersDTO.get(row);
				usagerToUpdate.setId(usagerDTO.getId());
				try{
					service.update(usagerToUpdate);
					request.getSession().setAttribute("notification","Mise à jour effectuée");
				}
				catch(SQLException e){
					request.getSession().setAttribute("erreur", e);
				}
			}
			else {
				try{
					service.ajouter(usagerToUpdate);
					request.getSession().setAttribute("notification","Création effectuée");
				}
				catch(SQLException e){
				request.getSession().setAttribute("erreur", e);
				}
			}
		}
		response.sendRedirect("usagers");
	}

}
