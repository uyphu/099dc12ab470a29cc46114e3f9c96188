'use strict';

angular.module('jhipsterApp')
    .factory('User', function ($q, DateUtils) {
    	return {
		   	init: function() {
				var hwdefer=$q.defer();
				var oauthloaddefer=$q.defer();
				var oauthdefer=$q.defer();
				if (!AppConstant.USER_PROFILE_ENDPOINT_LOADED) {
					gapi.client.load('userendpoint', AppConstant.ENDPOINT_VERSION, function() {
						AppConstant.USER_PROFILE_ENDPOINT_LOADED = true;
						hwdefer.resolve(gapi);
					}, AppConstant.ROOT_API);
				}
				gapi.client.load(AppConstant.OAUTH2, AppConstant.OAUTH2_VERSION, function(){
					oauthloaddefer.resolve(gapi);
				});
				var chain=$q.all([hwdefer.promise,oauthloaddefer.promise]);
				return chain;
   			},
   			
   			loadAll: function (cursor){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.cursor = cursor;
    			requestData.count = AppConstant.MAX_PAGE_SIZE;
    			gapi.client.userendpoint.listUser(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			listUserByGroup: function (groupId, querySearch, cursor){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.groupId = groupId;
    			requestData.querySearch = querySearch;
    			requestData.cursor = cursor;
    			requestData.count = AppConstant.MAX_PAGE_SIZE;
    			gapi.client.userendpoint.listUserByGroup(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			listAuthority: function (userId){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.userId = userId;
    			requestData.count = AppConstant.MAX_PAGE_SIZE;
    			gapi.client.userendpoint.listAuthority(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			addAuthority: function (userId, role){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.userId = userId;
    			requestData.role = role;
    			gapi.client.userendpoint.addAuthority(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			removeAuthority: function (userId, role){
    			var p=$q.defer();
    			var requestData = {};
    			requestData.userId = userId;
    			requestData.role = role;
    			gapi.client.userendpoint.removeAuthority(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			insert: function (user) {
   				var p=$q.defer();
    			gapi.client.userendpoint.insertUser(user).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			update: function (user) {
   				var p=$q.defer();
    			gapi.client.userendpoint.updateUser(user).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			delete: function (id) {
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
    			gapi.client.userendpoint.removeUser(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			}, 
   			
   			get: function (id) {
   				var p=$q.defer();
   				var requestData = {};
   				requestData.id = id;
    			gapi.client.userendpoint.getUser(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			}
	   };
    });
