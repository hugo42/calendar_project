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
		<div class="modal fade" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="buyModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="buyModalLabel">Acheter</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="radio-inline">
								<input type="radio" id="dictionSelect"> Ajouter dicton
							</label>
		         			<label class="radio-inline">
								<input type="radio" id="pictureSelect"> Ajouter image            
		           			</label>
		         		</div>
		
						<form id="dictionForm" class="hide">
		         			<div class="form-group">
		           				<label for="dicton" class="control-label">Dicton:</label>
		           				<textarea class="form-control" id="dicton"></textarea>
		         			</div>
		       			</form>
		
						<form id="pictureForm" class="hide">
		  					<div class="form-group">
			    				<label for="picture" class="controle-label">Image:</label>
			    				<input type="file" id="picture">
			    				<p class="help-block">La taille d'image ne doit pas dépasser 50ko...</p>
			  				</div>
						</form>	
		      		</div>
		    	</div>
		  	</div>
		</div>
	</jsp:body>
</t:layout>