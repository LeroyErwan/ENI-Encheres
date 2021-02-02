<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ecole.trocenchere.gestion.erreurs.LecteurMessage" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/Style.css" type="text/css" />
<title>Connexion</title>
</head>
<body>
<body>

	<header>
		<c:import url="HeaderNC.html"></c:import>
	</header>

	<c:if test="${!empty listeCodesErreur}">
			<div class="displayLine" role="alert">
			  <strong class="messageError">Erreur!</strong>
			  <br>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li class="messageError">${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
	</c:if>

	<div id="main">

		<div class="mainContent">

			<div class="MainContentTop">

				<form id="idForm" action="${pageContext.request.contextPath}/ServletConnection" method="POST">
					<input type="hidden" name="submitted" value="true" />

					<ul class="connectionParameters">

						<li><label for="userId" class="idLabel">Identifiant</label> <input
							type="text" name="user" class="idField" /> <br></li>

						<li><label for="password" class="idLabel">Mot de
								passe</label> <input type="password" name="password"
							class="passwordField" /><br></li>
					</ul>

					<div class="nav">
						<div>
							<input type="submit" name="connectionButton" value="Connexion"
								class="validationButton" />
						</div>

						<div class="passwordActions">
							<input type="checkbox" name="rememberMe" class="remember"
								value="false" /> <label>Se souvenir de moi</label> <br> <a
								class="link" href="mailto:${param.email}">Mot de passe
								oublié</a>
						</div>
					</div>

				</form>


			</div>

			<div class="MainContentBottom">
				<form action="ConnectedHome.jsp" method="GET">
					<input type="submit" name="createAccountButton"
						value="Créer un compte" class="createAccountButton" />
				</form>
			</div>
		</div>

	</div>

	<footer id="footerCell">

		<c:import url="Footer.html"></c:import>

	</footer>

</body>

</html>