class purchaseModal
	
	constructor: ()->
		$('#buyModal').on 'shown.bs.modal', (e)=>
			@w = $("#buyModal .modal-dialog")
			@w.find('.modal-title').text 'Acheter le ' + $('.label', $(e.relatedTarget)).text() + " ?" 
		@init()

	init: ()=>

		$('#choiceForm', @w).on 'change', @hideForms
		@hideForms()

	hideForms: (e)=>
		
		value = $('#choiceForm', @w).val()
		$('#dictionForm, #pictureForm', @w).addClass('hidden')
		if value == "1"
			$('#dictionForm', @w).removeClass('hidden')
		if value == "2"
			$('#pictureForm', @w).removeClass('hidden')

$ ->
	if $('#buyModal').length > 0
		new purchaseModal()