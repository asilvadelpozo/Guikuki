<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 07/06/14
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="restaurant">
    <header class="headerRestaurant">
        <p class="titleRestaurant">${restaurant.name}</p>
        <p></p>
    </header>
    <section class="contentRestaurant">
        <img src="<c:url value="/photo/${restaurant.id}/content" />" alt="${restaurant.name}">
        <div class="descriptionRestaurant">
            <p>${restaurant.description}</p>
        </div>
    </section>
</article>
