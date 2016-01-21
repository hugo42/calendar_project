<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="title">
		Accueil
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Accueil
	</jsp:attribute>
	<jsp:body>
		Pour commencer, veuillez vous connecter.
		<a href="signin" class="btn btn-default">Connexion</a>
		<a href="signup" class="btn btn-success">Inscription</a>
	</jsp:body>
</t:layout>