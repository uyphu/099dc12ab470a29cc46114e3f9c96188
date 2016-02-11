'use strict';

//angular.module('jhipsterApp')
//    .factory('Password', function ($resource) {
//        return $resource('api/account/change_password', {}, {
//        });
//    });
angular.module('jhipsterApp')
	.factory('Password', function ($q, $resource) {
		return {
    		save: function (account){
    			var p=$q.defer();
    			gapi.client.userendpoint.changePassword(account).execute(function(resp) {
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

angular.module('jhipsterApp')
    .factory('PasswordResetInit', function ($resource) {
        return $resource('api/account/reset_password/init', {}, {
        })
    });

angular.module('jhipsterApp')
    .factory('PasswordResetFinish', function ($resource) {
        return $resource('api/account/reset_password/finish', {}, {
        })
    });
