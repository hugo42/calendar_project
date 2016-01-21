<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout>
	<jsp:attribute name="title">
		Calendrier
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Janvier
	</jsp:attribute>
	<jsp:body>
	
		<ul id="days">
			<c:forEach items="${days}" var="day">
			    <li class="day" data-toggle="modal" data-target="#buyModal" data-day-id="${day.id}">
			    	<p class="label">${day.textDate}</p>
			    </li>
			</c:forEach>
		</ul>
	</jsp:body>
</t:layout>