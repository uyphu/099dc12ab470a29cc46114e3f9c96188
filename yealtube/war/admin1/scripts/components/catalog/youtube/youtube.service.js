'use strict';

angular.module('jhipsterApp')
   .factory('Youtube', function ($q, DateUtils, GApi) {
	   return {
   			
   			insert: function (id) {
   				var p=$q.defer();
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
   				GApi.execute(AppConstant.YOUTUBE_ENDPOINT, 'insertVideo', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			update: function (id) {
   				var p=$q.defer();
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
   				GApi.execute(AppConstant.YOUTUBE_ENDPOINT, 'updateVideo', requestData).then (function(resp){
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
    			GApi.execute(AppConstant.YOUTUBE_ENDPOINT, 'getTube', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			}
	   };
   });