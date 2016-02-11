'use strict';

angular.module('jhipsterApp')
    .controller('SiteController', function ($scope, $state, $window, Auth, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.$state = $state;

            $scope.logout = function () {
                Auth.logout();
                $state.go('home');
            };
            
        });
    });
