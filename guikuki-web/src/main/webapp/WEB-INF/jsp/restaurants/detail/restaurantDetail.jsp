<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 07/06/14
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    <div class="verticalTabs">

        <section class="verticalTab">

            <input type="radio" id="general" name="restaurantSections" checked>
            <label for="general" class="firstVerticalLabel"><spring:message code="restaurant.detail.general" /></label>

            <div class="tabContent general">
                <section class="pictureSection">
                    <img class="mainPicture" src="<c:url value="/photo/${restaurant.id}/content" />" alt="${restaurant.name}">
                </section>
                <section class="dataSection">
                    <header class="headerRestaurant">
                        <p class="titleRestaurantDetail">${restaurant.name}</p>
                    </header>
                    <p class="description">${restaurant.description}</p>
                    <c:forEach items="${restaurant.categories}" var="category">
                        <img src="/static/images/icons/${category}.png" alt="${category}" title="${category}">
                    </c:forEach>
                    <p><span class="underline"><spring:message code="restaurant.detail.general.zone" /></span><span>: ${restaurant.zone}</span></p>
                    <p><span class="underline"><spring:message code="restaurant.detail.general.address" /></span><span>: ${restaurant.address}</span></p>
                    <p><span class="underline"><spring:message code="restaurant.detail.general.telephone" /></span><span>: ${restaurant.telephone}</span></p>
                </section>
            </div>

        </section>

        <section class="verticalTab">

            <input type="radio" id="gallery" name="restaurantSections">
            <label for="gallery"><spring:message code="restaurant.detail.gallery" /></label>

            <div class="tabContent gallery">
            </div>

        </section>

        <section class="verticalTab">

            <input type="radio" id="menu" name="restaurantSections">
            <label for="menu"><spring:message code="restaurant.detail.menu" /></label>

            <div class="tabContent menu">
            </div>

        </section>

        <section class="verticalTab">

            <input type="radio" id="opinions" name="restaurantSections">
            <label for="opinions" class="lastVerticalLabel"><spring:message code="restaurant.detail.opinions" /></label>

            <div class="tabContent opinions">
            </div>

        </section>
    </div>



