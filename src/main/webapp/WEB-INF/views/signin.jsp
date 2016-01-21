<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="title">
		Connexion
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Connexion
	</jsp:attribute>
	<jsp:body>
		<div class="row">
			<div class="col-md-10">
				<form action="signin" method="post">
				  <div class="form-group">
				    <label for="email">Email address</label>
				    <input type="email" class="form-control" id="email" name="email" placeholder="email@user.com" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
				  </div>
				  <button type="submit" class="btn btn-primary">Se connecter</button>
				</form>			
			</div>
		</div>
	</jsp:body>
</t:layout>