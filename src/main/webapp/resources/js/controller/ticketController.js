'use strict';

angular.module('myApp').controller('TicketController', ['$scope', 'ticketService',
    function ($scope, ticketService) {

    $scope.preview = function(id) {
        var getTrain = ticketService.getTicketsByTrainId(id);
        getTrain.then(function(response){
            $scope.previewData = response.data;
            console.log('in ctrl', $scope.previewData);
            $('#ticketPreview').modal();
        });
    };

    $('#ticketPreview').on('hidden.bs.modal', function () {
        $scope.previewData = {};
    })
}]);
