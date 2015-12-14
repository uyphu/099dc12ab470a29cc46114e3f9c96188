'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.home', {
            	parent: 'dashboard',
                url: '/',
                templateUrl: 'scripts/app/home/home.html',
                controller: 'MainController',
                data: {
                	roles: []
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('home');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                    
                    
                
                }
            });
    });


