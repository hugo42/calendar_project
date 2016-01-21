(function() {
  var Signup,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Signup = (function() {
    function Signup() {
      this.submit = __bind(this.submit, this);
      this.checkEmail = __bind(this.checkEmail, this);
      this.init = __bind(this.init, this);
      this.w = $('form#form-signup');
      this.init();
    }

    Signup.prototype.init = function() {
      $("#email", this.w).on('input', (function(_this) {
        return function(e) {
          if ($(e.currentTarget).val().length > 4) {
            return _this.checkEmail();
          }
        };
      })(this));
      return this.w.submit(this.submit);
    };

    Signup.prototype.checkEmail = function() {
      var check;
      check = false;
      return $.ajax({
        url: "signup/check-email",
        success: (function(_this) {
          return function(data) {
            check = true;
            return $('#errors', _this.w).text(data);
          };
        })(this),
        error: (function(_this) {
          return function() {
            return $('#errors', _this.w).text("Cette adresse mail est déjà utilisée...");
          };
        })(this)
      });
    };

    Signup.prototype.submit = function(e) {
      e.preventDefault();
      if (this.checkEmail()) {
        return this.w.submit();
      }
    };

    return Signup;

  })();

  $(function() {
    if ($('form#form-signup').length > 0) {
      return new Signup;
    }
  });

}).call(this);
