<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<form id="form-signup" method="post" action="signup">
					<div class="form-group">
						<p id="errors">
							${errors}
						</p>
					</div>
				  <div class="form-group">
					<label for="name">Pseudo</label>
				    <input type="text" class="form-control" id="name" name="name" placeholder="Pseudo..." required>
				  </div>
				  <div class="form-group">
				    <label for="email">Email address</label>
				    <input type="email" class="form-control" id="email" name="email" placeholder="email@user.com" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
				  </div>
				  <div class="form-group">
				    <label for="password">Confirm password</label>
				    <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Password confirmation" required>
				  </div>
				  <button type="submit" id="validate" class="btn btn-primary">Créer compte</button>
				</form>			
			</div>
		</div>
	</jsp:body>
</t:layout>