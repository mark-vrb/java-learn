<%@ tag import="org.markvarabyou.services.UserService" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ tag isELIgnored="false" %>

<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title">
            <span class="glyphicon glyphicon-th-list"></span>
            <span> Users List</span>
        </h4>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="<%=(new UserService()).getUsers()%>" var="user">
            <h:userslistitem user="${user}"/>
        </c:forEach>
        </tbody>
    </table>
</div>