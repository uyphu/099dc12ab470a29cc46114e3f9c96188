'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('site', {
            	'abstract': true,
                templateUrl: 'scripts/app/site/site.html',
                controller: 'SiteController',
                data: {
                    roles: []
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        $translatePartialLoader.addPart('login');
                        $translatePartialLoader.addPart('language');
                        return $translate.refresh();
                    }]
                    
                }
            });
        
    });


