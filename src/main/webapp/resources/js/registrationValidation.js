$(document).ready(function() {

    /*
     * Register new user validation
     */

    $(".registrationForm").validate(
        {
            rules: {
                name: {
                    required: true,
                    minlength: 1,
                    /*remote: {
                        url: window.location.protocol + "/easy-reporting/register/available.html",
                        type: "get",
                        data: {
                            username: function () {
                                return $("#name").val();
                            }
                        }
                    }*/
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
                    remote: "Podana nazwa użytkownika już istnieje"
                }
            }

        }
    )
});


