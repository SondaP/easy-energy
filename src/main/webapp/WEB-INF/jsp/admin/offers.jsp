<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../layout/taglib.jsp" %>


<table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
        <th>Typ oferty</th>
        <th>Numer oferty</th>
        <th>Firma</th>
        <th>Data utworzenia</th>
        <th>Data ostatniej edycji</th>
        <th>Akcja</th>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${offers}" var="offer">
        <tr>
            <td>
                    ${offer.productCode}
            </td>
            <td>
                    ${offer.offerNumber}
            </td>
            <td>
                    ${offer.companyName}
            </td>
            <td>
                    ${offer.creationDate}
            </td>
            <td>
                    ${offer.lastEdition}

            </td>
            <td>
                <a href="<spring:url value="${pageContext.request.contextPath}/a/electricityOffer/${offer.id}.json"/>"
                   class="btn btn-info"> Wyświetl
                </a>
                <a href="<spring:url value="#" />" class="btn btn-primary">
                    Edytuj
                </a>
                <a href="<spring:url value="#" />" class="btn btn-success">
                    Drukuj
                </a>
                <a href="<spring:url value="/a/electricityOffer/remove/${offer.id}.html"/>"
                   class="btn btn-danger triggerRemove">
                    Usuń
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
                <h4 class="modal-title">Usuń ofertę</h4>
            </div>
            <div class="modal-body">
                Czy na pewno chcesz usunąć ofertę?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">anuluj</button>
                <a href="" class="btn btn-danger removeBtn">usuń</a>
            </div>
        </div>
    </div>
</div>


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