'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
        .state('register',{
        	parent: 'site',
            templateUrl:'scripts/app/account/register/register.html',
            controller: 'RegisterController',
            url:'/register',
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('register');
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('language');
                    return $translate.refresh();
                }]
                
            }
        });
    });
