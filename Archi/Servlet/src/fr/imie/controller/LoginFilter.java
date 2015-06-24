package fr.imie.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.dto.UsagerDTO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httpRequest.getRequestURI();
		Boolean securedRessource = true;
		UsagerDTO connectedUser = (UsagerDTO) httpRequest.getSession().getAttribute("connectedUser");
		
		if(httpRequest.getQueryString()!=null){
			url = url.concat("?").concat(httpRequest.getQueryString());
		}
		if(url.contains("login") || url.contains("css") || url.contains("png")){
			securedRessource = false;
		}
		
		if(!securedRessource || connectedUser !=null){
			chain.doFilter(request, response);
			
		}
		else {
			httpRequest.getSession().setAttribute("askedRessource", url);
			httpResponse.sendRedirect("login");
		}
		
		UsagerDTO recentConnectedUsager = (UsagerDTO) httpRequest.getSession().getAttribute("connectedUser");
		if(connectedUser==null && recentConnectedUsager!=null){
			
			String askedRessource = (String) httpRequest.getSession().getAttribute("askedRessource");
			if (askedRessource!=null && !askedRessource.contains("login")&& !askedRessource.contains("edit")&& !askedRessource.contains("delete")){
				httpResponse.sendRedirect(askedRessource);
			}
			else{
				httpResponse.sendRedirect("home");
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
