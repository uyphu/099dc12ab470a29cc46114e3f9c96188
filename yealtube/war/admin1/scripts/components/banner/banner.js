'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider.state('banner1', {
            'abstract': true,
            views: {
                'banner@': {
                    templateUrl: 'scripts/components/banner/banner.html',
                    controller: 'NavbarController'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('language');
                }]
            }
        });
    });
