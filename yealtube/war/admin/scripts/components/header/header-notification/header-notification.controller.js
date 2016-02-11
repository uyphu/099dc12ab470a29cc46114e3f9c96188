'use strict';

angular.module('jhipsterApp')
    .controller('HeaderNotificationController', function ($scope, $state) {
        $scope.languages1 = ['Jani2','Hege2','Kai3'];
        $scope.curState = true;//$state.current.name;
        $scope.isAuthenticated = Principal.isAuthenticated;
        
    });
