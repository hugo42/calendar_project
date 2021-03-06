
class Signup

	constructor: ()->
		@w = $('form#form-signup')
		@bind()

	bind: ()=>
		$("#email", @w).on 'input', (e)=>
			if $(e.currentTarget).val().length > 4
				@checkEmail()

		@w.submit @submit 

	checkEmail: ()=>

		$('#errors', @w).text ""
		$.ajax
			url: "check-email"
			method: "POST"
			data:
				"email" : $('#email', @w).val()
			statusCode:
				200: (data)=>
					$('#errors', @w).text "Cette adresse mail est déjà utilisée..."

	checkPasswordConfirm: ()=>
		$('#errors', @w).text ""
		res = true
		if $('#password', @w).val() != $('#passwordConfirm', @w).val()
			$('#errors', @w).text "La confirmation du mot de passe n'est pas correcte..."
			res = false
		return res


	submit: (e)=>
		if !@checkPasswordConfirm()
			e.preventDefault()

class Signin

	constructor: ()->
		@w = $('form#form-signin')
		@bind()

	bind: ()=>
		$("#email", @w).stop().on 'input', (e)=>
			if $(e.currentTarget).val().length > 4
				@checkEmail()

	checkEmail: ()=>

		$('#errors', @w).text ""
		$.ajax
			url: "check-email"
			method: "POST"
			data:
				"email" : $('#email', @w).val()
			statusCode:
				404: (data)=>
					$('#errors', @w).text "Cette adresse mail ne correspond à aucun utilisateur..."
				200: ()=>
					$('#errors', @w).text ""

$ ->

	if $('form#form-signup').length > 0
		new Signup

	if $('form#form-signin').length > 0
		new Signin