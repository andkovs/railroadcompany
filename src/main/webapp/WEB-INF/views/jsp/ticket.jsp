<div style="padding: 50px 0;">
    <div class="col-xs-6 col-xs-offset-4">
        <c:if test="${not empty error}">
            <div class="alert alert-danger col-xs-8 form-group" role="alert">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success col-xs-8 form-group" role="alert">${success}</div>
        </c:if>
    </div>
    <form:form action="/ticket/buy" method="post" class="container">
        <input hidden name="trainId" value="${ticket.train.trainId}"/>
        <input hidden name="depStationId" value="${ticket.depStation.stationId}"/>
        <input hidden name="arrStationId" value="${ticket.arrStation.stationId}"/>
        <div class="row">
            <div class="col-xs-10">
                <h3>Train number</h3>
                <p>${ticket.train.trainNumber}</p>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-xs-5">
                <h3>Departure station</h3>
                <p>${ticket.depStation.stationTitle}</p>
                <h3>Departure time</h3>
                <p>${ticket.depTime}</p>
            </div>
            <div class="col-xs-5">
                <h3>Arrival station</h3>
                <p>${ticket.arrStation.stationTitle}</p>
                <h3>Departure time</h3>
                <p>${ticket.arrTime}</p>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-xs-10">
                <h3>Number of all/available seats</h3>
                <p>${ticket.numberOfSeats}/${ticket.numberOfAvailableSeats}</p>
            </div>
        </div>
        <hr/>
        <button type="submit" class="btn btn-primary"><span
                class="glyphicon glyphicon-euro"></span> Buy
        </button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</div>
