'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.history', {
                url: '/history',
                templateUrl: 'scripts/app/history/history.html',
                controller: 'HistoryController',
                data: {
                    roles: []
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('history');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


