<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="fr.eni.ecole.trocenchere.gestion.erreurs.LecteurMessage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/Style.css" type="text/css" />
<title>Connected Home</title>

</head>

<body>
	<header>
		<%@include file="HeaderConnected.jspf" %>
	</header>
	
	<c:if test="${!empty listeCodesErreur}">
			<div class="displayLine" role="alert">
			 <!--   <strong class="messageError">Erreur!</strong> -->
			  <br>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li class="messageError">${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
	</c:if>
	
	<body class="body">
		<div class="welcome">
		<c:out value="${sessionScope.user}" />
		est connecté.
		</div>
	
		<h1 class="title">Liste des enchères</h1>
			
			<div class="checkBoxes">
				<div class="container">
				
					<div class="lookFor">
						<label class="lookForLabel">Filtre : </label>
						<input class="inputSelect" type="text" name="articleContains" placeholder="Le nom de l'article contient..." />
					
						<label class="lookForLabel">Catégories : </label> 
						<select name="Categories" id="category">
							<option value="Toutes">${initParam.ALL}</option>
							<option value="Informatique">${initParam.COMPUTER}</option>
							<option value="Ameublement">${initParam.FURNISHINGS}</option>
							<option value="Vêtement">${initParam.CLOTHING}</option>
							<option value="SportLoisirs">${initParam.SPORT}</option>
						</select> 
					</div>
				
					<div class="container2">
						<div>
							<ul>
								<label class="selectBoxTitle">Achats</label>
								<li><input type="checkbox" name="openBids" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">enchères ouvertes</label>
								</li>
								<li><input type="checkbox" name="myBids" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">mes enchères</label>
								</li>
								<li><input type="checkbox" name="myWonBids" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">mes enchères remportées</label>
								</li>
							</ul>
					
						</div>
						<div>
							<ul>
								<label class="selectBoxTitle">Mes ventes</label>
								<li><input type="checkbox" name="currentSales" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">ventes en cours</label>
								</li>
								<li><input type="checkbox" name="notStartedSales" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">ventes non débutées</label>
								</li>
								<li><input type="checkbox" name="endedSales" class="checkBox" value="false" /> 
									<label class="selectBoxLabel">ventes terminées</label>
								</li>
							</ul>
						</div>
					</div>
				</div>
					<input class="searchButton" type="submit" name="search" value="Rechercher"/>
				</div>
			
	
	
	
			
	
			<div class="ArticlesDisplay"></div>

	</body>

	<footer class="footer" id="footerCell">
			<%@include file="Footer.jspf" %>
	</footer>

</body>
</html>