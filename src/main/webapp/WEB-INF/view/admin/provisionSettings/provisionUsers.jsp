<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../layout/taglib.jsp" %>


<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>Identyfikator handlowca</th>
        <th>Akcja</th>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                    ${user.name}
            </td>
            <td>
                <a href="<spring:url value="#" />" class="btn btn-primary triggerRemove">
                    prowizje
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>

