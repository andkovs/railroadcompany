<div style="padding: 50px 0;">
    <div class="container">
        <form method="get" action="/ticket">
            <div class="row">
                <h2>Choose stations</h2>
                <hr/>
                <div class="col-md-3">
                    <label>Departure station</label>
                    <select name="selectDepStation" class="form-control" id="selectDepStation" required="true">
                        <option value="">Select</option>
                        <c:forEach var="st" items="${stations}">
                            <option value="${st.stationId}"
                                    <c:if test="${searchResult.depStationId==st.stationId}">selected</c:if>>${st.stationTitle}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label>Arrival station</label>
                    <select name="selectArrStation" class="form-control" id="selectArrStation" required="true">
                        <option value="">Select</option>
                        <c:forEach var="st" items="${stations}">
                            <option value="${st.stationId}"
                                    <c:if test="${searchResult.arrStationId==st.stationId}">selected</c:if>>${st.stationTitle}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <label>From</label>
                    <input class="form-control" type="time" name="fromTime" required="true"
                           <c:if test="${searchResult!=null}">value="${searchResult.fromTime}"</c:if>/ >
                </div>
                <div class="col-md-2">
                    <label>To</label>
                    <input class="form-control" type="time" name="toTime" required="true"
                           <c:if test="${searchResult!=null}">value="${searchResult.toTime}"</c:if>/>
                </div>
                <div class="col-md-2">
                    <label style="display:block;">&nbsp;</label>
                    <button type="submit" class="btn btn-primary"><span
                            class="glyphicon glyphicon-search"></span> Search
                    </button>
                </div>
            </div>
        </form>
        <hr/>
        <c:if test="${searchResult!=null}">
            <c:forEach var="pathTrain" items="${searchResult.pathTrains}">
                <div class="row">
                    <div class="col-md-12">
                        <c:forEach var="station" items="${pathTrain.stations}">
                            ${station.stationTitle} -
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-hover table-striped">
                            <tr>
                                <th style="width: 18%">Train</th>
                                <th style="width: 12%">Departure</th>
                                <th style="width: 7%">Time</th>
                                <th style="width: 12%">Arrival</th>
                                <th style="width: 7%">Time</th>
                                <th>Wagons</th>
                                <th style="width: 3%"></th>
                            </tr>
                            <c:forEach var="train" items="${pathTrain.trains}">
                                <tr>
                                    <td>
                                        <p>${train.trainNumber}</p>
                                    </td>
                                    <c:forEach var="s" items="${train.schedules}">
                                        <c:if test="${s.directionByDirectionId.depStationId==pathTrain.stations[0].stationId}">
                                            <td>
                                                <p>${s.directionByDirectionId.stationByDepStationId.stationTitle}</p>
                                            </td>
                                            <td>
                                                <p>${s.departureTime}</p>
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="s" items="${train.schedules}">
                                        <c:if test="${s.directionByDirectionId.arrStationId==pathTrain.stations[pathTrain.stations.size()-1].stationId}">
                                            <td>
                                                <p>${s.directionByDirectionId.stationByArrStationId.stationTitle}</p>
                                            </td>
                                            <td>
                                                <p>${s.arriveTime}</p>
                                            </td>
                                        </c:if>
                                    </c:forEach>
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
                                        <a href="/ticket/${train.trainId}/${pathTrain.stations[0].stationId}/${pathTrain.stations[pathTrain.stations.size()-1].stationId}"
                                           class="text-warning glyphicon glyphicon-euro"></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>