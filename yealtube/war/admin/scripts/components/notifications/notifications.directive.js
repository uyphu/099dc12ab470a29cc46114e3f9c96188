'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('notifications',function(){
		return {
        templateUrl:'scripts/components/notifications/notifications.html',
        restrict: 'E',
        replace: true,
    	}
	});


