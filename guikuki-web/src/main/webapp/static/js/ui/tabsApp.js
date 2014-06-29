/**
 * Created by antoniosilvadelpozo on 29/06/14.
 */

var tabsApp = angular.module('tabsApp', []);

tabsApp.controller('tabsController', [ '$scope', function($scope) {

    $scope.selectedTab = '';

    $scope.selectTab = function(selectedTab) {
        $scope.selectedTab = selectedTab;
    };

    $scope.getSelectedTab = function() {
        return $scope.selectedTab;
    };

}]);
