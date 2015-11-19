'use strict';

angular.module('jhipsterApp')
	.factory('Activate', function ($q) {
		return {
			get: function(activateKey1) {
				var p=$q.defer();
				var requestData = {};
				requestData.activateKey = 2;
				gapi.client.userendpoint.activateAccount(requestData).execute(function(resp){
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


