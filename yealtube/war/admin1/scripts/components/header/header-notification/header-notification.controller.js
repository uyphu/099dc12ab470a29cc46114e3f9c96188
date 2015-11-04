'use strict';

angular.module('jhipsterApp')
    .controller('HeaderNotificationController', function ($scope, $rootscope) {
        $scope.languages1 = ['Jani2','Hege2','Kai3'];
        $scope.curState = $rootscope.$state;
    });
