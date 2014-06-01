<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 01/04/14
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section id="restaurantList">
    <ul>
        <c:forEach items="${restaurants.restaurantList}" var="restaurant">
            <li>
                <jsp:include page="restaurantDetail.jsp">
                    <jsp:param name="id" value="${restaurant.id}" />
                    <jsp:param name="name" value="${restaurant.name}" />
                    <jsp:param name="description" value="${restaurant.description}" />
                </jsp:include>
            </li>
        </c:forEach>
    </ul>
</section>
<div id="navigationSlider"></div>


