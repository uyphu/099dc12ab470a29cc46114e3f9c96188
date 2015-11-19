'use strict';

angular.module('jhipsterApp')
	.factory('Register', function ($q, $http) {
		return {
			save: function(account) {
				var p=$q.defer();
				var newLlamaRecruit= null;
				//$http({method: 'GET', url: 'https://proconcoapp.appspot.com/_ah/api/userendpoint/v1/user', data: newLlamaRecruit});
		    	gapi.client.userendpoint.insertUser(account).execute(function(resp){
		    		if (resp != null) {
		    			//console.log(resp)
		        		p.resolve(resp);
					} else {
						p.resolve(null);
					}
		    	});
		    	return p.promise;
			}
		};
		
	});
