'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.tube', {
            	parent: 'dashboard',
                url: '/tube',
                templateUrl: 'scripts/app/catalog/tube/tube.html',
                controller: 'TubeController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('tube');
                        return $translate.refresh();
                    }]
                    
                }
            })
            .state('tubeDetail', {
                parent: 'dashboard',
                url: '/tube/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'proconcoappApp.tube.detail.title'
                },
                templateUrl: 'scripts/app/catalog/tube/tube-detail.html',
                controller: 'TubeDetailController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tube');
                        return $translate.refresh();
                    }]
                }
            })
            .state('tubeEdit', {
                parent: 'dashboard',
                url: '/tubeedit/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'proconcoappApp.tube.detail.title'
                },
                templateUrl: 'scripts/app/catalog/tube/tube-edit.html',
                controller: 'TubeEditController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tube');
                        return $translate.refresh();
                    }]
                }
            });
    });

