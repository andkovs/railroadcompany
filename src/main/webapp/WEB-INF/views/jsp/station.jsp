<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div style="position: relative; padding-top: 100%; background-color: #f0f0f0">
                    <div id="map" style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"></div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row">
                    <c:if test="${msg!=null}">
                        <div class="alert alert-danger col-xs-12 form-group" role="alert">
                            <p>${msg}</p>
                        </div>
                    </c:if>
                </div>
                <h3>${station.stationTitle}</h3>
                <form:form method="POST" action="/station/add" modelAttribute="station">
                    <form:hidden path="stationId"/>
                    <div class="form-group">
                        <form:label path="stationTitle">Title</form:label>
                        <form:input path="stationTitle" class="form-control" required="true"/>
                    </div>
                    <div class="row">
                        <div class="col-md-6 form-group">
                            <form:label path="lat">Lat</form:label>
                            <form:input path="lat" id="latInput" class="form-control" type="number" required="true"/>
                        </div>
                        <div class="col-md-6 form-group">
                            <form:label path="lng">Lng</form:label>
                            <form:input path="lng" id="lngInput" class="form-control" type="number" required="true"/>
                        </div>
                    </div>
                    <label>Linked Station</label>
                    <div class="row">
                        <c:forEach var="st" items="${stations}">
                            <div class="col-xs-6">
                                <div class="checkbox">
                                    <label>
                                        <form:checkbox path="arriveStationIds" value="${st.stationId}"
                                                       data-lat="${st.lat}"
                                                       data-lng="${st.lng}"></form:checkbox>
                                            ${st.stationTitle}
                                    </label>
                                </div>
                            </div>

                        </c:forEach>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="/station" class="btn btn-default">Back</a>
                </form:form>
            </div>
        </div>
    </div>
</main>

<script>
    function initMap() {
        var station = {lat: ${station.lat}, lng: ${station.lng}};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: station
        });
        //marker
        var markerGreen = new google.maps.Marker({
            position: station,
            map: map,
            draggable: true,
            icon: 'https://maps.google.com/mapfiles/kml/paddle/grn-circle.png'
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
                icon: 'http://maps.google.com/mapfiles/kml/paddle/red-circle.png'
            });
        }
        var directions = [
            <c:forEach var="nSt" items="${neighbouringStations}" varStatus="loop">
            [
                {lat: ${nSt.stationByDepStationId.lat}, lng: ${nSt.stationByDepStationId.lng}},
                {lat: ${nSt.stationByArrStationId.lat}, lng: ${nSt.stationByArrStationId.lng}}
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
        google.maps.event.addListener(markerGreen, 'dragend', function (event) {
            var lat = event.latLng.lat();
            var lng = event.latLng.lng();
            $('#latInput').val(lat);
            $('#lngInput').val(lng);
            markerGreen.setPosition({lat: lat, lng: lng});
            clearLines();
            drawLines();
        });
        var neighbourStations = [];

        function drawLines() {
            $('[name="arriveStationIds"]').filter(':checked').each(function () {
                var array = [{
                    lat: markerGreen.getPosition().lat(),
                    lng: markerGreen.getPosition().lng()
                }, {lat: $(this).data('lat'), lng: $(this).data('lng')}];
                var line = new google.maps.Polyline({
                    path: array,
                    geodesic: true,
                    strokeColor: '#00FF00',
                    strokeOpacity: 1.0,
                    strokeWeight: 2,
                    map: map
                });
                neighbourStations.push(line)
            })
        }

        function clearLines() {
            for (var i = 0; i < neighbourStations.length; i++) {
                neighbourStations[i].setMap(null);
            }
            neighbourStations = [];
        }

        $(function () {
            drawLines();
            $('[name="arriveStationIds"]').on('change', function () {
                clearLines();
                drawLines();
            })
        })
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9D3FKnauqxAM4dYF9BEoiWpifX0ibQso&callback=initMap"
        type="text/javascript"></script>
