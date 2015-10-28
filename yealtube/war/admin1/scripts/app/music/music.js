'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.music', {
                url: '/music',
                templateUrl: 'scripts/app/music/music.html',
                controller: 'MusicController',
                data: {
                    roles: []
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('music');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


