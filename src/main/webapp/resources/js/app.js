'use strict';
 
var App = angular.module('myApp',[]);

App.directive('datepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            $(element).datepicker();
            element.bind('blur keyup change', function(){
                var model = attrs.ngModel;
              //  if (model.indexOf(".") > -1) scope[model.replace(/\.[^.]*/g, "")][model.replace(/[^.]*\./, "")] = element.val();
               // else
                scope['dob'] = element.val();
            });
        }
    };
});