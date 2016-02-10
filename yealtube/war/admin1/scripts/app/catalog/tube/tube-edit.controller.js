'use strict';

angular.module('jhipsterApp')
  .controller('TubeEditController', function ($scope, $stateParams, Tube) {
      $scope.tube = {};
      $scope.load = function (id) {
          Tube.get(id).then(function(result) {
            $scope.tube = result;
          });
      };
      $scope.load($stateParams.id);
  });