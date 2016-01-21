
class Signup

	constructor: ()->
		@w = $('form#form-signup')
		@bind()

	bind: ()=>
		$("#email", @w).on 'input', (e)=>
			if $(e.currentTarget).val().length > 4
				@checkEmail()

		@w.submit @submit 

	checkEmail: (submit = false)=>

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
		if @checkPasswordConfirm()
			$('#errors', @w).text ""
			$.ajax
				url: "check-email"
				method: "POST"
				data:
					"email" : $('#email', @w).val()
				statusCode:
					403: (data)=>
						$(this).submit()
					200: (data)=>
						e.preventDefault()
						$('#errors', @w).text "Cette adresse mail est déjà utilisée..."
		else
			e.preventDefault()
$ ->

	if $('form#form-signup').length > 0
		new Signup