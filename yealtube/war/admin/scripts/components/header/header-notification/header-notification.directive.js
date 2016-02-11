'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('headerNotification',function(){
		return {
        templateUrl:'scripts/components/header/header-notification/header-notification.html',
        restrict: 'E',
        replace: true
    	}
	});


