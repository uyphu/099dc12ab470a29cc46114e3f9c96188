'use strict';

angular.module('jhipsterApp')
    .factory('AuthServerProvider', function loginService($q, localStorageService, $window) {
        return {
            login: function(credentials) {
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

