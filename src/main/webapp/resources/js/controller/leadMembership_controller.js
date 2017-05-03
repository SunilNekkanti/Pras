'use strict';
 
angular.module('myApp').controller('LeadMembershipController', ['$scope', 'LeadMembershipService', function($scope, LeadMembershipService) {
    var self = this;
    self.lead={id:null,firstname:'',lastname:'',gender:'' ,dob:'', medicaidNo:'',medicareNo:''};
    self.leads=[];
    self.statuses = [];
    
    self.genders = [];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllLeads();
    fetchAllLeadStatuses();
    fetchAllLeadGenders();
    
 
    function fetchAllLeads(){
    	LeadMembershipService.fetchAllLeads()
            .then(
            function(d) {
                self.leads = d.data.pagination.list;
                alert(d[0]);
            },
            function(errResponse){
                console.error('Error while fetching Leads');
            }
        );
    }
 
    function createLead(lead){
    	LeadMembershipService.createLead(lead)
            .then(
            fetchAllLeads,
            function(errResponse){
                console.error('Error while creating Lead');
            }
        );
    }
 
    function updateLead(lead, id){
    	LeadMembershipService.updateLead(lead, id)
            .then(
            fetchAllLeads,
            function(errResponse){
                console.error('Error while updating Lead');
            }
        );
    }
 
    function deleteLead(id){
    	LeadMembershipService.deleteLead(id)
            .then(
            fetchAllLeads,
            function(errResponse){
                console.error('Error while deleting Lead');
            }
        );
    }
 
    function fetchAllLeadStatuses(){
    	LeadMembershipService.fetchAllLeadStatuses()
            .then(
            function(d) {
                self.statuses = d;
            },
            function(errResponse){
                console.error('Error while fetching Lead Statuses');
            }
        );
    }
    
    function fetchAllLeadGenders(){
    	LeadMembershipService.fetchAllLeadGenders()
            .then(
            function(d) {
                self.genders = d;
                $scope.genders = d;
                
            },
            function(errResponse){
                console.error('Error while fetching Lead Genders');
            }
        );
    }
    
    function submit() {
        if(self.lead.id===null){
            console.log('Saving New Lead', self.lead);
            createLead(self.lead);
        }else{
            updateLead(self.lead, self.lead.id);
            console.log('Lead updated with id ', self.lead.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.leads.length; i++){
            if(self.leads[i].id === id) {
                self.lead = angular.copy(self.leads[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.lead.id === id) {//clean form if the lead to be deleted is shown there.
            reset();
        }
        deleteLead(id);
    }
 
 
    function reset(){
        self.lead={id:null,firstname:'',lastname:'',gender:'' ,dob:'',status:'',county:'',medicaidNo:'',medicareNo:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);