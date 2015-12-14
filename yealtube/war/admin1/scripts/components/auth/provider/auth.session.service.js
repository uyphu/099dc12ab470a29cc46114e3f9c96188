'use strict';

angular.module('jhipsterApp')
    .factory('AuthServerProvider', function loginService($q, localStorageService, $window, GApi) {
        return {
            login: function(credentials) {
            	var p=$q.defer();
	        	  GApi.executeAuth(AppConstant.USERXAUTHTOKEN_ENDPOINT, 'authorize', {login:credentials.username, password:credentials.password}).then(function(resp) {
	        		  localStorageService.set('token', resp);
	        		  gapi.auth.setToken({
	        			  access_token: resp.token + resp.type
	        		  });
	        		  p.resolve(resp);
	          	  }).catch(function (err) {
	          		  p.resolve(err);
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

