<%@ tag import="java.util.ArrayList" %>
<%@ tag import="org.markvarabyou.services.UserService" %>
<%@ tag import="org.markvarabyou.services.transfer.User" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ tag isELIgnored="false" %>

<%
    ArrayList<User> users = (new UserService()).getUsers();
%>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="<%=users%>" var="user">
        <h:userslistitem firstName="${user.firstName}" lastName="${user.lastName}" email="${user.email}" userId="${user.id}"/>
    </c:forEach>
    </tbody>
</table>