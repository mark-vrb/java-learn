<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="/WEB-INF/tags/csajsp-taglib.tld" prefix="csajsp" %>
<html>
<head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h:userslist/>

    <div>
        <csajsp:if>
            <csajsp:condition>true</csajsp:condition>
            <csajsp:then>Condition was true</csajsp:then>
            <csajsp:else>Condition was false</csajsp:else>
        </csajsp:if>
        <p>
            <csajsp:if>
                <csajsp:condition><%= request.isSecure() %></csajsp:condition>
            <csajsp:then>Request is using SSL (https)</csajsp:then>
            <csajsp:else>Request is not using SSL</csajsp:else>
            </csajsp:if>
        <p>
            Some coin tosses:<br>
            <csajsp:if>
            <csajsp:condition>
                    <%= Math.random() > 0.5 %>
            </csajsp:condition>
            <csajsp:then><b>Heads</b><br></csajsp:then>
            <csajsp:else><b>Tails</b><br></csajsp:else>
            </csajsp:if>
    </div>
</div>
</body>
</html>
