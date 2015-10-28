'use strict';

angular.module('jhipsterApp')
    .factory('Account', function Account($resource, $q) {
//        return $resource('api/account', {}, {
//            'get': { method: 'GET', params: {}, isArray: false,
//                interceptor: {
//                    response: function(response) {
//                        // expose response
//                        return response;
//                    }
//                }
//            }
//        });
    	return {
    		getAccount: function () {
    			var p=$q.defer();
//    			gapi.client.userendpoint.getAccount().execute(function(resp) {
//                    if (resp != null) {
//                    	p.resolve(AppConstant.ACCOUNT);
//    				} else {
//    					p.resolve(null);
//    				}
//    			});
    			p.resolve(AppConstant.ACCOUNT);
    			return p.promise;
    		},
    		save: function (account){
    			var p=$q.defer();
    			
    			gapi.client.userendpoint.updateUser(account).execute(function(resp) {
                    if (resp != null) {
                    	p.resolve(resp);
    				} else {
    					p.resolve(null);
    				}
    			});
    			return p.promise;
    		}
    	}
    });
