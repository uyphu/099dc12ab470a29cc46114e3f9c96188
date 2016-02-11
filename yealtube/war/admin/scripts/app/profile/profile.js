'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('profile', {
                abstract: true,
                parent: 'site'
            });
    });
