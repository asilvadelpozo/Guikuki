<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 03/04/14
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Guikuki</title>
        <link rel="stylesheet" type="text/css" href="<tiles:getAsString name='styles' />">
        <script src="/static/js/angular.js"></script>
    </head>
    <body>
        <tiles:insertAttribute name="social" />
        <tiles:insertAttribute name="header" />
        <div id="content">
            <tiles:insertAttribute name="content" />
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>
