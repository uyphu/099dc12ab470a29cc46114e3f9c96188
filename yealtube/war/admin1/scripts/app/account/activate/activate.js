'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
	        .state('activate',{
	        	parent: 'site',
	            templateUrl:'scripts/app/account/activate/activate.html',
	            controller: 'ActivationController',
	            url:'/activate?key',
	            resolve: {
	                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
	                	$translatePartialLoader.addPart('activate');
	                    $translatePartialLoader.addPart('global');
	                    $translatePartialLoader.addPart('language');
	                    return $translate.refresh();
	                }]
	                
	            }
	        });
    });

