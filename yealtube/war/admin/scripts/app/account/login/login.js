'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
        .state('login',{
        	parent: 'site',
        	templateUrl:'scripts/app/account/login/login.html',
        	controller: 'LoginController',
            url:'/login',
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('login');
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('language');
                    return $translate.refresh();
                }]
                
            }
        });
    });
