'use strict';

angular.module('jhipsterApp')
   .controller('TubeController', function ($scope, $rootScope, $timeout, usSpinnerService, 
		   Tube, TubeSearch,  Youtube, YoutubeSearch, ParseLinks, localStorageService) {
       $scope.tubes = [];
       $scope.page = 1;
       $scope.cursor = null;
       $scope.invalidQuerySearch = null;
       $scope.spinneractive = false;
       $scope.invalidName = null;
       $scope.value = 10;
       $scope.busy  = false;
       $scope.searchQuery = localStorageService.get('searchTubeQuery');
       
       $scope.loadAll = function() {
    	   $scope.busy  = true;
    	   $scope.search($scope.searchQuery, $scope.cursor, AppConstant.MAX_INIT_PAGE_SIZE);
       };
       $scope.reset = function() {
           $scope.page = 1;
           $scope.cursor = null;
           $scope.tubes = [];
           $scope.loadAll();
           $scope.busy  = false;
       };
       $scope.loadPage = function(page) {
           $scope.page = page;
           if ($scope.searchQuery != null) {
        	   $scope.search($scope.searchQuery, $scope.cursor, AppConstant.MAX_NEXT_PAGE_SIZE);
           } else {
        	   if ($scope.cursor != null) {
        		   $scope.search(null, $scope.cursor, AppConstant.MAX_NEXT_PAGE_SIZE);
               }
           }
       };
       
       $scope.showUpdate = function (id) {
           Tube.get(id).then(function(result) {
               $scope.tube = result;
               $('#saveTubeModal').modal('show');
           });
       };
       
       $scope.save = function () {
	       if ($scope.tube.id != null) {
	    	   var account = localStorageService.get('account');
	    	   $scope.tube.userId = account.id;
	           Youtube.update($scope.tube.id).then(function (data){
	        	   if (data.error != null) {
	        		   showError(data.code);
	        	   } else {
	        		   $scope.refresh();
	        	   }
	           });
	       } else {
	    	   var account = localStorageService.get('account');
	    	   $scope.tube.userId = account.id;
	    	   Youtube.insert($scope.tube.id).then(function (data){
	    		   if (data.error != null) {
	        		   showError(data.code);
	        	   } else {
	        		   $scope.refresh();
	        	   }
	           });
	       }
       };

       $scope.delete = function (id) {
    	   Tube.get(id).then(function (data){
    		   $scope.tube = data;
    		   $('#deleteTubeConfirmation').modal('show');
    	   });
       };

       $scope.confirmDelete = function (id) {
           Tube.delete(id).then(function (data){
        	   $scope.reset();
               $('#deleteTubeConfirmation').modal('hide');
               $scope.clear();
    	   });
       };
       
       $scope.searchHandler = function() {
    	   $scope.invalidQuerySearch = null;
    	   if ($scope.cursor == null) {
     		   $scope.tubes = [];
     	   }
    	   $scope.search($scope.searchQuery, $scope.cursor, AppConstant.MAX_INIT_PAGE_SIZE);
       }

       $scope.search = function (searchQuery, cursor, count) {
    	   if (searchQuery != null && searchQuery != '') {
    		   if (searchQuery.indexOf('id:') != -1) {
    			   var query = searchQuery.split(':', 2);
    			   try {
    				   var id = query[1];
					   $scope.startSpin();
					   Tube.get(id).then(function(data){
						   startTimer();
						   $scope.tubes = [];
						   if (data != null) {
							   $scope.tubes.push(data.result);
						   }
					   });
    				} catch (e) {
    					$scope.invalidQuerySearch = 'ERROR';
    				}
    		   } else {
    			   searchTube(searchQuery, cursor, count)
    		   }
    	   } else {
    		   searchTube(null, cursor, count);
    	   }
       };
       
       function searchTube(searchQuery, cursor, count) {
    	   $scope.startSpin();
    	   $scope.busy  = true;
		   TubeSearch.searchTube(searchQuery, cursor, count).then(function(data) {
			   $scope.stopSpin();
    		   if (data != null) {
    			   localStorageService.set('searchTubeQuery', searchQuery);
    			   for (var i = 0; i < data.items.length; i++) {
                       $scope.tubes.push(data.items[i]);
      			   }
    			   $scope.cursor = data.nextPageToken;
    		   }
    		   $scope.busy  = false;
       		}, function(response) {
       			$scope.stopSpin();
       			$scope.invalidQuerySearch = 'ERROR';
       		});
       };

       $scope.refresh = function () {
           $scope.reset();
           $('#saveTubeModal').modal('hide');
           $scope.clear();
       };

       $scope.clear = function () {
    	   $scope.invalidName = null;
           $scope.tube = {name: null, description: null, url: null, id: null, 
        		   status:null, like:null, dislike:null, rating:null, dateAdded:null, dateModified:null};
           $scope.editForm.$setPristine();
           
           //$scope.editForm.$setUntouched();
       };
       
       $scope.change = function() {
    	   $scope.cursor = null;
       };
       
       $scope.startSpin = function() {
    	   if (!$scope.spinneractive) {
    		   usSpinnerService.spin('spinner-1');
    	   }
       };

	   $scope.stopSpin = function() {
		   if ($scope.spinneractive) {
			   usSpinnerService.stop('spinner-1');
		   }
	   };

	   $rootScope.$on('us-spinner:spin', function(event, key) {
		   $scope.spinneractive = true;
	   });

	   $rootScope.$on('us-spinner:stop', function(event, key) {
		   $scope.spinneractive = false;
	   });
	   
	   function startTimer() {
           //var timer = $timeout(function () {
        	   $scope.stopSpin();
           //}, 6000);
       };
	   
	   function showError(errorCode) {
		   if (errorCode == 409) {
			   $scope.invalidName = 'ERROR';
		   }
       };
       
       $scope.loadAll();
   });