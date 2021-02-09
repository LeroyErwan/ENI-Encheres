package fr.eni.ecole.trocenchere.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.bll.ArticleManager;
import fr.eni.ecole.trocenchere.bll.UserManager;
import fr.eni.ecole.trocenchere.bo.User;
import fr.eni.ecole.trocenchere.gestion.erreurs.BusinessException;
import fr.eni.ecole.trocenchere.gestion.erreurs.CodesResultatServlets;
import fr.eni.ecole.trocenchere.utils.ServletUtils;

/**
 * Servlet implementation class ServletSellArticle
 */
@WebServlet("/Connected/SellArticle")
public class ServletSellArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("doget - servlet sellArticle");

		String profileName = request.getParameter("profile");

		System.out.println("je passe dans la ServletSellArticle - doGet / profilename : " + profileName);

		// User - link to data base
		UserManager userManager = new UserManager();
		User profile = null;

		try {
			profile = userManager.selectUser(profileName);
		} catch (BusinessException e) {
			ServletUtils.handleBusinessException(e, request);
		}

		request.getServletContext().setAttribute("profile", profile);
		// System.out.println("Ville du profile : " + profile.getCity());

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/SellArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean hasError = false;
		
		System.out.println("dopost - servlet sellArticle");

		String profileName = request.getParameter("profile");
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		List<Integer> listeCodesErreur = new ArrayList<>();

		// Get parameters
		String articleName = request.getParameter("name");
		String articleDesc = request.getParameter("description");
		String articleCat = request.getParameter("categories");
		Integer saleStartBid = Integer.parseInt(request.getParameter("startBid"));
		String saleStartDate = request.getParameter("startDate");
		String saleEndDate = request.getParameter("endDate");
		String pickUpStreet = request.getParameter("street");
		String pickUpPostCode = request.getParameter("postCode");
		String pickUpCity = request.getParameter("city");
		
		//Error category
		if ("Toutes".equals(articleCat)) {
			listeCodesErreur.add(CodesResultatServlets.CATEGORY_MISSING_ERROR);
			hasError=true;
		}

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("user");
		User profile=null;
		UserManager um = new UserManager();
		
		try {
			profile = um.selectUser(userName);
		}
		catch (BusinessException e) {
			ServletUtils.handleBusinessException(e, request);
			hasError=true;
		}
				
		//get session data
		int idSeller = profile.getIdUser();

		//format dates
		try {
			saleStartDate = saleStartDate.replace("T", " ");
			saleEndDate = saleEndDate.replace("T", " ");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			startDate = LocalDateTime.parse(saleStartDate, dtf);
			endDate = LocalDateTime.parse(saleEndDate, dtf);
			System.out.println("Start date : " + startDate + " / End date : " + endDate); //debug display
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATETIME_ERROR);
			hasError=true;
		}

		// create Article
		try {
			ArticleManager am = new ArticleManager();
			am.sellArticle(idSeller, articleName, articleDesc, articleCat, saleStartBid, startDate, endDate,
					pickUpStreet, pickUpPostCode, pickUpCity);
		} catch (BusinessException e) {
			ServletUtils.handleBusinessException(e, request);
			System.out.println("erreur lors de la saisie du formulaire");
			e.printStackTrace();
			hasError=true;
		}
		
		if (hasError) {
			request.getServletContext().setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else {
			String message = "Votre vente a bien été ajoutée";
			request.getServletContext().setAttribute("message", message);
		}
		
		this.doGet(request, response);
	}
}
