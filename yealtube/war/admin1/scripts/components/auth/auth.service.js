'use strict';

angular.module('jhipsterApp')
    .factory('Auth', function Auth($rootScope, $state, $q, $translate, Principal, AuthServerProvider, Account, Register, Activate, Password, PasswordResetInit, PasswordResetFinish) {
        return {
//            login: function (credentials, callback) {
//                var cb = callback || angular.noop;
//                var deferred = $q.defer();
//
//                AuthServerProvider.login(credentials).then(function (data) {
//                    // retrieve the logged account information
//                	AppConstant.ACCOUNT = data;
//                    Principal.identity(true).then(function(account) {
//                        // After the login the language will be changed to
//                        // the language selected by the user during his registration
//                        $translate.use(account.langKey);
//                        $translate.refresh();
//                        deferred.resolve(data);
//                    });
//                	deferred.resolve(data);
//                    return cb();
//                }).catch(function (err) {
//                    this.logout();
//                    deferred.reject(err);
//                    return cb(err);
//                }.bind(this));
//
//                return deferred.promise;
//            },
        	
        	login: function (credentials, callback) {
                //var cb = callback || angular.noop;
                var deferred = $q.defer();

                AuthServerProvider.login(credentials).then(function (data) {
                    // retrieve the logged account information
                	if (data.error == null) {
                		AppConstant.ACCOUNT = data;
                        Principal.identity(true).then(function(account) {
                            // After the login the language will be changed to
                            // the language selected by the user during his registration
                            $translate.use(account.langKey);
                            $translate.refresh();
                            deferred.resolve(data);
                        });
                    	//deferred.resolve(data);
					} else {
						deferred.resolve(data);
					}
                	
                });

                return deferred.promise;
            },

            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
            },

            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                        if ($rootScope.toState.data.roles && $rootScope.toState.data.roles.length > 0 && !Principal.isInAnyRole($rootScope.toState.data.roles)) {
                            if (isAuthenticated) {
                                // user is signed in but not authorized for desired state
                                $state.go('accessdenied');
                            }
                            else {
                                // user is not authenticated. stow the state they wanted before you
                                // send them to the signin state, so you can return them when you're done
                                $rootScope.returnToState = $rootScope.toState;
                                $rootScope.returnToStateParams = $rootScope.toStateParams;

                                // now, send them to the signin state so they can log in
                                $state.go('login');
                            }
                        }
                    });
            },
//            createAccount: function (account, callback) {
//                var cb = callback || angular.noop;
//
////                return Register.save(account,
////                    function () {
////                        return cb(account);
////                    },
////                    function (err) {
////                        this.logout();
////                        return cb(err);
////                    }.bind(this)).$promise;
//                	Register.save(account).then(function(){
//                		return cb(account);
//    				},
//    				function(err){
//    					console.log(ErrorCode.ERROR_INIT_ENDPOINT_SERVICE);
//    					this.logout();
//                      return cb(err);
//    				}.bind(this)).$promise;
//                
//            },
            createAccount: function (account) {
            	var p=$q.defer();
            	Register.save(account).then(function(data){
            		p.resolve(data);
				},
				function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					this.logout();
					p.resolve(error);
				});
				
				return p.promise;
            },
            
            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;

//                return Account.save(account,
//                    function () {
//                        return cb(account);w
//                    },
//                    function (err) {
//                        return cb(err);
//                    }.bind(this)).$promise;
                return Account.save(account).then(function(data){
            		return cb(account);
				},
				function(err){
					console.log(ErrorCode.ERROR_INIT_ENDPOINT_SERVICE);
					this.logout();
                  return cb(err);
				}.bind(this)).$promise;
            },

            activateAccount: function (key, callback) {
                var cb = callback || angular.noop;

                return Activate.get(key,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

//            changePassword: function (newPassword, callback) {
//                var cb = callback || angular.noop;
//
//                return Password.save(newPassword, function () {
//                    return cb();
//                }, function (err) {
//                    return cb(err);
//                }).$promise;
//            },
            
            changePassword: function (account) {
            	var p=$q.defer();
            	Password.save(account).then(function(data){
            		p.resolve(data);
				},
				function(error){
					console.log(ErrorCode.ERROR_CALL_ENDPOINT_SERVICE + error);
					this.logout();
					p.resolve(error);
				});
				
				return p.promise;
            },

            resetPasswordInit: function (mail, callback) {
                var cb = callback || angular.noop;

                return PasswordResetInit.save(mail, function() {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordFinish: function(key, newPassword, callback) {
                var cb = callback || angular.noop;

                return PasswordResetFinish.save(key, newPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

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
    		}
        };
    });
