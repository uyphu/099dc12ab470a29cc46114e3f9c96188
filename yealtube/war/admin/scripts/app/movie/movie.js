'use strict';

angular.module('jhipsterApp').config(
		function($stateProvider) {
			$stateProvider.state('dashboard.movie', {
				parent : 'dashboard',
				url : '/movie',
				templateUrl : 'scripts/app/movie/movie.html',
				controller : 'MovieController',
				data : {
					roles : ['ROLE_USER']
				},
				resolve : {
					mainTranslatePartialLoader : [ '$translate',
							'$translatePartialLoader',
							function($translate, $translatePartialLoader) {
								$translatePartialLoader.addPart('movie');
								return $translate.refresh();
							} ]
				}
			});
		});


