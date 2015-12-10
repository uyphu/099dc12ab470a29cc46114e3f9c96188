'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.18+', {
                url: '/18+',
                templateUrl: 'scripts/app/18+/18+.html',
                controller: '18+Controller',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('18+');
                        return $translate.refresh();
                    }]
                    
                }
            });
    });


