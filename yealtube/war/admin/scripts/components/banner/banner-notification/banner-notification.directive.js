'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('bannerNotification',function(){
		return {
        templateUrl:'scripts/components/banner/banner-notification/banner-notification.html',
        restrict: 'E',
        replace: true
    	}
	});


