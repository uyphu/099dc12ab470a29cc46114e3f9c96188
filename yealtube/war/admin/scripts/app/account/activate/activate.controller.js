'use strict';

angular.module('jhipsterApp')
    .controller('ActivationController', function ($scope, $rootScope, $stateParams, $window, Auth) {
    	
    	$scope.activate = function() {
    		Auth.activateAccount({key: $stateParams.key}).then(function (data) {
    			if (data.error != null) {
            		$scope.success = null;
            		$scope.error = 'ERROR';
    			} else {
    				$scope.error = null;
    				$scope.success = 'OK';
    			}
            });
    	}
    	
    	$rootScope.$watch('LOADED', function() {
    		if ($rootScope.LOADED)
    			$scope.activate();
        });
    	
    });

