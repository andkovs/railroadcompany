<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div style="position: relative; padding-top: 100%; background-color: #f0f0f0">
                    <div id="map" style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"></div>
                </div>
            </div>
            <div class="col-md-4">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="<c:if test="${tabpanel==true||tabpanel==null}">active</c:if>">
                        <a href="#train" aria-controls="train" role="tab" data-toggle="tab">Train</a>
                    </li>
                    <li role="presentation" class="<c:if test="${tabpanel==false}">active</c:if>">
                        <a href="#schedule" aria-controls="schedule" role="tab" data-toggle="tab">Schedule</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">

                    <div role="tabpanel" class="tab-pane <c:if test="${tabpanel==true||tabpanel==null}">active</c:if>" id="train">
                        <h3>${train.trainNumber}</h3>
                        <form:form method="POST" action="/train/add" modelAttribute="train">
                            <form:hidden path="trainId"/>
                            <div class="form-group">
                                <form:input path="trainNumber" class="form-control"/>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <form:checkbox path="enabled"/>
                                    is enabled
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a href="/train" class="btn btn-default">Back</a>
                        </form:form>

                        <h3>Wagons</h3>
                        <c:forEach var="w" items="${train.wagons}">
                            <div class="row form-group">
                                <div class="col-md-10 form-control-static">
                                        ${w.wagonTitle} ${w.wagonType}
                                </div>
                                <div class="col-md-2"><a href="/wagon/${w.wagonId}/delete"
                                                         class="btn btn-danger glyphicon glyphicon-trash"></a></div>
                            </div>
                        </c:forEach>


                        <c:if test="${train.trainId!=0}">
                            <form method="POST" action="/wagon/add">
                                <input type="hidden" name="trainId" value="${train.trainId}"/>
                                <div class="row">
                                    <div class="col-md-7">
                                        <select name="wagonTypeId" class="form-control">
                                            <c:forEach var="w" items="${wagonTypes}">
                                                <option value="${w.wagonId}">${w.wagonType}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <input name="wagonTitle" class="form-control"/>
                                    </div>
                                    <div class="col-md-2">
                                        <button type="submit" class="btn btn-success glyphicon glyphicon-plus"></button>
                                    </div>
                                </div>
                            </form>
                        </c:if>

                    </div>
                    <div role="tabpanel" class="tab-pane <c:if test="${tabpanel == false}">active</c:if>" id="schedule">
                        <h3>Schedule</h3>
                        <hr/>
                        <c:forEach var="sch" items="${train.schedules}" varStatus="loop">
                            <div class="row">
                                <div class="col-md-7 form-group">
                                    <p class="form-control-static">${sch.directionByDirectionId.stationByDepStationId.stationTitle}</p>
                                </div>
                                <div class="col-md-5 form-group">
                                    <p class="form-control-static">${sch.departureTime}</p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-7 form-group">
                                    <p class="form-control-static">${sch.directionByDirectionId.stationByArrStationId.stationTitle}</p>
                                </div>
                                <div class="col-md-5 form-group">
                                    <p class="form-control-static">${sch.arriveTime}</p>
                                </div>
                            </div>
                            <c:if test="${loop.last}">
                               <a href="/schedule/${sch.scheduleId}/delete" class="btn btn-danger">Remove</a>
                            </c:if>
                            <hr/>
                        </c:forEach>
                        <form:form method="POST" action="/schedule/add">
                            <input type="hidden" name="trainId" value="${train.trainId}"/>
                            <div class="row">
                                <div class="col-md-7 form-group">
                                    <select id="dep_st" name="depStationId" class="form-control">
                                        <c:if test="${train.schedules.size()==0}">
                                            <option value="">Select</option>
                                            <c:forEach var="st" items="${stations}">
                                                <option value="${st.stationId}">${st.stationTitle}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${train.schedules.size()!=0}">
                                            <option value="${train.schedules[train.schedules.size()-1].directionByDirectionId.stationByArrStationId.stationId}">
                                                    ${train.schedules[train.schedules.size()-1].directionByDirectionId.stationByArrStationId.stationTitle}
                                            </option>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="col-md-5 form-group">
                                    <input class="form-control" type="time" id="depTime" name="departureTime"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-7 form-group">
                                    <select id="arr_st" name="arrStationId" class="form-control">
                                        <option value="">Select</option>
                                        <c:forEach var="st" items="${stations}">
                                            <option value="${st.stationId}">${st.stationTitle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-5 form-group">
                                    <input class="form-control" type="time" id="arrTime" name="arriveTime"/>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script>
    console.log('tabpanel', ${tabpanel});
    //variebles
    var train = [
        <c:forEach var="sch" items="${train.schedules}" varStatus="loop">
        {
            dep: ${sch.directionByDirectionId.stationByDepStationId.stationId},
            arr: ${sch.directionByDirectionId.stationByArrStationId.stationId}
        }${!loop.last ? ',' : ''}
        </c:forEach>
    ];
    var stations = [
        <c:forEach var="st" items="${stations}" varStatus="loop">
        {
            id: ${st.stationId},
            title: '${st.stationTitle}',
            location: {lat: ${st.lat}, lng: ${st.lng}}
        }${!loop.last ? ',' : ''}
        </c:forEach>
    ];
    var directions = [
        <c:forEach var="d" items="${directions}" varStatus="loop">
        {
            dep: ${d.depStationId},
            arr: ${d.arrStationId},
            depLocation: {lat: ${d.stationByDepStationId.lat}, lng: ${d.stationByDepStationId.lng}},
            arrLocation: {lat: ${d.stationByArrStationId.lat}, lng: ${d.stationByArrStationId.lng}}
        }${!loop.last ? ',' : ''}
        </c:forEach>
    ];
    function initMap() {
        var center = {lat: ${stations[1].lat}, lng: ${stations[1].lng}};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 7,
            center: center
        });
        for (var i = 0; i < stations.length; i++) {
            var s = stations[i];
            var marker = new google.maps.Marker({
                position: s.location,
                map: map,
                icon: 'http://maps.google.com/mapfiles/kml/paddle/red-circle.png',
                title: s.title
            });
        }
        for (var i = 0; i < directions.length; i++) {
            var line = new google.maps.Polyline({
                path: [directions[i].depLocation, directions[i].arrLocation],
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 1,
                map: map
            });
            for (var j = 0; j < train.length; j++) {
                var sm = directions[i].arr == train[j].arr && directions[i].dep == train[j].dep;
                if (sm) {
                    line.setOptions({strokeColor: '#0000FF', strokeWeight: 4});
                }
            }
        }
    }
    $(function () {
        var depStationSelect = $('#dep_st');
        var arrStationSelect = $('#arr_st');

        function filterArrStations() {
            var depStId = depStationSelect.val();
            var neighbours = $.map(directions, function (el, i) {
                var r = null;
                if (el.dep == depStId) {
                    r = el.arr;
                }
                return r;
            });
            console.log('neighbours', neighbours);
            arrStationSelect.val('')
                    .find('option')
                    .each(function () {
                        var optionVal = parseInt($(this).attr('value'), 0);
                        var isNeighbour = neighbours.indexOf(optionVal) >= 0;
                        if (isNeighbour || optionVal == NaN) {
                            $(this).show();
                        } else {
                            $(this).hide();
                        }
                    })
        };
        filterArrStations();
        depStationSelect.on('change', filterArrStations);
    });
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9D3FKnauqxAM4dYF9BEoiWpifX0ibQso&callback=initMap"
        type="text/javascript"></script>
