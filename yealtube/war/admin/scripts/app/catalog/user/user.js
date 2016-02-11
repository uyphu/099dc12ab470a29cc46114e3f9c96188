'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.user', {
            	parent: 'dashboard',
                url: '/user',
                templateUrl: 'scripts/app/catalog/user/user.html',
                controller: 'UserController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }]
                    
                }
            })
            .state('userDetail', {
                parent: 'dashboard',
                url: '/user/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'yealtube.user.detail.title'
                },
                templateUrl: 'scripts/app/catalog/user/user-detail.html',
                controller: 'UserDetailController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }]
                }
            });
    });

