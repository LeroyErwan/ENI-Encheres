package fr.eni.ecole.trocenchere.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.trocenchere.bll.ArticleManager;
import fr.eni.ecole.trocenchere.bo.Article;
import fr.eni.ecole.trocenchere.gestion.erreurs.BusinessException;
import fr.eni.ecole.trocenchere.utils.ServletUtils;

@WebServlet("/Home")
public class ServletNonConnectedHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// getting the object CATEGORIES so they're available in the servlet
		String categoriesString = this.getServletContext().getInitParameter("CATEGORIES");
		List<String> categories = Arrays.asList(categoriesString.split(","));

		this.getServletContext().setAttribute("categories", categories);
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check: if user is connected, dispatch to ConnectedHome
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("user");
		//System.out.println("Session of " + userName);
		if (userName != null) {
			this.getServletContext().getRequestDispatcher("/Connected/Home").forward(request, response);
		} else {

			// displaying the NonConnectedHome, with the object CATEGORIES

		// displaying articles (default: no keyword, category Toutes)
		String categorySelected = "";
		String keyWord = "";
		ArticleManager am = new ArticleManager();

		List<Article> articlesSelected = null;
		try {
			articlesSelected = am.displayArticles(keyWord, categorySelected, request);
		} catch (BusinessException e) {
			ServletUtils.handleBusinessException(e, request);
		}
			
			request.getServletContext().setAttribute("articlesSelected", articlesSelected);

			this.getServletContext().getRequestDispatcher("/WEB-INF/NonConnectedHome.jsp").forward(request, response);
		}
}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getting the parameters : category selected by user
		String categorySelected = request.getParameter("categories");
		ArticleManager am = new ArticleManager();

		// getting the keyword typed by user
		String keyWord = null;

		keyWord = request.getParameter("keyWord");
		//System.out.println(keyWord);
		

		// displaying articles
		List<Article> articlesSelected = null;
		try {
			articlesSelected = am.displayArticles(keyWord, categorySelected, request);
		} catch (BusinessException e) {
			ServletUtils.handleBusinessException(e, request);
		}

		request.getServletContext().setAttribute("articlesSelected", articlesSelected);

		this.getServletContext().getRequestDispatcher("/WEB-INF/NonConnectedHome.jsp").forward(request, response);

	}

}
