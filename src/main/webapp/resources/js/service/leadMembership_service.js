'use strict';
 
angular.module('myApp').factory('LeadMembershipService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:9090/Pras/admin/leadMembership/list?pageNo=1&pageSize=100&sSearach=&sSearchIns=&sSearchPrvdr&sort=&sortdir';
    
    var LEAD_STATUS_REST_SERVICE_URI = 'http://localhost:9090/Pras/leadStatuses?pageNo=1&pageSize=10';
    var LEAD_GENDER_REST_SERVICE_URI = 'http://localhost:9090/Pras/leadGenders?pageNo=1&pageSize=10';
 
    var factory = {
        fetchAllLeads: fetchAllLeads,
        createLead: createLead,
        updateLead:updateLead,
        deleteLead:deleteLead,
        fetchAllLeadGenders : fetchAllLeadGenders,
        fetchAllLeadStatuses :fetchAllLeadStatuses
    };
 
    return factory;
 
    function fetchAllLeads() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Leads');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createLead(lead) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, lead)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Lead');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateLead(lead, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, lead)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Lead');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteLead(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Lead');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function fetchAllLeadStatuses() {
        var deferred = $q.defer();
        $http.get(LEAD_STATUS_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Leads');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllLeadGenders() {
        var deferred = $q.defer();
        $http.get(LEAD_GENDER_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Leads');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);