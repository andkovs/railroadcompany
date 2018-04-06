'use strict';

angular.module('myApp').service('ticketService', ['$http', function ($http) {

    // Get tickets
    this.getTicketsByTrainId = function (id) {
        return $http.get('/rest/ticket/train/'+id);
    };
}]);