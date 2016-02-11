'use strict';

angular.module('jhipsterApp')
   .factory('CategorySearch', function ($resource, $q, GApi) {
	   return {
		   searchCategory: function(querySearch, cursor){
			    var p=$q.defer();
	   			var requestData = {};
	   			requestData.cursor = cursor;
	   			requestData.count = AppConstant.MAX_PAGE_SIZE;
	   			requestData.querySearch = querySearch;
	   			GApi.execute(AppConstant.CATEGORY_ENDPOINT, 'searchCategory', requestData).then (function(resp){
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