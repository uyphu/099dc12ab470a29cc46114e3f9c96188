'use strict';

angular.module('jhipsterApp')
  .controller('CategoryDetailController', function ($scope, $stateParams, Category) {
      $scope.category = {};
      $scope.load = function (id) {
          Category.get(id).then(function(result) {
            $scope.category = result;
          });
      };
      $scope.load($stateParams.id);
  });