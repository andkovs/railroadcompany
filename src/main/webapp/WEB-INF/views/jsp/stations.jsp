<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div style="position: relative; padding-top: 100%; background-color: #f0f0f0">
                    <div id="map" style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"></div>
                </div>
            </div>
            <div class="col-md-4">
                <table id="stationTable" class="table table-hover table-striped">
                    <thead>
                    <tr data-toggle="collapse" data-target="#accordion" class="clickable">
                        <th>Stations</th>
                        <th style="width: 3%"></th>
                        <th style="width: 3%"></th>
                    </tr>
                    </thead>
                    <c:if test="${not empty stations}">
                        <tbody>
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
                                    <a class="js-delete-confirm text-danger glyphicon glyphicon-trash"
                                       href="<c:url value="/station/${station.stationId}/delete"/>">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>

                </table>
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="paging"></ul>
                </nav>
                <a href="/station/0" class="btn btn-success">New station</a>
            </div>
        </div>
    </div>

</main>

<script>
    $(function () {
        $('.js-delete-confirm').click(function (e) {
            if (!confirm('Delete this station?')) {
                e.preventDefault();
            }
        });

        var table = $('#stationTable');
        var rows = $('tbody tr', table);
        var paging = $('#paging');
        var count = 10;
        var pages = Math.floor(rows.length / count) + 1;
        if (pages == 1) {
            paging.hide();
        }
        else {
            for (var i = 1; i <= pages; i++) {
                paging.append('<li><a href="#">' + i + '</a></li>');
                paging.find('li:first-child').addClass('active');
            }
        }
        function updateRows(current) {
            rows.each(function (i, el) {
                var min = current * count;
                var max = min + count;
                if (i >= min && i < max) {
                    $(el).show();
                }
                else {
                    $(el).hide();
                }
            })
        }

        updateRows(0);
        var pagingLinks = paging.find('a');

        pagingLinks.on('click', function (e) {
            console.log($(this));
            e.preventDefault();
            var p = pagingLinks.index($(this));
            console.log(p);
            updateRows(p);
            paging.find('.active').removeClass('active');
            $(this).parent().addClass('active');
        })

    });

    //    $(document).ready(function () {
    //        $('#stationTable').DataTable({
    //            "ordering": false,
    //            "info":     true
    //        });
    //    });
    //    $(function () {
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
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9D3FKnauqxAM4dYF9BEoiWpifX0ibQso&callback=initMap"
        type="text/javascript"></script>
