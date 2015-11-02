'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('header',function(){
		return {
        templateUrl:'scripts/components/header/header.html',
        restrict: 'E',
        replace: true
    	}
	});

angular.module('jhipsterApp')
.directive('activeMenu', function($translate, $locale, tmhDynamicLocale) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var language = attrs.activeMenu;

            scope.$watch(function() {
                return $translate.use();
            }, function(selectedLanguage) {
                if (language === selectedLanguage) {
                    tmhDynamicLocale.set(language);
                    element.addClass('active');
                } else {
                    element.removeClass('active');
                }
            });
        }
    };
})
.directive('activeLink', function(location) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var clazz = attrs.activeLink;
            var path = attrs.href;
            path = path.substring(1); //hack because path does bot return including hashbang
            scope.location = location;
            scope.$watch('location.path()', function(newPath) {
                if (path === newPath) {
                    element.addClass(clazz);
                } else {
                    element.removeClass(clazz);
                }
            });
        }
    };
});


