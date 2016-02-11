'use strict';

angular.module('jhipsterApp', ['LocalStorageModule', 'tmh.dynamicLocale', 'ui.bootstrap',
    'ngResource', 'ui.router', 'ngCookies', 'pascalprecht.translate', 'ngCacheBuster', 'infinite-scroll', 
    'angularSpinner', 'angular-google-gapi', 'hm.readmore'])
    
    .run(function ($rootScope, $location, $window, $http, $state, $translate, Auth, Principal, Language, 
    		ENV, VERSION, GAuth, GApi, GData, localStorageService) {
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
		GApi.load('usergroupendpoint', 'v1', BASE);
		GApi.load('categoryendpoint', 'v1', BASE);
		GApi.load('tubeendpoint', 'v1', BASE);
		GApi.load('youtubeendpoint', 'v1', BASE);
		GApi.load('calendar', 'v3');
//		GAuth.setClient(AppConstant.CLIENT_ID);
//		GAuth.setScope('https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/calendar.readonly');
		
		var token = localStorageService.get('token');
		if (token != null) {
			console.log('Set token: ' + token.token + token.type);
			GAuth.setToken({
				  access_token: token.token + token.type
				});
		}
		
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
    .factory('BearerAuthInterceptor', function ($rootScope, $q, $location, localStorageService) {
        return {
            // Add authorization token to headers
            request: function (config) {
            	
                config.headers = config.headers || {};
                var token = localStorageService.get('token');
                if (token != null) {
                	console.log('Set token Interceptor: ' + token.token + token.type);
                    if (token && token.expires && token.expires > new Date().getTime()) {
                      config.headers['x-auth-token'] = token.token;
                      config.headers['x-type-token'] = token.type;
                    }
                }
                return config;
            }
        };
    })
//	  .factory('BearerAuthInterceptor', function ($window, $q) {
//		    return {
//		        request: function(config) {
//		            config.headers = config.headers || {};
//		            if ($window.localStorage.getItem('token')) {
//		              // may also use sessionStorage
//		                config.headers.Authorization = 'Bearer ' + $window.localStorage.getItem('token');
//		            }
//		            return config || $q.when(config);
//		        },
//		        response: function(response) {
//		            if (response.status === 401) {
//		                //  Redirect user to login page / signup Page.
//		            }
//		            return response || $q.when(response);
//		        }
//		    };
//		});

    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, $translateProvider, 
    		tmhDynamicLocaleProvider, httpRequestInterceptorCacheBusterProvider, usSpinnerConfigProvider) {

        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
        
        // Register the previously created AuthInterceptor.
        $httpProvider.interceptors.push('BearerAuthInterceptor');

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
        
        $stateProvider
        .state('site', {
        	'abstract': true,
        	templateUrl: 'scripts/app/site/site.html',
            controller: 'SiteController',
            data: {
                roles: []
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('main');
                    $translatePartialLoader.addPart('login');
                    $translatePartialLoader.addPart('language');
                    //return $translate.refresh();
                }]
                
            }
        });
        
        $stateProvider
        .state('dashboard', {
        	'abstract': true,
            templateUrl: 'scripts/app/main/main.html',
            controller: 'MainController',
            data: {
                roles: []
            },
            resolve: {
            	authorize: ['Auth',
                          function (Auth) {
                              return Auth.authorize();
                          }
                      ],
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('main');
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('language');
                    //return $translate.refresh();
                }]
            }
        });
        
        //$httpProvider.interceptors.push('authInterceptor');

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
    })
    .directive('readMore', function() {
		  return {
		    restrict: 'A',
		    transclude: true,
		    replace: true,
		    template: '<p></p>',
		    scope: {
		      moreText: '@',
		      lessText: '@',
		      words: '@',
		      ellipsis: '@',
		      char: '@',
		      limit: '@',
		      content: '@'
		    },
		    link: function(scope, elem, attr, ctrl, transclude) {
		      var moreText = angular.isUndefined(scope.moreText) ? ' <a class="read-more">Read More...</a>' : ' <a class="read-more">' + scope.moreText + '</a>',
		        lessText = angular.isUndefined(scope.lessText) ? ' <a class="read-less">Less ^</a>' : ' <a class="read-less">' + scope.lessText + '</a>',
		        ellipsis = angular.isUndefined(scope.ellipsis) ? '' : scope.ellipsis,
		        limit = angular.isUndefined(scope.limit) ? 150 : scope.limit;
		
		      attr.$observe('content', function(str) {
		        readmore(str);
		      });
		
		      transclude(scope.$parent, function(clone, scope) {
		        readmore(clone.text().trim());
		      });
		
		      function readmore(text) {
		
		        var text = text,
		          orig = text,
		          regex = /\s+/gi,
		          charCount = text.length,
		          wordCount = text.trim().replace(regex, ' ').split(' ').length,
		          countBy = 'char',
		          count = charCount,
		          foundWords = [],
		          markup = text,
		          more = '';
		
		        if (!angular.isUndefined(attr.words)) {
		          countBy = 'words';
		          count = wordCount;
		        }
		
		        if (countBy === 'words') { // Count words
		
		          foundWords = text.split(/\s+/);
		
		          if (foundWords.length > limit) {
		            text = foundWords.slice(0, limit).join(' ') + ellipsis;
		            more = foundWords.slice(limit, count).join(' ');
		            markup = text + moreText + '<span class="more-text">' + more + lessText + '</span>';
		          }
		
		        } else { // Count characters
		
		          if (count > limit) {
		            text = orig.slice(0, limit) + ellipsis;
		            more = orig.slice(limit, count);
		            markup = text + moreText + '<span class="more-text">' + more + lessText + '</span>';
		          }
		
		        }
		
		        elem.append(markup);
		        elem.find('.read-more').on('click', function() {
		          $(this).hide();
		          elem.find('.more-text').addClass('show').slideDown();
		        });
		        elem.find('.read-less').on('click', function() {
		          elem.find('.read-more').show();
		          elem.find('.more-text').hide().removeClass('show');
		        });
		
		      }
		    }
		  };
		});
