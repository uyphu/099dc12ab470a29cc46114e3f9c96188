'use strict';

angular.module('jhipsterApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, $window, Auth, usSpinnerService) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = true;
        $scope.activatedError = false;
        $scope.authenticationError = false;
        $scope.passwordError = false;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function (event) {
        	$scope.startSpin();
            event.preventDefault();
            
            $scope.activatedError = false;
            $scope.authenticationError = false;
            $scope.passwordError = false;
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function (data) {
            	$scope.stopSpin();
                if (data.error != null) {
                	showError(data.message);
           	   	} else {
	           	   	$scope.activatedError = false;
	                $scope.authenticationError = false;
	                $scope.passwordError = false;
	           	   	if ($rootScope.previousStateName === 'register') {
	                    $state.go('home');
	                } else {
	                    $rootScope.back();
	                }
           	   	}
            });
        };
        
        $window.init = function() {
		  	$scope.$apply($scope.initgapi);
		};
		
		$scope.initgapi = function() {
			if (!AppConstant.USER_PROFILE_ENDPOINT_LOADED) {
				Auth.init().then(function(){
					if (AppConstant.USER_PROFILE_ENDPOINT_LOADED) {
						//getCustomersSummary('customersSummary',vm.currentPage - 1,vm.pageSize);
						console.log("userprofileendpoint loaded...")
					}
				},
				function(){
					console.log(ErrorCode.ERROR_INIT_ENDPOINT_SERVICE);
				});
			}
			
		};
		
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
		
		function showError(errorMsg) {
 		   	if (errorMsg.indexOf('[613]') != -1) {
 		   		$scope.activatedError = true;
 		   	} else if (errorMsg.indexOf('[614]') != -1) {
 		   		$scope.passwordError = true;
 		   	} else {
 		   		$scope.authenticationError = true;
 		   	}
        };
        
        $scope.startSpin = function() {
     	   	if (!$scope.spinneractive) {
     		   usSpinnerService.spin('spinner-1');
     		   //$scope.startcounter++;
     	   	}
        };

 	   	$scope.stopSpin = function() {
 		   if ($scope.spinneractive) {
 			   usSpinnerService.stop('spinner-1');
 		   }
 	   	};

 	   	$rootScope.$on('us-spinner:spin', function(event, key) {
 	   		$scope.spinneractive = true;
 	   	});

 	   	$rootScope.$on('us-spinner:stop', function(event, key) {
 		   	$scope.spinneractive = false;
 	   	});
		
		loadData();
    });
