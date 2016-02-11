'use strict';

angular.module('jhipsterApp')
  .controller('TubeDetailController', function ($scope, $stateParams, Youtube) {
      $scope.tube = {};
      $scope.load = function (id) {
    	  Youtube.getDetail(id).then(function(result) {
            $scope.tube = result;
          });
      };
      $scope.load($stateParams.id);
  });