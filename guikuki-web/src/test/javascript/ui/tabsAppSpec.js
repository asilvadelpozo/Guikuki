/**
 * Created by antoniosilvadelpozo on 06/07/14.
 */

describe('tabsApp', function() {

    beforeEach(module('tabsApp'));

    describe('tabsController', function() {

        var scope, tabsCtrl;

        beforeEach(inject(function ($controller, $rootScope) {
            scope = $rootScope.$new();
            tabsCtrl = $controller('tabsController', {'$scope': scope});
        }));

        it('selected tab should be empty in the beginning', function() {
            expect(scope.selectedTab).toBe('');
        });

        it('selected tab should be correct after selecting one tab', function() {
            scope.selectedTab = 'Tab0';
            scope.selectTab('Tab1');
            expect(scope.selectedTab).toBe('Tab1');
        });

    });

});
