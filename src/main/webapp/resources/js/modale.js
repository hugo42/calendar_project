$(document).ready(function() {
  $('#buyModal').on('shown.bs.modal', function (event) {
    var clicker = $(event.relatedTarget);
    var idDay = clicker.data("day-id");
    var modal = $(this);
    modal.find('.modal-title').text('Acheter le jour ' + idDay);
  });
	
  $('#choiceForm').on('change', function(event){
	var value = $(this).val();
	if( value === "dicton"){
	  $("#dictionForm").removeClass("hide");
	  if($("#pictureForm").not("hide")){
		$("#pictureForm").addClass("hide");
	  };
	};
	if( value === "image"){
	  $("#pictureForm").removeClass("hide");
	  if($("#dictionForm").not("hide")){
		$("#dictionForm").addClass("hide");
	  }
	};
   });
});