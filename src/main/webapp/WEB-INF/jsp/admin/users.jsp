<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../layout/taglib.jsp" %>


<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>Nazwa</th>
        <th>Email</th>
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
                    ${user.email}
            </td>
            <td>
                    ${user.password}
            </td>
            <td>
                <a href="<spring:url value="/sa/users/remove/${user.id}.html" />" class="btn btn-danger triggerRemove">
                    dezaktywuj
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>


<!-- Modal for removing user -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Usuń użytkownika</h4>
            </div>
            <div class="modal-body">
                Czy na pewno chcesz usunąć użytkownika?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">anuluj</button>
                <a href="" class="btn btn-danger removeBtn">usuń</a>
            </div>
        </div>
    </div>
</div>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $(".triggerRemove").click(function (e) {
            e.preventDefault();
            $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
            $("#modalRemove").modal();
        });
    });
</script>