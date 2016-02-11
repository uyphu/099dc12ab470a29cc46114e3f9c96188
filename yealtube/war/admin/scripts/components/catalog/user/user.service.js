'use strict';

angular.module('jhipsterApp')
   .factory('User', function ($q, DateUtils, GApi) {
	   return {
   			
   			loadAll: function (cursor, count){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.cursor = cursor;
    			requestData.count = count;
    			GApi.execute(AppConstant.USER_ENDPOINT, 'listUser', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			insert: function (user) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.USER_ENDPOINT, 'insertUser', user).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			update: function (user) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.USER_ENDPOINT, 'updateUser', user).then (function(resp){
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
    			GApi.execute(AppConstant.USER_ENDPOINT, 'removeUser', requestData).then (function(resp){
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
    			GApi.execute(AppConstant.USER_ENDPOINT, 'getUser', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			}
	   };
   });