'use strict';

angular.module('jhipsterApp').config(
		function($stateProvider) {
			$stateProvider.state('dashboard.home', {
				parent : 'dashboard',
				url : '/',
				templateUrl : 'scripts/app/home/home.html',
				controller : 'HomeController',
				data : {
					roles : ['ROLE_USER']
				},
				resolve : {
					mainTranslatePartialLoader : [ '$translate',
							'$translatePartialLoader',
							function($translate, $translatePartialLoader) {
								$translatePartialLoader.addPart('home');
								$translatePartialLoader.addPart('global');
								return $translate.refresh();
							} ]
				}
			});
		});


