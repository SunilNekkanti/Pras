package com.pfchoice.core.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity(name = "lead_membership_hospitalization_details")
public class LeadMembershipHospitalizationDetails extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "lead_mbr_hos_details_id", nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lead_mbr_hos_id", referencedColumnName = "lead_mbr_hos_id")
	private LeadMembershipHospitalization leadMbrHospitalization;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "att_phy_id", referencedColumnName = "att_phy_id")
	private AttPhysician attPhysician;

	@Expose
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "room_type_code", referencedColumnName = "code")
	private PlaceOfService roomType;

	@Expose
	@Column(name = "adm_dx")
	private String admDx;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "exp_dc_dt")
	protected Date expDisDate;

	@Expose
	@Column(name = "auth_days")
	private Integer authDays;

	@Expose
	@Column(name = "cm_pri_user")
	private String cmPriUser;

	@Expose
	@Column(name = "disease_cohort")
	private String diseaseCohort;

	@Expose
	@Column(name = "comorbidities")
	private String comorbidities;

	@Expose
	@Column(name = "file_id")
	private Integer fileId;

	/**
	 * 
	 */
	public LeadMembershipHospitalizationDetails() {
		super();
	}

	/**
	 * @param id
	 */
	public LeadMembershipHospitalizationDetails(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the leadMbrHospitalization
	 */
	public LeadMembershipHospitalization getLeadMbrHospitalization() {
		return leadMbrHospitalization;
	}

	/**
	 * @param mbrHospitalization
	 *            the mbrHospitalization to set
	 */
	public void setLeadMbrHospitalization(LeadMembershipHospitalization leadMbrHospitalization) {
		this.leadMbrHospitalization = leadMbrHospitalization;
	}

	/**
	 * @return the attPhysician
	 */
	public AttPhysician getAttPhysician() {
		return attPhysician;
	}

	/**
	 * @param attPhysician
	 *            the attPhysician to set
	 */
	public void setAttPhysician(AttPhysician attPhysician) {
		this.attPhysician = attPhysician;
	}

	/**
	 * @return the roomType
	 */
	public PlaceOfService getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(PlaceOfService roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the admDx
	 */
	public String getAdmDx() {
		return admDx;
	}

	/**
	 * @param admDx
	 *            the admDx to set
	 */
	public void setAdmDx(String admDx) {
		this.admDx = admDx;
	}

	/**
	 * @return the expDisDate
	 */
	public Date getExpDisDate() {
		return expDisDate;
	}

	/**
	 * @param expDisDate
	 *            the expDisDate to set
	 */
	public void setExpDisDate(Date expDisDate) {
		this.expDisDate = expDisDate;
	}

	/**
	 * @return the authDays
	 */
	public Integer getAuthDays() {
		return authDays;
	}

	/**
	 * @param authDays
	 *            the authDays to set
	 */
	public void setAuthDays(Integer authDays) {
		this.authDays = authDays;
	}

	/**
	 * @return the cmPriUser
	 */
	public String getCmPriUser() {
		return cmPriUser;
	}

	/**
	 * @param cmPriUser
	 *            the cmPriUser to set
	 */
	public void setCmPriUser(String cmPriUser) {
		this.cmPriUser = cmPriUser;
	}

	/**
	 * @return the diseaseCohort
	 */
	public String getDiseaseCohort() {
		return diseaseCohort;
	}

	/**
	 * @param diseaseCohort
	 *            the diseaseCohort to set
	 */
	public void setDiseaseCohort(String diseaseCohort) {
		this.diseaseCohort = diseaseCohort;
	}

	/**
	 * @return the comorbidities
	 */
	public String getComorbidities() {
		return comorbidities;
	}

	/**
	 * @param comorbidities
	 *            the comorbidities to set
	 */
	public void setComorbidities(String comorbidities) {
		this.comorbidities = comorbidities;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(final Integer fileId) {
		this.fileId = fileId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof LeadMembershipHospitalizationDetails)) {
			return false;
		}
		LeadMembershipHospitalizationDetails other = (LeadMembershipHospitalizationDetails) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		 StringBuilder result = new StringBuilder();
		  

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append("\n");

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append("\n");
		  }
		  result.append("}");

		  return result.toString();
	}

}
