'use strict';

angular.module('jhipsterApp')
    .factory('AuthServerProvider', function loginService($q, localStorageService, $window) {
        return {
            login: function(credentials) {
//                var data = 'j_username=' + encodeURIComponent(credentials.username) +
//                    '&j_password=' + encodeURIComponent(credentials.password) +
//                    '&remember-me=' + credentials.rememberMe + '&submit=Login';
//                return $http.post('api/authentication', data, {
//                    headers: {
//                        'Content-Type': 'application/x-www-form-urlencoded'
//                    }
//                }).success(function (response) {
//                    return response;
//                });
                
    			var p=$q.defer();
    			var requestData = {};
    			requestData.login = credentials.username;
    			requestData.password = credentials.password;
    			gapi.client.userendpoint.login(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
            },
            logout: function() {
                // logout from the server
//                $http.post('api/logout').success(function (response) {
//                    localStorageService.clearAll();
//                    // to get a new csrf token call the api
//                    $http.get('api/account');
//                    return response;
//                });
            	gapi.client.userendpoint.logout().execute(function(resp) {
            		localStorageService.clearAll();
    			});
            },
            getToken: function () {
                var token = localStorageService.get('token');
                return token;
            },
            hasValidToken: function () {
                var token = this.getToken();
                return !!token;
            }
        };
    });

//angular.module('jhipsterApp')
//.factory('AuthServerProvider', function loginService($http, $q, localStorageService, $window) {
//    return {
//        login: function(credentials) {
//            var data = 'j_username=' + encodeURIComponent(credentials.username) +
//                '&j_password=' + encodeURIComponent(credentials.password) +
//                '&remember-me=' + credentials.rememberMe + '&submit=Login';
//            return $http.post('api/authentication', data, {
//                headers: {
//                    'Content-Type': 'application/x-www-form-urlencoded'
//                }
//            }).success(function (response) {
//                return response;
//            });
//        },
//        logout: function() {
//            // logout from the server
//            $http.post('api/logout').success(function (response) {
//                localStorageService.clearAll();
//                // to get a new csrf token call the api
//                $http.get('api/account');
//                return response;
//            });
//        },
//        getToken: function () {
//            var token = localStorageService.get('token');
//            return token;
//        },
//        hasValidToken: function () {
//            var token = this.getToken();
//            return !!token;
//        }
//    };
//});
