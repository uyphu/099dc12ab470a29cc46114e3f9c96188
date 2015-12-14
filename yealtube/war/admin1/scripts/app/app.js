'use strict';

angular.module('jhipsterApp', ['LocalStorageModule', 'tmh.dynamicLocale', 'ui.bootstrap',
    'ngResource', 'ui.router', 'ngCookies', 'pascalprecht.translate', 'ngCacheBuster', 'infinite-scroll', 
    'angularSpinner', 'angular-google-gapi'])
    
    .run(function ($rootScope, $location, $window, $http, $state, $translate, Auth, Principal, Language, 
    		ENV, VERSION, GAuth, GApi, GData) {
        $rootScope.ENV = ENV;
        $rootScope.VERSION = VERSION;
        $rootScope.LOADED = false;
        
        //Loading facebook
        $window.fbAsyncInit = function() {
	        FB.init({
	          appId      : '142987259399471',
	          xfbml      : true,
	          version    : 'v2.5'
	        });
	      };
	
	      (function(d, s, id){
	         var js, fjs = d.getElementsByTagName(s)[0];
	         if (d.getElementById(id)) {return;}
	         js = d.createElement(s); js.id = id;
	         js.src = "//connect.facebook.net/en_US/sdk.js";
	         fjs.parentNode.insertBefore(js, fjs);
	       }(document, 'script', 'facebook-jssdk'));
        
		var BASE;
		if($window.location.hostname == 'localhost') {
		BASE = 'http://localhost:9494/_ah/api';
		} else {
		    BASE = 'https://yealtubetest.appspot.com/_ah/api';
		}
		
		  BASE = 'https://yealtubetest.appspot.com/_ah/api';
		
		GApi.load('userxauthtokenendpoint', 'v1', BASE);
		GApi.load('userendpoint', 'v1', BASE);
		GApi.load('calendar', 'v3');
		GAuth.setClient(AppConstant.CLIENT_ID);
		GAuth.setScope('https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/calendar.readonly');
	      
        AppConstant.API_LOAD_TYPE = 2;
        $window.init = function() {
        	//$rootScope.initgapi();
		};
		
        $rootScope.initgapi = function() {
			if (!AppConstant.USER_LOGIN_ENDPOINT_LOADED) {
				Auth.init().then(function(){
					console.log('Loaded login endpoint.');
				},
				function(){
					console.log(ErrorCode.ERROR_INIT_ENDPOINT_SERVICE);
				});
			} 
		};
        
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }

            // Update the language
            Language.getCurrent().then(function (language) {
                $translate.use(language);
            });
        });

        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
            var titleKey = 'global.title';

            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $translate(titleKey).then(function (title) {
                // Change window title with translated one
                $window.document.title = title;
            });
        });

        $rootScope.back = function() {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                //$state.go('home');
            	$state.go('dashboard.home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
        
        
    })
//    .factory('authInterceptor', function ($rootScope, $q, $location, localStorageService) {
//        return {
//            // Add authorization token to headers
//            request: function (config) {
//                config.headers = config.headers || {};
//                var token = localStorageService.get('token');
//                
//                if (token && token.expires && token.expires > new Date().getTime()) {
//                  config.headers['x-auth-token'] = token.token;
//                  config.headers['x-type-token'] = token.type;
//                }
//                
//                return config;
//            }
//        };
//    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, $translateProvider, 
    		tmhDynamicLocaleProvider, httpRequestInterceptorCacheBusterProvider, usSpinnerConfigProvider) {

        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

        $urlRouterProvider.otherwise('/');
        //$urlRouterProvider.otherwise('/dashboard/home');
//        $stateProvider.state('site', {
//            'abstract': true,
//            views: {
//                'navbar@': {
//                    templateUrl: 'scripts/components/navbar/navbar.html',
//                    controller: 'NavbarController'
//                }
//            },
//            resolve: {
//                authorize: ['Auth',
//                    function (Auth) {
//                        return Auth.authorize();
//                    }
//                ],
//                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
//                    $translatePartialLoader.addPart('global');
//                    $translatePartialLoader.addPart('language');
//                }]
//            }
//        });
        
//        $stateProvider
//        .state('site', {
//        	'abstract': true,
//        	templateUrl: 'scripts/app/site/site.html',
//            controller: 'SiteController',
//            data: {
//                roles: []
//            },
//            resolve: {
//                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
//                    $translatePartialLoader.addPart('main');
//                    $translatePartialLoader.addPart('login');
//                    $translatePartialLoader.addPart('language');
//                    //return $translate.refresh();
//                }]
//                
//            }
//        });
        
        $stateProvider
        .state('dashboard', {
        	'abstract': true,
            templateUrl: 'scripts/app/main/main.html',
            controller: 'MainController',
            data: {
                roles: []
            },
            resolve: {
//            	authorize: ['Auth',
//                          function (Auth) {
//                              return Auth.authorize();
//                          }
//                      ],
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('main');
                    $translatePartialLoader.addPart('language');
                    return $translate.refresh();
                }]
                
            }
        });
        
//        $stateProvider.state('site', {
//            'abstract': true,
//            views: {
//                'header@': {
//                    templateUrl: 'scripts/components/header/header.html',
//                    controller: 'HeaderController'
//                }
//            },
//            resolve: {
//                authorize: ['Auth',
//                    function (Auth) {
//                        return Auth.authorize();
//                    }
//                ],
//                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
//                    $translatePartialLoader.addPart('global');
//                    $translatePartialLoader.addPart('language');
//                }]
//            }
//        });


        // Initialize angular-translate
        $translateProvider.useLoader('$translatePartialLoader', {
            urlTemplate: 'i18n/{lang}/{part}.json'
        });

        $translateProvider.preferredLanguage('en');
        $translateProvider.useCookieStorage();
        $translateProvider.useSanitizeValueStrategy('escaped');

        tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
        tmhDynamicLocaleProvider.useCookieStorage();
        tmhDynamicLocaleProvider.storageKey('NG_TRANSLATE_LANG_KEY');
        
        usSpinnerConfigProvider.setDefaults({color: 'blue'});
    });
