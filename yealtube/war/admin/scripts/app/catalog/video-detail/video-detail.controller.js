'use strict';

angular.module('jhipsterApp')
  .controller('VideoDetailController', function ($rootScope, $scope, $stateParams, Tube, Youtube) {
      $scope.tube = {};
      $scope.content = "<b>this is bold content</b>";
      $scope.limit = 200;
      $scope.lessText = "Read less";
      $scope.moreText = "Read more";
      $scope.dotsClass = "toggle-dots-grey";
      $scope.linkClass = "toggle-link-yellow";
      $scope.load = function (id) {
    	  Youtube.getDetail(id).then(function(result) {
            $scope.tube = result;
            document.getElementById('video-player').innerHTML = $scope.tube.embedHtml;
            $rootScope.TUBE = $scope.tube;
          });
      };
      $scope.load($stateParams.id);
  });