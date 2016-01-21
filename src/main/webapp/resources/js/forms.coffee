
class Signup

	constructor: ()->
		@w = $('form#form-signup')
		@init()

	init: ()=>
		$("#email", @w).on 'input', (e)=>
			if $(e.currentTarget).val().length > 4
				@checkEmail()

		@w.submit @submit 

	checkEmail: ()=>

		check = false
		$.ajax
			url: "signup/check-email"
			success: (data)=>
				check = true
				$('#errors', @w).text data
			error: ()=>
				$('#errors', @w).text "Cette adresse mail est déjà utilisée..."

	submit: (e)=>
		e.preventDefault()
		if @checkEmail()
			@w.submit()

$ ->

	if $('form#form-signup').length > 0
		new Signup