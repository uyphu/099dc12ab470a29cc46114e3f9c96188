'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.video', {
            	parent: 'dashboard',
                url: '/video',
                templateUrl: 'scripts/app/catalog/video-detail/video.html',
                controller: 'VideoController',
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
            .state('dashboard.videoDetail', {
                parent: 'dashboard',
                url: '/video/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'proconcoappApp.tube.detail.title'
                },
                templateUrl: 'scripts/app/catalog/video-detail/video-detail.html',
                controller: 'VideoDetailController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('video');
                        return $translate.refresh();
                    }]
                }
            });
    });

