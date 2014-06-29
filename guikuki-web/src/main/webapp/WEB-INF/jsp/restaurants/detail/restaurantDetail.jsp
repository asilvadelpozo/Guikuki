<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 07/06/14
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    <div class="restaurantDetail" ng-app="tabsApp" ng-controller="tabsController as tabsCtrl" ng-init="selectTab('general')">
        <div class="tabSet">
            <ul>
                <li class="tab first" ng-class="{selected: getSelectedTab() == 'general'}">
                    <a href="" ng-click="selectTab('general')"><spring:message code="restaurant.detail.general" /></a>
                </li>
                <li class="tab" ng-class="{selected: getSelectedTab() == 'gallery'}">
                    <a href="" ng-click="selectTab('gallery')"><spring:message code="restaurant.detail.gallery" /></a>
                </li>
                <li class="tab" ng-class="{selected: getSelectedTab() == 'menu'}">
                    <a href="" ng-click="selectTab('menu')"><spring:message code="restaurant.detail.menu" /></a>
                </li>
                <li class="tab last" ng-class="{selected: getSelectedTab() == 'opinions'}">
                    <a href="" ng-click="selectTab('opinions')"><spring:message code="restaurant.detail.opinions" /></a>
                </li>
            </ul>
        </div>

        <div class="tabContent" ng-switch="getSelectedTab()">
            <div class="general" ng-switch-when="general">
                <jsp:include page="./sections/general.jsp">
                    <jsp:param name="restaurant" value="${restaurant}" />
                </jsp:include>
            </div>
            <div ng-switch-when="gallery">Gallery</div>
            <div ng-switch-when="menu">Menu</div>
            <div ng-switch-when="opinions">Opinions</div>
        </div>

    </div>

    <script src="/static/js/ui/tabsApp.js"></script>



