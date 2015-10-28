'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('jhipsterApp')
	.directive('timeline',function() {
    return {
        templateUrl:'scripts/components/timeline/timeline.html',
        restrict: 'E',
        replace: true,
    }
  });
