<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Train</th>
                        <th>Start</th>
                        <th>Time</th>
                        <th>Finish</th>
                        <th>Time</th>
                        <th>Wagons</th>
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
                                    <p>${train.schedules[0].arriveTime}</p>
                                </td>
                                <td>
                                    <c:forEach var="w" items="${train.wagons}">
                                        <c:if test="${w.wagonType=='third-class sleeper'}"><img src="/resources/img/wagon_blue75.png"></c:if>
                                    </c:forEach>
                                    <c:forEach var="w" items="${train.wagons}">
                                        <c:if test="${w.wagonType=='second-class sleeper'}"><img src="/resources/img/wagon_green75.png"></c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="<c:url value="/train/${train.trainId}"/>"
                                       class="glyphicon glyphicon-edit"></a>
                                </td>
                                <td>
                                    <a href="<c:url value="/train/${train.trainId}/delete"/>"
                                       class="text-danger glyphicon glyphicon-trash"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                </table>
                <a href="/train/0" class="btn btn-success">New train</a>
            </div>
        </div>
    </div>

</main>

