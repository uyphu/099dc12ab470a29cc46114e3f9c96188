'use strict';

angular.module('jhipsterApp')
    .controller('bannerNotificationController', function ($scope, $state) {
        $scope.curState = true;//$state.current.name;
        $scope.isAuthenticated = Principal.isAuthenticated;
        
    });
