'use strict';

angular.module('jhipsterApp')
    .factory('Account', function Account($resource, $q, GApi) {
    	return {
    		getAccount: function () {
    			var p=$q.defer();
	        	GApi.executeAuth(AppConstant.USER_ENDPOINT, 'getAccount').then(function(resp) {
	        		  p.resolve(resp);
	          	}).catch(function (err) {
	          		  p.resolve(err);
		        });
	        	return p.promise;
    		},
    		save: function (account){
    			var p=$q.defer();
    			
    			gapi.client.userendpoint.updateUser(account).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
    		}
    	}
    });
