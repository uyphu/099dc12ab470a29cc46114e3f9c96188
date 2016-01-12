'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard.category', {
            	parent: 'dashboard',
                url: '/category',
                templateUrl: 'scripts/app/catalog/category/category.html',
                controller: 'CategoryController',
                data: {
                    roles: ['ROLE_USER']
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('category');
                        return $translate.refresh();
                    }]
                    
                }
            })
            .state('categoryDetail', {
                parent: 'dashboard',
                url: '/category/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'proconcoappApp.category.detail.title'
                },
                templateUrl: 'scripts/app/catalog/category/category-detail.html',
                controller: 'CategoryDetailController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('category');
                        return $translate.refresh();
                    }]
                }
            });
    });

