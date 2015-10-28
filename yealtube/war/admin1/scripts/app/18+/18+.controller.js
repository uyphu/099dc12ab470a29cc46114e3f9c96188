'use strict';

angular.module('jhipsterApp')
    .controller('18+Controller', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
    });
