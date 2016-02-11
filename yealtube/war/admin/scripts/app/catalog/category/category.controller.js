'use strict';

angular.module('jhipsterApp')
   .controller('CategoryController', function ($scope, $rootScope, $timeout, usSpinnerService, Category, CategorySearch, ParseLinks) {
       $scope.categorys = [];
       $scope.page = 1;
       $scope.cursor = null;
       $scope.invalidQuerySearch = null;
       $scope.spinneractive = false;
       $scope.invalidName = null;
       //$scope.startcounter = 0;
       $scope.loadAll = function() {
    	   listData($scope.cursor, AppConstant.MAX_PAGE_SIZE);
       };
       $scope.reset = function() {
           $scope.page = 1;
           $scope.cursor = null;
           $scope.categorys = [];
           $scope.loadAll();
       };
       $scope.loadPage = function(page) {
           $scope.page = page;
           if ($scope.searchQuery != null) {
        	   $scope.search();
           } else {
        	   if ($scope.cursor != null) {
            	   //listData($scope.cursor);
        		   listData($scope.cursor, AppConstant.MAX_PAGE_SIZE);
               }
           }
       };
       
       $scope.showUpdate = function (id) {
           Category.get(id).then(function(result) {
               $scope.category = result;
               $('#saveCategoryModal').modal('show');
           });
       };
       
       function listData(cursor, count) {
    	   $scope.startSpin();
    	   Category.loadAll(cursor, count).then(function(data) {
    		   $scope.stopSpin();
    		   if (data != null) {
    			   if (data.items != null) {
	    			   for (var i = 0; i < data.items.length; i++) {
	                     $scope.categorys.push(data.items[i]);
	    			   }
	    			   $scope.cursor = data.nextPageToken;
    			   }
    		   }
    	   });
       };

       $scope.save = function () {
           if ($scope.category.id != null) {
        	   //$scope.category.updUid = AppConstant.ACCOUNT.login;
               Category.update($scope.category).then(function (data){
            	   if (data.error != null) {
            		   showError(data.code);
            	   } else {
            		   $scope.refresh();
            	   }
               });
           } else {
        	   //$scope.category.crtUid = AppConstant.ACCOUNT.login;
        	   //$scope.category.updUid = AppConstant.ACCOUNT.login;
        	   Category.insert($scope.category).then(function (data){
        		   if (data.error != null) {
            		   showError(data.code);
            	   } else {
            		   $scope.refresh();
            	   }
               });
           }
       };

       $scope.delete = function (id) {
    	   Category.get(id).then(function (data){
    		   $scope.category = data;
    		   $('#deleteCategoryConfirmation').modal('show');
    	   });
       };

       $scope.confirmDelete = function (id) {
           Category.delete(id).then(function (data){
        	   $scope.reset();
               $('#deleteCategoryConfirmation').modal('hide');
               $scope.clear();
    	   });
       };

       $scope.search = function () {
    	   $scope.invalidQuerySearch = null;
    	   if ($scope.cursor == null) {
     		   $scope.categorys = [];
     	   }
    	   if ($scope.searchQuery != null && $scope.searchQuery != '') {
    		   if ($scope.searchQuery.indexOf('id:') != -1) {
    			   var query = $scope.searchQuery.split(':', 2);
    			   try {
    				   var id = parseInt(query[1]);
    				   if (!isNaN(id)) {
    					   $scope.startSpin();
    					   Category.get(id).then(function(data){
    						   startTimer();
    						   $scope.categorys = [];
    						   if (data != null) {
    							   $scope.categorys.push(data.result);
    						   }
    					   });
    				   } else {
    					   $scope.invalidQuerySearch = 'ERROR';
    				   }
    				} catch (e) {
    					$scope.invalidQuerySearch = 'ERROR';
    				}
    			   
    			   
    		   } else {
    			   $scope.startSpin();
    			   CategorySearch.searchCategory($scope.searchQuery, $scope.cursor).then(function(data) {
    				   $scope.stopSpin();
    	    		   if ($scope.cursor == null) {
    	    			   $scope.categorys = [];
    	    		   }
    	    		   if (data != null) {
    	    			   for (var i = 0; i < data.items.length; i++) {
    	                       $scope.categorys.push(data.items[i]);
    	      			   }
    	    			   $scope.cursor = data.nextPageToken;
    	    		   }
    	       		}, 
    	       		function(response) {
    	               if(response.status === 404) {
    	                   $scope.loadAll();
    	               }
    	       		});
    		   }
    	   } else {
    		   listData($scope.cursor, AppConstant.MAX_PAGE_SIZE);
    	   }
       };

       $scope.refresh = function () {
           $scope.reset();
           $('#saveCategoryModal').modal('hide');
           $scope.clear();
       };

       $scope.clear = function () {
    	   $scope.invalidName = null;
           $scope.category = {name: null, description: null, metaTitle: null, id: null, parentId:null, metaDescription:null, metaKeyword:null};
           $scope.editForm.$setPristine();
           //$scope.editForm.$setUntouched();
       };
       
       $scope.change = function() {
    	   $scope.cursor = null;
       };
       
       $scope.startSpin = function() {
    	   if (!$scope.spinneractive) {
    		   usSpinnerService.spin('spinner-1');
    		   //$scope.startcounter++;
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