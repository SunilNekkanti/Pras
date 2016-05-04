package com.pfchoice.common.util;

/**
 * @author sarath
 *
 */
public enum TileDefinitions {

	HELLO ("hello" ),
	HOME ("home" ),
	INDEX ("index" ),
	LOGINFORM ("loginForm" ),
	MEMBERSHIP ("membership" ),
	MEMBERSHIPLIST ("membershipList" ),
	MEMBERSHIPEDIT ("membershipEdit" ),
	MEMBERSHIPEDITSUCCESS ("membershipEditSuccess" ),
	MEMBERSHIPDETAILS ("membershipDetails" ),
	MEMBERSHIPDISPLAY ("membershipDisplay" ),
	MEMBERSHIPDETAILSLIST ("membershipDetailsList" ),
	MEMBERSHIPDETAILSDISPLAY ("membershipDetailsDisplay" ),
	MEMBERSHIPDETAILSEDITSUCCESS ("membershipDetailsEditSuccess" ),
	MEMBERSHIPDETAILSEDIT ("membershipDetailsEdit" ),
	PROVIDERLIST ("providerList" ),
	PROVIDER ("provider" ),
	PROVIDERNEW ("providerNew" ),
	PROVIDEREDIT ("providerEdit" ),
	PROVIDERNEWSUCCESS ("providerNewSuccess" ),
	PROVIDERDETAILS ("providerDetails" ),
	PROVIDEREDITSUCCESS ("providerEditSuccess" ),
	PROVIDERADDSUCCESS ("providerAddSuccess" ),
	PROVIDERCONTRACTLIST ("providerContractList" ),
	PROVIDERCONTRACTEDIT ("providerContractEdit" ),
	PROVIDERCONTRACTEDITSUCCESS ("providerContractEditSuccess" ),
	PROVIDERCONTRACTDISPLAY ("providerContractDisplay" ),
	INSURANCELIST ("insuranceList" ),
	INSURANCE ("insurance" ),
	INSURANCENEW ("insuranceNew" ),
	INSURANCENEWSUCCESS ("insuranceNewSuccess" ),
	INSURANCEEDIT ("insuranceEdit" ),
	INSURANCEDETAILS ("insuranceDetails" ),
	INSURANCEEDITSUCCESS ("insuranceEditSuccess" ),
	INSURANCEDELETESUCCESS ("insuranceDeleteSuccess" ),
	INSURANCECONTRACTLIST ("insuranceContractList" ),
	INSURANCECONTRACTEDIT ("insuranceContractEdit" ),
	INSURANCECONTRACTEDITSUCCESS ("insuranceContractEditSuccess" ),
	INSURANCECONTRACTDISPLAY ("insuranceContractDisplay" ),
	MEMBERSHIPTABMENU ("membershipTabMenu" ),
	MEMBERSHIPCONTACTLIST ("membershipContactList" ),
	PROVIDERCONTACTLIST ("providerContactList" ),
	INSURANCECONTACTLIST ("insuranceContactList" ),
	CONTACTLIST ("contactList" ),
	CONTACTEDIT ("contactEdit" ),
	CONTACTEDITSUCCESS ("contactEditSuccess" ),
	CONTACTDISPLAY ("contactDisplay" ),
	MBRLIST ("mbrList" ),
	HEDISMEASURELIST ("hedisMeasureList" ),
	HEDISMEASURENEW ("hedisMeasureNew" ),
	HEDISMEASUREEDIT ("hedisMeasureEdit" ),
	HEDISMEASUREEDITSUCCESS ("hedisMeasureEditSuccess" ),
	HEDISMEASUREDISPLAY ("hedisMeasureDisplay" ),
	CPTMEASURELIST ("cptMeasureList" ),
	CPTMEASUREEDIT ("cptMeasureEdit" ),
	CPTMEASURENEW ("cptMeasureNew" ),
	CPTMEASUREEDITSUCCESS ("cptMeasureEditSuccess" ),
	CPTMEASUREDISPLAY ("cptMeasureDisplay" ),
	ICDMEASURELIST ("icdMeasureList" ),
	ICDMEASURENEW ("icdMeasureNew" ),
	ICDMEASUREEDIT ("icdMeasureEdit" ),
	ICDMEASUREEDITSUCCESS ("icdMeasureEditSuccess" ),
	ICDMEASUREDISPLAY ("icdMeasureDisplay" ),
	HEDISMEASURERULELIST ("hedisMeasureRuleList" ),
	HEDISMEASURERULENEW ("hedisMeasureRuleNew" ),
	HEDISMEASURERULEEDIT ("hedisMeasureRuleEdit" ),
	HEDISMEASURERULEEDITAJAX ("hedisMeasureRuleEditAjax" ),
	HEDISMEASURERULEEDITSUCCESS ("hedisMeasureRuleEditSuccess" ),
	HEDISMEASURERULEDISPLAY ("hedisMeasureRuleDisplay" ),
	INSURANCECONTACTEDIT ("insuranceContactEdit" ),
	INSURANCECONTACTEDITSUCCESS ("insuranceContactEditSuccess" ),
	INSURANCECONTACTDISPLAY ("insuranceContactDisplay" ),
	PROVIDERCONTACTEDIT ("providerContactEdit" ),
	PROVIDERCONTACTEDITSUCCESS ("providerContactEditSuccess" ),
	PROVIDERCONTACTDISPLAY ("providerContactDisplay" ),
	MEMBERSHIPCONTACTEDIT ("membershipContactEdit" ),
	MEMBERSHIPCONTACTEDITSUCCESS ("membershipContactEditSuccess" ),
	MEMBERSHIPCONTACTDISPLAY ("membershipContactDisplay" ),
	MEMBERSHIPPROVIDEREDIT ("membershipProviderEdit" ),
	MEMBERSHIPPROVIDERLIST ("membershipProviderList" ),
	MEMBERSHIPHEDISMEASURE ("membershipHedisMeasure" ),
	MEMBERSHIPCOMPLETEDETAILS ("membershipCompleteDetails" ),
	USERLIST ("userList" ),
	USEREDIT ("userEdit" ),
	USEREDITSUCCESS ("userEditSuccess" ),
	USERNEW ("userNew" ),
	AD403 ("ad403" ),
	HEDISMEMBERSHIPLIST ("hedisMembershipList" ),
	HEDISMEMBERSHIPLIST2 ("hedisMembershipList2" ),
	PLANTYPELIST ("planTypeList" ),
	PLANTYPENEW ("planTypeNew" ),
	PLANTYPEEDIT ("planTypeEdit" ),
	FILETYPELIST ("fileTypeList" ),
	FILETYPENEW ("fileTypeNew" ),
	FILETYPEEDIT ("fileTypeEdit" ),
	HEDISMEASUREGROUPLIST ("hedisMeasureGroupList" ),
	HEDISMEASUREGROUPNEW ("hedisMeasureGroupNew" ),
	HEDISMEASUREGROUPEDIT ("hedisMeasureGroupEdit" );

	
    private final String text;

    /**
     * @param text
     */
    private TileDefinitions(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}