'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.history', {
            	parent: 'dashboard',
                url: '/history',
                templateUrl: 'scripts/app/history/history.html',
                controller: 'HistoryController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('history');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


