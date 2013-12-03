<%@ tag isELIgnored="false" %>
<%@attribute name="firstName" required="true"  %>
<%@attribute name="lastName" required="true"  %>
<%@attribute name="email" required="true"  %>
<%@attribute name="userId" required="true"  %>
<tr>
    <td>${userId}</td>
    <td>${firstName}</td>
    <td>${lastName}</td>
    <td>${email}</td>
</tr>