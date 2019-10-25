$(document).ready(function() {

    //for custom checkboxes
    $('input').not('.noStyle').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

});

//validate login form
function validform(formId) {
    return $(formId).validate({
        ignore: null,
        ignore: 'input[type="hidden"]',
        errorPlacement: function (error, element) {
            wrap = element.parent();
            wrap1 = wrap.parent();
            if (wrap1.hasClass('checkbox')) {
                error.insertAfter(wrap1);
            } else {
                if (element.attr('type') == 'file') {
                    error.insertAfter(element.next());
                } else {
                    error.insertAfter(element);
                }
            }
        },
        errorClass: 'help-block',
        rules: {
            username: "required",
            password1: {
                required: true,
                minlength: 5
            },
            confirm_passowrd:{
                required: true,
                minlength: 5
            }

        },
        messages: {
            password1: {
                required: "请输入密码！！",
                minlength: "密码最少长度为5位数"
            },
            confirm_passowrd: {
                required: "请输入密码！！",
                minlength: "密码最少长度为5位数"
            },
            username: "请输入用户名！！"
        },
        highlight: function (element) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-success').addClass('has-error');
            } else {
                if ($(element).attr('type') == 'file') {
                    $(element).parent().parent().removeClass('has-success').addClass('has-error');
                }
                $(element).offsetParent().parent().parent().parent().removeClass('has-success').addClass('has-error');

            }
        },
        unhighlight: function (element, errorClass) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).offsetParent().parent().hasClass('checkbox')) {
                $(element).offsetParent().parent().parent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).next().hasClass('bootstrap-filestyle')) {
                $(element).parent().parent().removeClass('has-error').addClass('has-success');
            }
            else {
                $(element).offsetParent().parent().parent().removeClass('has-error').addClass('has-success');
            }
        }
    });
}