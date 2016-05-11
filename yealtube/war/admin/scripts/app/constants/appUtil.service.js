/*jshint bitwise: false*/
'use strict';

angular.module('jhipsterApp')
    .service('AppUtil', function ($window) {
        this.getBaseUrl = function () {
        	return "" + window.location.href.match(/^.*\//);
        }
        
        this.updateHeader = function (tube) {
        	$window.document.title = tube.title;
        	//find meta description tag
        	var meta = document.querySelector('meta[name=description]');
        	meta.setAttribute('content', tube.description);
        	meta = document.querySelector('meta[name=title]');
        	meta.setAttribute('content', tube.title);
        	meta = document.querySelector('meta[name=keywords]');
        	meta.setAttribute('content', tube.tags);
        	
        	meta = document.querySelector('meta[property=og\\:site_name]');
        	meta.setAttribute('content', 'newhottubetest');
        	meta = document.querySelector('meta[property=og\\:url]');
        	meta.setAttribute('content', 'https://newhottubetest.appspot.com');
        	meta = document.querySelector('meta[property=og\\:title]');
        	meta.setAttribute('content', tube.title);
        	meta = document.querySelector('meta[property=og\\:image]');
        	meta.setAttribute('content', '//i.ytimg.com/vi/'+tube.id+'/default.jpg');
        	meta = document.querySelector('meta[property=og\\:description]');
        	meta.setAttribute('content', tube.description);
        	
        	meta = document.querySelector('meta[name=twitter\\:site]');
        	meta.setAttribute('content', 'newhottubetest');
        	meta = document.querySelector('meta[name=twitter\\:url]');
        	meta.setAttribute('content', 'https://newhottubetest.appspot.com');
        	meta = document.querySelector('meta[name=twitter\\:title]');
        	meta.setAttribute('content', tube.title);
        	meta = document.querySelector('meta[name=twitter\\:image]');
        	meta.setAttribute('content', '//i.ytimg.com/vi/'+tube.id+'/default.jpg');
        	meta = document.querySelector('meta[name=twitter\\:description]');
        	meta.setAttribute('content', tube.description);
        }
    });
    

