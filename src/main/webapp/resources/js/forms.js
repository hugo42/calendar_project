(function() {
  var Signin, Signup,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Signup = (function() {
    function Signup() {
      this.submit = __bind(this.submit, this);
      this.checkPasswordConfirm = __bind(this.checkPasswordConfirm, this);
      this.checkEmail = __bind(this.checkEmail, this);
      this.bind = __bind(this.bind, this);
      this.w = $('form#form-signup');
      this.bind();
    }

    Signup.prototype.bind = function() {
      $("#email", this.w).on('input', (function(_this) {
        return function(e) {
          if ($(e.currentTarget).val().length > 4) {
            return _this.checkEmail();
          }
        };
      })(this));
      return this.w.submit(this.submit);
    };

    Signup.prototype.checkEmail = function(submit) {
      if (submit == null) {
        submit = false;
      }
      $('#errors', this.w).text("");
      return $.ajax({
        url: "check-email",
        method: "POST",
        data: {
          "email": $('#email', this.w).val()
        },
        statusCode: {
          200: (function(_this) {
            return function(data) {
              return $('#errors', _this.w).text("Cette adresse mail est déjà utilisée...");
            };
          })(this)
        }
      });
    };

    Signup.prototype.checkPasswordConfirm = function() {
      var res;
      $('#errors', this.w).text("");
      res = true;
      if ($('#password', this.w).val() !== $('#passwordConfirm', this.w).val()) {
        $('#errors', this.w).text("La confirmation du mot de passe n'est pas correcte...");
        res = false;
      }
      return res;
    };

    Signup.prototype.submit = function(e) {
      if (this.checkPasswordConfirm()) {
        $('#errors', this.w).text("");
        return $.ajax({
          url: "check-email",
          method: "POST",
          data: {
            "email": $('#email', this.w).val()
          },
          statusCode: {
            403: (function(_this) {
              return function(data) {
                return $(_this).submit();
              };
            })(this),
            200: (function(_this) {
              return function(data) {
                e.preventDefault();
                return $('#errors', _this.w).text("Cette adresse mail est déjà utilisée...");
              };
            })(this)
          }
        });
      } else {
        return e.preventDefault();
      }
    };

    return Signup;

  })();

  Signin = (function() {
    function Signin() {
      this.checkEmail = __bind(this.checkEmail, this);
      this.bind = __bind(this.bind, this);
      this.w = $('form#form-signin');
      this.bind();
    }

    Signin.prototype.bind = function() {
      return $("#email", this.w).stop().on('input', (function(_this) {
        return function(e) {
          if ($(e.currentTarget).val().length > 4) {
            return _this.checkEmail();
          }
        };
      })(this));
    };

    Signin.prototype.checkEmail = function() {
      $('#errors', this.w).text("");
      return $.ajax({
        url: "check-email",
        method: "POST",
        data: {
          "email": $('#email', this.w).val()
        },
        statusCode: {
          403: (function(_this) {
            return function(data) {
              return $('#errors', _this.w).text("Cette adresse mail ne correspond à aucun utilisateur...");
            };
          })(this),
          200: (function(_this) {
            return function() {
              return $('#errors', _this.w).text("");
            };
          })(this)
        }
      });
    };

    return Signin;

  })();

  $(function() {
    if ($('form#form-signup').length > 0) {
      new Signup;
    }
    if ($('form#form-signin').length > 0) {
      return new Signin;
    }
  });

}).call(this);
