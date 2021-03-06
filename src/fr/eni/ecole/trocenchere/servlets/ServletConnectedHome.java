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

/**
 * Servlet implementation class ServletConnectedHome
 */
@WebServlet("/Connected/Home")
public class ServletConnectedHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		// getting the object CATEGORIES so they're available in the servlet
		String categoriesString = this.getServletContext().getInitParameter("CATEGORIES");
		List<String> categories = Arrays.asList(categoriesString.split(","));

		this.getServletContext().setAttribute("categories", categories);
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			this.getServletContext().getRequestDispatcher("/WEB-INF/ConnectedHome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String foo = null;
		foo = request.getParameter("foo");
		if (foo != null) {
			foo = null;
			this.doGet(request, response);
		} else {

			// getting the parameters : category selected by user
			String categorySelected = request.getParameter("categories");

			// getting the keyword typed by user
			String keyWord = null;

			ArticleManager am = new ArticleManager();

			keyWord = request.getParameter("keyWord");
			//System.out.println(keyWord);

			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("user");

			String buyOrSell = request.getParameter("buyOrSell");
			String checkBox = "";
			if (buyOrSell.equalsIgnoreCase("buy1")) {
				checkBox = request.getParameter("buy2");
			} else {
				checkBox = request.getParameter("sell2");
			}

			// displaying selected articles
			List<Article> articlesSelected = null;
			try {
				articlesSelected = am.displayArticlesConnected(userName, keyWord, categorySelected, buyOrSell, checkBox,
						request);
			} catch (BusinessException e) {
				ServletUtils.handleBusinessException(e, request);
			}

			request.getServletContext().setAttribute("articlesSelected", articlesSelected);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ConnectedHome.jsp").forward(request, response);

		}
	}

}
