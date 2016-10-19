$(document).ready(function () {

    /*
     * Register new user validation
     */

    $(".registrationForm").validate(
        {
            rules: {
                name: {
                    required: true,
                    minlength: 1,
                    remote: {
                        url: window.location.protocol + "/register/available.html",
                        type: "get",
                        data: {
                            username: function () {
                                return $("#name").val();
                            }
                        }
                    }
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5
                },

                password_again: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                }

            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            messages: {
                name: {
                    remote: "Podana nazwa użytkownika jest już zajęta",
                    required: "Pole jest wymagane",
                    minlength: "Pole powinno zawierać co najmniej 1 znak"
                },
                email: {
                    required: "Pole jest wymagane",
                    email: "Wpisz poprawny adres mailowy"
                },
                password: {
                    required: "Pole jest wymagane",
                    minlength: "Pole powinno zawierać co najmniej 5 znaków"
                },
                password_again: {
                    required: "Pole jest wymagane",
                    minlength: "Pole powinno zawierać co najmniej 5 znaków",
                    equalTo: "Powtórzone hasło różni się od poprzednio wpisanego hasła"
                }


            }

        }
    )
});


