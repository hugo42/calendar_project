<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout>
	<jsp:attribute name="title">
		Accueil
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Accueil
	</jsp:attribute>
	<jsp:body>
	
		<c:if test="${empty guest}">
			<p>Pour commencer, veuillez vous connecter.</p>
			<a href="signin" class="btn btn-default">Connexion</a>
			<a href="signup" class="btn btn-success">Inscription</a>
		</c:if>
		
		<c:if test="${not empty guest}">
			<p>Allons jeter un oeil au calendrier !</p>
			<a href="main" class="btn btn-success">Calendrier</a>
		</c:if>
		<img src="uploads/train.jpg" />
				
	</jsp:body>
</t:layout>