/**
 * Created by Sergey Yaskov on 29.09.2015.
 */
var jviewer = typeof jviewer === 'undefined' ? {} : jviewer;

jviewer.quiz = function (){

    var _$ = function (id) {
        return document.getElementById(id);
    };

    return {

        hasText: function (inputId) {
            var input = _$(inputId);
            if ($.trim(input.value) === '') {
                input.focus();
                return false;
            }
            return true;
        },

        press: function(event, buttonId) {
            if (event.keyCode === 13) {
                $(_$(buttonId)).click();
                return false;
            }
        }

    }

}();