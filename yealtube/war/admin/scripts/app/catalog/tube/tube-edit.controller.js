'use strict';

angular.module('jhipsterApp')
  .controller('TubeEditController', function ($scope, $stateParams, localStorageService, usSpinnerService, Youtube, Tube, Category) {
      $scope.tube = {};
      $scope.categories = [];
      $scope.load = function (id) {
    	  loadData(id);
    	  loadCategories();
      };
      
      function loadData(id) {
    	  $scope.startSpin();
    	  Youtube.getDetail(id).then(function(result) {
    		  $scope.stopSpin();
	          $scope.tube = result;
	          var account = localStorageService.get('account');
	    	  $scope.tube.userId = account.id;
	      });
      };
      
      $scope.save = function () {
    	  var account = localStorageService.get('account');
    	  $scope.tube.userId = account.id;
	      if ($scope.tube.id != null) {
	    	  Tube.update($scope.tube).then(function (data){
	        	   if (data.error != null) {
	        		   showError(data.code);
	        	   } else {
	        		   $scope.success = 'OK';
	        	   }
	           });
	       }
      };
      
      function loadCategories() {
    	  $scope.startSpin();
      	   Category.loadAll(null, null).then(function(data) {
      		   $scope.stopSpin();
      		   if (data != null) {
      			   if (data.items != null) {
   	    			   for (var i = 0; i < data.items.length; i++) {
   	                     $scope.categories.push(data.items[i]);
   	    			   }
      			   }
      		   }
      	   });
      };
      
      function showError(errorCode) {
		   if (errorCode == 404) {
			   $scope.idNotFoundError = 'ERROR';
		   } else 
		   if (errorCode == 502) {
			   $scope.inputError = 'ERROR';
		   } else {
			   $scope.updateError = 'ERROR';
		   }
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
      
      $scope.load($stateParams.id);
  });