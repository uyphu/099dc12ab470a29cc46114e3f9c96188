'use strict';

angular.module('jhipsterApp')
   .factory('TubeSearch', function ($resource, $q, GApi) {
	   return {
		   searchTube: function(querySearch, cursor, count){
			    var p=$q.defer();
	   			var requestData = {};
	   			requestData.cursor = cursor;
	   			requestData.count = count;
	   			requestData.querySearch = querySearch;
	   			GApi.execute(AppConstant.TUBE_ENDPOINT, 'searchTube', requestData).then (function(resp){
	   				if (resp != null && resp.items != null) {
	                   	p.resolve(resp);
	   				} else {
	   					p.resolve(null);
	   				}
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
	   			return p.promise;
		   }
	   }
   });