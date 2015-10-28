'use strict';

angular.module('jhipsterApp')
    .controller('NavbarController', function ($scope, $location, $state, $window, Auth, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };
        
        $window.init = function() {
        	$scope.initgapi();
		}
		
        $scope.initgapi = function() {
			if (!AppConstant.USER_LOGIN_ENDPOINT_LOADED) {
				Auth.init().then(function(){
					console.log('Loaded login endpoint.');
				},
				function(){
					console.log(ErrorCode.ERROR_INIT_ENDPOINT_SERVICE);
				});
			} 
		}
        
        function loadData() {
			if (AppConstant.USER_PROFILE_ENDPOINT_LOADED) {
				//getCustomersSummary('customersSummary',vm.currentPage - 1,vm.pageSize);
			} else {
				if (AppConstant.API_LOAD_TYPE != 0) {
					$scope.initgapi();
				} else {
					AppConstant.API_LOAD_TYPE = 2;
				}
			}
		};
		
		loadData();
    });
