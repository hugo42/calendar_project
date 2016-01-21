<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="title">
		Inscription
	</jsp:attribute>
	<jsp:attribute name="h1Title">
		Inscription
	</jsp:attribute>
	<jsp:body>
		<div class="row">
			<div class="col-md-10">
				<form id="form-signup" action="signup">
					<div class="form-group">
						<p id="errors"></p>
					</div>
				  <div class="form-group">
					<label for="email">Pseudo</label>
				    <input type="text" class="form-control" id="text" placeholder="Enter your pseudo..." required>
				  </div>
				  <div class="form-group">
				    <label for="email">Email address</label>
				    <input type="email" class="form-control" id="email" name="email" placeholder="email@user.com" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" placeholder="Password" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Confirm password</label>
				    <input type="password" class="form-control" id="passwordConfirm" placeholder="Password confirmation" required>
				  </div>
				  <button type="submit" class="btn btn-primary">Créer compte</button>
				</form>			
			</div>
		</div>
	</jsp:body>
</t:layout>