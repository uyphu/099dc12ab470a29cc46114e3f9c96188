'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('banner',function(){
		return {
        templateUrl:'scripts/components/banner/banner.html',
        restrict: 'E',
        replace: true
    	}
	});



