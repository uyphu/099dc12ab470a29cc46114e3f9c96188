'use strict';

angular.module('jhipsterApp')
   .factory('UserGroup', function ($q, DateUtils, GApi) {
	   return {
   			
   			loadAll: function (cursor, count){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.cursor = cursor;
    			requestData.count = count;
    			GApi.execute(AppConstant.USERGROUP_ENDPOINT, 'listUserGroup', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			insert: function (userGroup) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.USERGROUP_ENDPOINT, 'insertUserGroup', userGroup).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			},
   			
   			update: function (userGroup) {
   				var p=$q.defer();
   				GApi.execute(AppConstant.USERGROUP_ENDPOINT, 'updateUserGroup', userGroup).then (function(resp){
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
    			GApi.execute(AppConstant.USERGROUP_ENDPOINT, 'removeUserGroup', requestData).then (function(resp){
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
    			GApi.execute(AppConstant.USERGROUP_ENDPOINT, 'getUserGroup', requestData).then (function(resp){
   				 	p.resolve(resp);
    			},function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					p.reject(error);
				});
    			return p.promise;
   			}
	   };
   });