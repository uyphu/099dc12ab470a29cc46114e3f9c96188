//var app = angular.module('MyApp', ['ngMaterial', 'hm.readmore']);
//	app.config(function ($mdThemingProvider) {
//		$mdThemingProvider
//			.theme('docs-dark', 'default')
//			.primaryPalette('grey')
//			.accentPalette('pink')
//			.warnPalette('red')
//			.dark();
//	})
//	app.controller('ExampleController', Example4Controller);
//	/** @ngInject */
//	function Example4Controller($scope) {
//		$scope.text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque gravida vel erat eu vestibulum. Maecenas malesuada, ante at venenatis porta, erat risus porta massa, ac vestibulum libero ex id mauris. Sed faucibus arcu eget lorem vestibulum congue. Phasellus at elit non dolor semper eleifend. Donec nec maximus purus. Donec pretium orci sed ullamcorper scelerisque. Nullam quis elit tristique, interdum eros quis, tincidunt tortor. Fusce odio enim, maximus in sollicitudin vitae, fermentum in elit. Aliquam pretium odio condimentum, fringilla risus in, mollis mi. Phasellus ullamcorper enim vehicula mi commodo laoreet.';
//		$scope.limit = 200;
//		$scope.lessText = "Read less";
//		$scope.moreText = "Read more";
//		$scope.dotsClass = "toggle-dots-grey";
//		$scope.linkClass = "toggle-link-yellow";
//	}

var app = angular.module('MyApp', ['angularSpinner', 'ng','ngAnimate','ngAria', 'ngMaterial', 'hm.readmore']);
app.config(function (usSpinnerConfigProvider, $mdThemingProvider) {
	//usSpinnerConfigProvider.setDefaults({color: 'blue'});
	$mdThemingProvider
		.theme('docs-dark', 'default')
		.primaryPalette('grey')
		.accentPalette('pink')
		.warnPalette('red')
		.dark();
});
app.controller('ExampleController', function($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque gravida vel erat eu vestibulum. Maecenas malesuada, ante at venenatis porta, erat risus porta massa, ac vestibulum libero ex id mauris. Sed faucibus arcu eget lorem vestibulum congue. Phasellus at elit non dolor semper eleifend. Donec nec maximus purus. Donec pretium orci sed ullamcorper scelerisque. Nullam quis elit tristique, interdum eros quis, tincidunt tortor. Fusce odio enim, maximus in sollicitudin vitae, fermentum in elit. Aliquam pretium odio condimentum, fringilla risus in, mollis mi. Phasellus ullamcorper enim vehicula mi commodo laoreet.';
	$scope.limit = 200;
	$scope.lessText = "Read less";
	$scope.moreText = "Read more";
	$scope.dotsClass = "toggle-dots-grey";
	$scope.linkClass = "toggle-link-yellow";
});