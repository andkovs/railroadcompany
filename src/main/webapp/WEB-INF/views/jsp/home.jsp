<div style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <h2>Train schedule <c:if test="${station!=null}">- ${station.stationTitle}</c:if></h2>
            <hr/>
            <div class="col-md-4">
                <select
                        <c:if test="${station!=null}">value="${station.stationId}"</c:if> name="selectStation"
                        class="form-control" id="selectStation">
                    <option value="">Select</option>
                    <c:forEach var="st" items="${stations}">
                        <option value="${st.stationId}">${st.stationTitle}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <button href="" id="searchButton" class="btn btn-primary" disabled="true"><span
                        class="glyphicon glyphicon-search"></span>
                    Search
                </button>
            </div>
        </div>
        <hr/>
        <c:if test="${not empty schedules}">
            <div class="row">
                <div class="col-md-6">
                    <table class="table table-hover table-striped">
                        <tr>
                            <th>Train</th>
                            <th>Arrival time</th>
                            <th>Departure time</th>
                            <th>Next station</th>


                            <c:forEach var="schedule" items="${schedules}">
                        <tr>
                            <td>
                                <p>${schedule.trainByTrainId.trainNumber}</p>
                            </td>
                            <c:choose>
                                <c:when test="${schedule.arriveTime==schedule.departureTime}">
                                    <td>
                                        <p>- - -</p>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <p>${schedule.arriveTime}</p>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <p>${schedule.departureTime}</p>
                            </td>
                            <td>
                                <p>${schedule.directionByDirectionId.stationByArrStationId.stationTitle}</p>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</div>

<script>
    $(function () {
        var select = $("#selectStation");
        var button = $("#searchButton");
        select.change(function () {
            var selectValue = select.val();
            if (selectValue == '') {
                button.prop('disabled', true);
            }
            else {
                button.prop('disabled', false);
            }
        });
        button.click(function () {
            var selectValue = select.val();
            if (selectValue != '') {
                window.location = "/" + selectValue;
            }
        })
    });

</script>
