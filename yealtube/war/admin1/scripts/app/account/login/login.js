'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
        .state('login',{
        	parent: 'site',
            templateUrl:'scripts/app/account/login/login.html',
            url:'/login'
        });
    });
