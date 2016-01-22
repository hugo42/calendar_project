<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout>
	<jsp:attribute name="title">
		Erreur
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Erreur
	</jsp:attribute>
	<jsp:body>
		<p>${errors}</p>
		
	</jsp:body>
</t:layout>