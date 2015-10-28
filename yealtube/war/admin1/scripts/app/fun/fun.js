'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.fun', {
                url: '/fun',
                templateUrl: 'scripts/app/fun/fun.html',
                controller: 'FunController',
                data: {
                    roles: []
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('fun');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


