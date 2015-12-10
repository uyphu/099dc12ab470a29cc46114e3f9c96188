'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('error', {
                parent: 'site',
                url: '/error',
                data: {
                    roles: [],
                    pageTitle: 'errors.title'
                },
                templateUrl: 'scripts/app/error/error.html',
//                views: {
//                    'content@': {
//                        templateUrl: 'scripts/app/error/error.html'
//                    }
//                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('error');
                        return $translate.refresh();
                    }]
                }
            })
            .state('accessdenied', {
                parent: 'site',
                url: '/accessdenied',
                data: {
                    roles: []
                },
                templateUrl: 'scripts/app/error/accessdenied.html',
//                views: {
//                    'content@': {
//                        templateUrl: 'scripts/app/error/accessdenied.html'
//                    }
//                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('error');
                        return $translate.refresh();
                    }]
                }
            });
    });
