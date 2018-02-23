<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div style="position: relative; padding-top: 100%; background-color: #f0f0f0">
                    <div id="map" style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"></div>
                </div>
            </div>
            <div class="col-md-4">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Stations</th>
                        <th style="width: 3%"></th>
                        <th style="width: 3%"></th>
                    </tr>

                    <c:if test="${not empty stations}">
                        <c:forEach var="station" items="${stations}">
                            <tr>
                                <td>
                                    <p>${station.stationTitle}</p>
                                </td>
                                <td>
                                    <a href="<c:url value="/station/${station.stationId}"/>"
                                       class="glyphicon glyphicon-edit"></a>
                                </td>
                                <td>
                                    <a href="<c:url value="/station/${station.stationId}/delete"/>"
                                       class="text-danger glyphicon glyphicon-trash"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                </table>
                <a href="/station/0" class="btn btn-success">New station</a>
            </div>
        </div>
    </div>

</main>

<script>
    $(function () {
    function initMap() {
        var station = {lat: ${stations[1].lat}, lng: ${stations[1].lng}};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 7,
            center: station
        });
        var stations = [
            <c:forEach var="st" items="${stations}">
            {
                id: ${st.stationId},
                title: '${st.stationTitle}',
                location: {lat: ${st.lat}, lng: ${st.lng}}
            },
            </c:forEach>
        ];
        for (var i = 0; i < stations.length; i++) {
            var s = stations[i];
            var marker = new google.maps.Marker({
                position: s.location,
                map: map,
                icon: 'http://maps.google.com/mapfiles/kml/paddle/red-circle.png',
                title: s.title
            });
        }
        var directions = [
            <c:forEach var="nSt" items="${neighbouringStations}" varStatus="loop">
            [
                {lat: ${nSt.departureStation.lat}, lng: ${nSt.departureStation.lng}},
                {lat: ${nSt.arriveStation.lat}, lng: ${nSt.arriveStation.lng}}
            ]${!loop.last ? ',' : ''}
            </c:forEach >
        ];
        for (var i = 0; i <= directions.length; i++) {
            var direction = new google.maps.Polyline({
                path: directions[i],
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2,
                map: map
            });
        }
    }
    initMap();
    });
</script>
