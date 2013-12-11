<%@ tag isELIgnored="false" %>
<%@attribute name="user" type="org.markvarabyou.services.transfer.User" required="true"%>
<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.email}</td>
</tr>