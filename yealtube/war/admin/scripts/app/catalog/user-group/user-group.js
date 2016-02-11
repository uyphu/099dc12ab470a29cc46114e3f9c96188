'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.user-group', {
            	parent: 'dashboard',
                url: '/user-group',
                templateUrl: 'scripts/app/catalog/user-group/user-group.html',
                controller: 'UserGroupController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    	$translatePartialLoader.addPart('userGroup');
                        return $translate.refresh();
                    }]
                    
                }
            })
            .state('userGroupDetail', {
                parent: 'dashboard',
                url: '/user-group/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'yealtube.userGroup.detail.title'
                },
                templateUrl: 'scripts/app/catalog/user-group/user-group-detail.html',
                controller: 'CategoryDetailController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userGroup');
                        return $translate.refresh();
                    }]
                }
            });
    });

