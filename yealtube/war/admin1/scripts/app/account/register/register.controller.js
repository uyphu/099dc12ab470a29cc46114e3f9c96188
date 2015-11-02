'use strict';

angular.module('jhipsterApp')
    .controller('RegisterController', function ($scope, $translate, $timeout, Auth) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.errorUserAndEmailExists = null;
        $scope.errorAccountExists = null;
        $scope.registerAccount = {};
        $timeout(function (){angular.element('[ng-model="registerAccount.login"]').focus();});

        $scope.register = function () {
            if ($scope.registerAccount.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.registerAccount.langKey = $translate.use();
                $scope.doNotMatch = null;
                $scope.error = null;
                $scope.errorUserExists = null;
                $scope.errorEmailExists = null;
                $scope.errorUserAndEmailExists = null;
                $scope.errorAccountExists = null;
                
                Auth.createAccount($scope.registerAccount).then(function (data) {
                	if (data.error != null) {
                		$scope.success = null;
                		showError(data.message);
					} else {
						$scope.success = 'OK';
					}
                });
            }
        };
        
        function showError(errorMsg) {
        	if (errorMsg != null) {
				if (errorMsg.indexOf('[602]') != -1) {
					//account already exists
					$scope.errorAccountExists = 'ERROR';
				} else if (errorMsg.indexOf('[610]') != -1) {
					//user and email already exists
					$scope.errorUserAndEmailExists = 'ERROR';
				} else if (errorMsg.indexOf('[608]') != -1) {
					//login already in use
					$scope.errorUserExists = 'ERROR';
				} else if (errorMsg.indexOf('[609]') != -1) {
					//e-mail address already in use
					$scope.errorEmailExists = 'ERROR';
				} else {
					$scope.error = 'ERROR';
				}
			}
        };
    });
