'use strict';

angular.module('jhipsterApp')
   .factory('Tube', function ($q, DateUtils, GApi) {
	   return {
   			
   			loadAll: function (cursor, count){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.cursor = cursor;
    			requestData.count = count;
    			GApi.execute(AppConstant.TUBE_ENDPOINT, 'listTube', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			insert: function (tube) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.TUBE_ENDPOINT, 'insertTube', tube).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			update: function (tube) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.TUBE_ENDPOINT, 'updateTube', tube).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			delete: function (id) {
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
    			GApi.execute(AppConstant.TUBE_ENDPOINT, 'removeTube', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			}, 
   			
   			get: function (id) {
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
    			GApi.execute(AppConstant.TUBE_ENDPOINT, 'getTube', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			}
	   };
   });