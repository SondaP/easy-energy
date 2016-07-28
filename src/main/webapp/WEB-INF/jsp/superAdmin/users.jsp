<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../layout/taglib.jsp" %>


<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>Nazwa</th>
        <th>Hasło</th>
        <th>Akcja</th>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="<spring:url value="/sa/users/${user.id}.html" />">
                        ${user.name}
                </a>
            </td>
            <td>
                    ${user.password}
            </td>
            <td>
                <a href="<spring:url value="/sa/users/remove/${user.id}.html" />" class="btn btn-danger">
                       usuń
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>