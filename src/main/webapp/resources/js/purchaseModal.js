(function() {
  var purchaseModal,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  purchaseModal = (function() {
    function purchaseModal() {
      this.hideForms = __bind(this.hideForms, this);
      this.init = __bind(this.init, this);
      $('#buyModal').on('shown.bs.modal', (function(_this) {
        return function(e) {
          _this.w = $("#buyModal .modal-dialog");
          $('.day-input', _this.w).val($(e.relatedTarget).data('day-id'));
          return _this.w.find('.modal-title').text('Acheter le ' + $('.label', $(e.relatedTarget)).text() + " ?");
        };
      })(this));
      this.init();
    }

    purchaseModal.prototype.init = function() {
      $('#choiceForm', this.w).on('change', this.hideForms);
      return this.hideForms();
    };

    purchaseModal.prototype.hideForms = function(e) {
      var value;
      value = $('#choiceForm', this.w).val();
      $('#dictionForm, #pictureForm', this.w).addClass('hidden');
      if (value === "1") {
        $('#dictionForm', this.w).removeClass('hidden');
      }
      if (value === "2") {
        return $('#pictureForm', this.w).removeClass('hidden');
      }
    };

    return purchaseModal;

  })();

  $(function() {
    if ($('#buyModal').length > 0) {
      return new purchaseModal();
    }
  });

}).call(this);
