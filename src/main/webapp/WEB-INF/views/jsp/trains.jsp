<main style="padding: 50px 0;" ng-controller="TicketController">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <a href="/train/0" class="btn btn-success">New train</a>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Train</th>
                        <th>Start</th>
                        <th>Time</th>
                        <th>Finish</th>
                        <th>Time</th>
                        <th style="width: 23%">Shift</th>
                        <th>Wagons</th>
                        <th style="width: 3%"></th>
                        <th style="width: 3%"></th>
                        <th style="width: 3%"></th>
                        <th style="width: 3%"></th>
                    </tr>

                    <c:if test="${not empty trains}">
                        <c:forEach var="train" items="${trains}">
                            <tr>
                                <td>
                                    <p>${train.trainNumber}</p>
                                </td>
                                <td>
                                    <p>${train.schedules[0].directionByDirectionId.stationByDepStationId.stationTitle}</p>
                                </td>
                                <td>
                                    <p>${train.schedules[0].departureTime}</p>
                                </td>
                                <td>
                                    <p>${train.schedules[train.schedules.size()-1].directionByDirectionId.stationByArrStationId.stationTitle}</p>
                                </td>
                                <td>
                                    <p>${train.schedules[train.schedules.size()-1].arriveTime}</p>
                                </td>
                                <td>
                                    <form action="/train/${train.trainId}/shift" method="post">
                                        <div class="row">
                                            <div class="col-xs-4">
                                                <input class="form-control" type="number" name="shift"
                                                       value="${train.shift}"/>
                                            </div>
                                            <button type="submit" class="btn btn-primary glyphicon glyphicon-ok">
                                            </button>
                                        </div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                </td>
                                <td>
                                    <c:forEach var="w" items="${train.wagons}">
                                        <c:if test="${w.wagonType=='third-class sleeper'}"><img
                                                src="/resources/img/wagon_blue75.png"></c:if>
                                    </c:forEach>
                                    <c:forEach var="w" items="${train.wagons}">
                                        <c:if test="${w.wagonType=='second-class sleeper'}"><img
                                                src="/resources/img/wagon_green75.png"></c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="<c:url value="/train/${train.trainId}/enable"/>"
                                       class="<c:if test="${train.enabled==false}">text-danger</c:if> <c:if test="${train.enabled==true}">text-success</c:if> glyphicon glyphicon-off"></a>
                                </td>
                                <td>
                                    <a id="view" href="#" ng-click="preview(${train.trainId})"
                                       class="glyphicon glyphicon-eye-open"></a>
                                </td>
                                <td>
                                    <a href="<c:url value="/train/${train.trainId}"/>"
                                       class="glyphicon glyphicon-edit"></a>
                                </td>
                                <td>
                                    <a href="<c:url value="/train/${train.trainId}/delete"/>"
                                       class="js-delete-confirm text-danger glyphicon glyphicon-trash"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>

            </div>
        </div>
    </div>

    <div id="ticketPreview" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <!-- Header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h2 ng-show="previewData.length>0" class="modal-title">Train {{previewData[0].trainNumber}}</h2>
                    <h2 ng-show="previewData.length<=0" class="modal-title">No ticket's yet here.</h2>
                </div>

                <!-- Body -->
                <div class="modal-body" \>
                    <table class="table table-hover table-striped">
                        <tr>
                            <th>Last name</th>
                            <th>First name</th>
                            <th>Middle name</th>
                            <th>Birth date</th>
                            <th>Wagon</th>
                            <th>Wagon type</th>
                            <th>Place</th>
                            <th>Dep station</th>
                            <th>Arr station</th>
                        </tr>
                        <tr ng-repeat="p in previewData">
                            <td>{{p.user.lastName}}</td>
                            <td>{{p.user.firstName}}</td>
                            <td>{{p.user.middleName}}</td>
                            <td>{{p.user.birthDate}}</td>
                            <td>{{p.wagon.wagonTitle}}</td>
                            <td>{{p.wagon.wagonType}}</td>
                            <td>{{p.seatNumber}}</td>
                            <td>{{p.departureStation.stationTitle}}</td>
                            <td>{{p.arriveStation.stationTitle}}</td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>
    </div>

</main>


<script>
    $(function () {
        $('.js-delete-confirm').click(function (e) {
            if (!confirm('Delete this train?')) {
                e.preventDefault();
            }
        });
    });
</script>

