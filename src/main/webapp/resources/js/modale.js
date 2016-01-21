$('#buyModal').on('shown.bs.modal', function (event) {
  var clicker = $(event.relatedTarget)
  var idDay = clicker.data("day-id")
  var modal = $(this)
  modal.find('.modal-title').text('Acheter le jour ' + idDay)

  modal.find('#dictionSelect').click(function(){
    if($(this).hasClass("hide")){
      $("#dictionForm").removeClass("hide")
    }
    if($("#pictureForm").not("hide")){
      $("#pictureForm").addClass("hide")
    }
  })

  modal.find("#pictureSelect").click(function() {
    if($(this).hasClass("hide")){
      $("#pictureForm").removeClass("hide")
    }
    if($("#dictionForm").not("hide")){
      $("#dictionForm").addClass("hide")
    }
  });
});