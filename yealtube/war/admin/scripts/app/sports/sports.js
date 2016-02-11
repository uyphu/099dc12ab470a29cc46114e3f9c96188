'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.sports', {
            	parent: 'dashboard',
                url: '/sports',
                templateUrl: 'scripts/app/sports/sports.html',
                controller: 'SportsController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('sports');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


