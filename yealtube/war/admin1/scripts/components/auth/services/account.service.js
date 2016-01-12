'use strict';

angular.module('jhipsterApp')
    .factory('Account', function Account($resource, $q, GApi) {
    	return {
    		getAccount: function () {
    			var p=$q.defer();
    			
    			GApi.execute(AppConstant.USER_ENDPOINT, 'getAccount', null).then (function(resp){
    				 p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			
	        	return p.promise;
    		},
    		save: function (account){
    			var p=$q.defer();
    			
    			GApi.execute(AppConstant.USER_ENDPOINT, 'updateUser', null).then (function(resp){
   				 p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
    		}
    	}
    });
