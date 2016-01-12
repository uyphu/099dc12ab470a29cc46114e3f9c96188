'use strict';

angular.module('jhipsterApp')
  .controller('UserGroupDetailController', function ($scope, $stateParams, UserGroup) {
      $scope.userGroup = {};
      $scope.load = function (id) {
          UserGroup.get(id).then(function(result) {
            $scope.userGroup = result;
          });
      };
      $scope.load($stateParams.id);
  });