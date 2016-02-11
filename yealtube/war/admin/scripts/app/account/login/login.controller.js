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
	                    //$state.go('home');
	           	   		$state.go('dashboard.home');
	                } else {
	                    $rootScope.back();
	                }
           	   	}
            });
        };
        
        //Login with google
        $scope.loginGoogle = function (event) {
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
	                    //$state.go('home');
	           	   		$state.go('dashboard.home');
	                } else {
	                    $rootScope.back();
	                }
           	   	}
            });
        };
        
        //Login with Facebook
        $scope.loginFacebook = function (event) {
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
	                    //$state.go('home');
	           	   		$state.go('dashboard.home');
	                } else {
	                    $rootScope.back();
	                }
           	   	}
            });
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
		
    });
