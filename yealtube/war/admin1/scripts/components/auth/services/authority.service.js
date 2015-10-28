'use strict';

angular.module('jhipsterApp')
    .factory('Authority', function ($resource, $q, DateUtils) {
    	return {
		   	init: function() {
				var hwdefer=$q.defer();
				var oauthloaddefer=$q.defer();
				var oauthdefer=$q.defer();
				if (!AppConstant.AUTHORITY_ENDPOINT_LOADED) {
					gapi.client.load('authorityendpoint', AppConstant.ENDPOINT_VERSION, function() {
						AppConstant.AUTHORITY_ENDPOINT_LOADED = true;
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
    			gapi.client.authorityendpoint.listAuthority(requestData).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			insert: function (authority) {
   				var p=$q.defer();
    			gapi.client.authorityendpoint.insert(authority).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
   			},
   			
   			update: function (authority) {
   				var p=$q.defer();
    			gapi.client.authorityendpoint.updateAuthority(authority).execute(function(resp) {
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
    			gapi.client.authorityendpoint.removeAuthority(requestData).execute(function(resp) {
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
    			gapi.client.authorityendpoint.getAuthority(requestData).execute(function(resp) {
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
