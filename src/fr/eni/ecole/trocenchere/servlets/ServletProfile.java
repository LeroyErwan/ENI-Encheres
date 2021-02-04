package fr.eni.ecole.trocenchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.trocenchere.bll.UserManager;
import fr.eni.ecole.trocenchere.bo.User;
import fr.eni.ecole.trocenchere.gestion.erreurs.BusinessException;


@WebServlet("/ServletProfile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Integer> listeCodesErreur = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String userName = request.getParameter("user");
//		
//		// User - link to data base
//		UserManager userManager = new UserManager();
//		User user=null;
//		
//		try {
//			user = userManager.selectUser(userName);
//		}catch (BusinessException e) {
//			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
//			e.printStackTrace();
//		}

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/Profile.jsp");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
