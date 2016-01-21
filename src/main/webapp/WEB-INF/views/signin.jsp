<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div class="col-md-4">
				<form id="form-signin" action="signin" method="post">
				<div class="form-group">
						<p id="errors">
							${errors}
						</p>
					</div>
				  <div class="form-group">
				    <label for="email">Email address</label>
				    <input type="email" class="form-control" id="email" name="email" placeholder="email@user.com" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
				  </div>
				  <button type="submit" id="validate" class="btn btn-primary">Se connecter</button>
				  <a href="signup" class="btn btn-success">Créer un compte</a>
				</form>			
			</div>
		</div>
	</jsp:body>
</t:layout>