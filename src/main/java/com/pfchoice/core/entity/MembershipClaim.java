package com.pfchoice.core.entity;

import java.io.Serializable;
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
@Entity(name = "membership_claims")
public class MembershipClaim extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mbr_claim_id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "claim_id_number")
	private String claimNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mbr_id", referencedColumnName = "mbr_id")
	private Membership mbr;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", referencedColumnName = "prvdr_id")
	private Provider prvdr;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ins_id", referencedColumnName = "insurance_id")
	private Insurance ins;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "activity_date")
	protected Date activityDate;

	@Expose
	@Column(name = "activity_month")
	private Integer activityMonth;
	
	@Expose
	@Column(name = "claim_type")
	private String claimType;

	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "location_id", referencedColumnName = "code")
	private PlaceOfService roomType;
	
/*	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "facility_type_code", referencedColumnName = "code")
	private FacilityType facilityType;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "bill_type_code", referencedColumnName = "code")
	private BillType billType;
	*/
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "frequency_type_code", referencedColumnName = "code")
	private FrequencyType frequencyType;
	
	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "claim_start_date")
	protected Date claimStartDate;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "claim_end_date")
	protected Date claimEndDate;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "paid_date")
	protected Date paidDate;

	@Expose
	@Column(name = "bill_type")
	private String billType;
	
	@Expose
	@Column(name = "ndc")
	private String ndc;

	@Expose
	@Column(name = "mony")
	private String mony;

	@Expose
	@Column(name = "drug_label_name")
	private String drugLabelName;

	@Expose
	@Column(name = "drug_version")
	private String drugVersion;

	@Expose
	@Column(name = "dischargestatus")
	private String dischargeStatus;

	@Expose
	@Column(name = "memEnrollId")
	private String MemEnrollId;

	@Expose
	@Column(name = "diagnoses")
	private String diagnosis;

	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS1", referencedColumnName = "code")
	private ICDMeasure diagnosis1;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS2", referencedColumnName = "code")
	private ICDMeasure diagnosis2;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS3", referencedColumnName = "code")
	private ICDMeasure diagnosis3;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS4", referencedColumnName = "code")
	private ICDMeasure diagnosis4;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS5", referencedColumnName = "code")
	private ICDMeasure diagnosis5;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS6", referencedColumnName = "code")
	private ICDMeasure diagnosis6;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS7", referencedColumnName = "code")
	private ICDMeasure diagnosis7;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "DIAGNOSIS8", referencedColumnName = "code")
	private ICDMeasure diagnosis8;
	
	@Expose
	@Column(name = "ICDPROCCODE1")
	private String icdProcCode1;
	
	@Expose
	@Column(name = "ICDPROCCODE2")
	private String icdProcCode2;
	
	@Expose
	@Column(name = "ICDPROCCODE3")
	private String icdProcCode3;
	
	@Expose
	@Column(name = "ICDPROCCODE4")
	private String icdProcCode4;
	
	@Expose
	@Column(name = "ICDPROCCODE5")
	private String icdProcCode5;
	
	@Expose
	@Column(name = "product_label")
	private String productLabel;
	
	@Expose
	@Column(name = "product_lvl1")
	private String productLvl1;
	
	@Expose
	@Column(name = "product_lvl2")
	private String productLvl2;
	
	@Expose
	@Column(name = "product_lvl3")
	private String productLvl3;
	
	@Expose
	@Column(name = "product_lvl4")
	private String productLvl4;
	
	@Expose
	@Column(name = "product_lvl5")
	private String productLvl5;
	
	@Expose
	@Column(name = "product_lvl6")
	private String productLvl6;
	
	@Expose
	@Column(name = "product_lvl7")
	private String productLvl7;
	
	@Expose
	@Column(name = "market_lvl1")
	private String marketLvl1;
	
	@Expose
	@Column(name = "market_lvl2")
	private String marketLvl2;
	
	@Expose
	@Column(name = "market_lvl3")
	private String marketLvl3;
	
	@Expose
	@Column(name = "market_lvl4")
	private String marketLvl4;
	
	@Expose
	@Column(name = "market_lvl5")
	private String marketLvl5;
	
	@Expose
	@Column(name = "market_lvl6")
	private String marketLvl6;
	
	@Expose
	@Column(name = "market_lvl7")
	private String marketLvl7;
	
	@Expose
	@Column(name = "market_lvl8")
	private String marketLvl8;
	
	@Expose
	@Column(name = "risk_recon_cos_des")
	private String riskReconCosDes;
	
	@Expose
	@Column(name = "tin")
	private String tin;
	
	@Expose
	@Column(name = "dx_type_cd")
	private String dxTypeCode;
	
	@Expose
	@Column(name = "proc_type_cd")
	private String procTypeCode;
	
	@Expose
	@Column(name = "file_id")
	private Integer fileId;
	
/*	@Expose
	@OneToMany(mappedBy = "mbrClaim", fetch = FetchType.LAZY)
	private List<MembershipClaimDetails> mbrClaimDetailsList;
*/
	/**
	 * 
	 */
	public MembershipClaim() {
		super();
	}

	/**
	 * @param id
	 */
	public MembershipClaim(final Integer id) {
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
	 * @return the claimNumber
	 */
	public String getClaimNumber() {
		return claimNumber;
	}

	/**
	 * @param claimNumber the claimNumber to set
	 */
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	/**
	 * @return the mbr
	 */
	public Membership getMbr() {
		return mbr;
	}

	/**
	 * @param mbr the mbr to set
	 */
	public void setMbr(Membership mbr) {
		this.mbr = mbr;
	}

	/**
	 * @return the prvdr
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr the prvdr to set
	 */
	public void setPrvdr(Provider prvdr) {
		this.prvdr = prvdr;
	}

	/**
	 * @return the ins
	 */
	public Insurance getIns() {
		return ins;
	}

	/**
	 * @param ins the ins to set
	 */
	public void setIns(Insurance ins) {
		this.ins = ins;
	}

	/**
	 * @return the activityDate
	 */
	public Date getActivityDate() {
		return activityDate;
	}

	/**
	 * @param activityDate the activityDate to set
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	/**
	 * @return the activityMonth
	 */
	public Integer getActivityMonth() {
		return activityMonth;
	}

	/**
	 * @param activityMonth the activityMonth to set
	 */
	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	/**
	 * @return the claimType
	 */
	public String getClaimType() {
		return claimType;
	}

	/**
	 * @param claimType the claimType to set
	 */
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	/**
	 * @return the roomType
	 */
	public PlaceOfService getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(PlaceOfService roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the frequencyType
	 */
	public FrequencyType getFrequencyType() {
		return frequencyType;
	}

	/**
	 * @param frequencyType the frequencyType to set
	 */
	public void setFrequencyType(FrequencyType frequencyType) {
		this.frequencyType = frequencyType;
	}

	/**
	 * @return the claimStartDate
	 */
	public Date getClaimStartDate() {
		return claimStartDate;
	}

	/**
	 * @param claimStartDate the claimStartDate to set
	 */
	public void setClaimStartDate(Date claimStartDate) {
		this.claimStartDate = claimStartDate;
	}

	/**
	 * @return the claimEndDate
	 */
	public Date getClaimEndDate() {
		return claimEndDate;
	}

	/**
	 * @param claimEndDate the claimEndDate to set
	 */
	public void setClaimEndDate(Date claimEndDate) {
		this.claimEndDate = claimEndDate;
	}

	/**
	 * @return the paidDate
	 */
	public Date getPaidDate() {
		return paidDate;
	}

	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	/**
	 * @return the billType
	 */
	public String getBillType() {
		return billType;
	}

	/**
	 * @param billType the billType to set
	 */
	public void setBillType(String billType) {
		this.billType = billType;
	}

	/**
	 * @return the ndc
	 */
	public String getNdc() {
		return ndc;
	}

	/**
	 * @param ndc the ndc to set
	 */
	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	/**
	 * @return the mony
	 */
	public String getMony() {
		return mony;
	}

	/**
	 * @param mony the mony to set
	 */
	public void setMony(String mony) {
		this.mony = mony;
	}

	/**
	 * @return the drugLabelName
	 */
	public String getDrugLabelName() {
		return drugLabelName;
	}

	/**
	 * @param drugLabelName the drugLabelName to set
	 */
	public void setDrugLabelName(String drugLabelName) {
		this.drugLabelName = drugLabelName;
	}

	/**
	 * @return the drugVersion
	 */
	public String getDrugVersion() {
		return drugVersion;
	}

	/**
	 * @param drugVersion the drugVersion to set
	 */
	public void setDrugVersion(String drugVersion) {
		this.drugVersion = drugVersion;
	}

	/**
	 * @return the dischargeStatus
	 */
	public String getDischargeStatus() {
		return dischargeStatus;
	}

	/**
	 * @param dischargeStatus the dischargeStatus to set
	 */
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	/**
	 * @return the memEnrollId
	 */
	public String getMemEnrollId() {
		return MemEnrollId;
	}

	/**
	 * @param memEnrollId the memEnrollId to set
	 */
	public void setMemEnrollId(String memEnrollId) {
		MemEnrollId = memEnrollId;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the diagnosis1
	 */
	public ICDMeasure getDiagnosis1() {
		return diagnosis1;
	}

	/**
	 * @param diagnosis1 the diagnosis1 to set
	 */
	public void setDiagnosis1(ICDMeasure diagnosis1) {
		this.diagnosis1 = diagnosis1;
	}

	/**
	 * @return the diagnosis2
	 */
	public ICDMeasure getDiagnosis2() {
		return diagnosis2;
	}

	/**
	 * @param diagnosis2 the diagnosis2 to set
	 */
	public void setDiagnosis2(ICDMeasure diagnosis2) {
		this.diagnosis2 = diagnosis2;
	}

	/**
	 * @return the diagnosis3
	 */
	public ICDMeasure getDiagnosis3() {
		return diagnosis3;
	}

	/**
	 * @param diagnosis3 the diagnosis3 to set
	 */
	public void setDiagnosis3(ICDMeasure diagnosis3) {
		this.diagnosis3 = diagnosis3;
	}

	/**
	 * @return the diagnosis4
	 */
	public ICDMeasure getDiagnosis4() {
		return diagnosis4;
	}

	/**
	 * @param diagnosis4 the diagnosis4 to set
	 */
	public void setDiagnosis4(ICDMeasure diagnosis4) {
		this.diagnosis4 = diagnosis4;
	}

	/**
	 * @return the diagnosis5
	 */
	public ICDMeasure getDiagnosis5() {
		return diagnosis5;
	}

	/**
	 * @param diagnosis5 the diagnosis5 to set
	 */
	public void setDiagnosis5(ICDMeasure diagnosis5) {
		this.diagnosis5 = diagnosis5;
	}

	/**
	 * @return the diagnosis6
	 */
	public ICDMeasure getDiagnosis6() {
		return diagnosis6;
	}

	/**
	 * @param diagnosis6 the diagnosis6 to set
	 */
	public void setDiagnosis6(ICDMeasure diagnosis6) {
		this.diagnosis6 = diagnosis6;
	}

	/**
	 * @return the diagnosis7
	 */
	public ICDMeasure getDiagnosis7() {
		return diagnosis7;
	}

	/**
	 * @param diagnosis7 the diagnosis7 to set
	 */
	public void setDiagnosis7(ICDMeasure diagnosis7) {
		this.diagnosis7 = diagnosis7;
	}

	/**
	 * @return the diagnosis8
	 */
	public ICDMeasure getDiagnosis8() {
		return diagnosis8;
	}

	/**
	 * @param diagnosis8 the diagnosis8 to set
	 */
	public void setDiagnosis8(ICDMeasure diagnosis8) {
		this.diagnosis8 = diagnosis8;
	}

	/**
	 * @return the icdProcCode1
	 */
	public String getIcdProcCode1() {
		return icdProcCode1;
	}

	/**
	 * @param icdProcCode1 the icdProcCode1 to set
	 */
	public void setIcdProcCode1(String icdProcCode1) {
		this.icdProcCode1 = icdProcCode1;
	}

	/**
	 * @return the icdProcCode2
	 */
	public String getIcdProcCode2() {
		return icdProcCode2;
	}

	/**
	 * @param icdProcCode2 the icdProcCode2 to set
	 */
	public void setIcdProcCode2(String icdProcCode2) {
		this.icdProcCode2 = icdProcCode2;
	}

	/**
	 * @return the icdProcCode3
	 */
	public String getIcdProcCode3() {
		return icdProcCode3;
	}

	/**
	 * @param icdProcCode3 the icdProcCode3 to set
	 */
	public void setIcdProcCode3(String icdProcCode3) {
		this.icdProcCode3 = icdProcCode3;
	}

	/**
	 * @return the icdProcCode4
	 */
	public String getIcdProcCode4() {
		return icdProcCode4;
	}

	/**
	 * @param icdProcCode4 the icdProcCode4 to set
	 */
	public void setIcdProcCode4(String icdProcCode4) {
		this.icdProcCode4 = icdProcCode4;
	}

	/**
	 * @return the icdProcCode5
	 */
	public String getIcdProcCode5() {
		return icdProcCode5;
	}

	/**
	 * @param icdProcCode5 the icdProcCode5 to set
	 */
	public void setIcdProcCode5(String icdProcCode5) {
		this.icdProcCode5 = icdProcCode5;
	}

	/**
	 * @return the productLabel
	 */
	public String getProductLabel() {
		return productLabel;
	}

	/**
	 * @param productLabel the productLabel to set
	 */
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	/**
	 * @return the productLvl1
	 */
	public String getProductLvl1() {
		return productLvl1;
	}

	/**
	 * @param productLvl1 the productLvl1 to set
	 */
	public void setProductLvl1(String productLvl1) {
		this.productLvl1 = productLvl1;
	}

	/**
	 * @return the productLvl2
	 */
	public String getProductLvl2() {
		return productLvl2;
	}

	/**
	 * @param productLvl2 the productLvl2 to set
	 */
	public void setProductLvl2(String productLvl2) {
		this.productLvl2 = productLvl2;
	}

	/**
	 * @return the productLvl3
	 */
	public String getProductLvl3() {
		return productLvl3;
	}

	/**
	 * @param productLvl3 the productLvl3 to set
	 */
	public void setProductLvl3(String productLvl3) {
		this.productLvl3 = productLvl3;
	}

	/**
	 * @return the productLvl4
	 */
	public String getProductLvl4() {
		return productLvl4;
	}

	/**
	 * @param productLvl4 the productLvl4 to set
	 */
	public void setProductLvl4(String productLvl4) {
		this.productLvl4 = productLvl4;
	}

	/**
	 * @return the productLvl5
	 */
	public String getProductLvl5() {
		return productLvl5;
	}

	/**
	 * @param productLvl5 the productLvl5 to set
	 */
	public void setProductLvl5(String productLvl5) {
		this.productLvl5 = productLvl5;
	}

	/**
	 * @return the productLvl6
	 */
	public String getProductLvl6() {
		return productLvl6;
	}

	/**
	 * @param productLvl6 the productLvl6 to set
	 */
	public void setProductLvl6(String productLvl6) {
		this.productLvl6 = productLvl6;
	}

	/**
	 * @return the productLvl7
	 */
	public String getProductLvl7() {
		return productLvl7;
	}

	/**
	 * @param productLvl7 the productLvl7 to set
	 */
	public void setProductLvl7(String productLvl7) {
		this.productLvl7 = productLvl7;
	}

	/**
	 * @return the marketLvl1
	 */
	public String getMarketLvl1() {
		return marketLvl1;
	}

	/**
	 * @param marketLvl1 the marketLvl1 to set
	 */
	public void setMarketLvl1(String marketLvl1) {
		this.marketLvl1 = marketLvl1;
	}

	/**
	 * @return the marketLvl2
	 */
	public String getMarketLvl2() {
		return marketLvl2;
	}

	/**
	 * @param marketLvl2 the marketLvl2 to set
	 */
	public void setMarketLvl2(String marketLvl2) {
		this.marketLvl2 = marketLvl2;
	}

	/**
	 * @return the marketLvl3
	 */
	public String getMarketLvl3() {
		return marketLvl3;
	}

	/**
	 * @param marketLvl3 the marketLvl3 to set
	 */
	public void setMarketLvl3(String marketLvl3) {
		this.marketLvl3 = marketLvl3;
	}

	/**
	 * @return the marketLvl4
	 */
	public String getMarketLvl4() {
		return marketLvl4;
	}

	/**
	 * @param marketLvl4 the marketLvl4 to set
	 */
	public void setMarketLvl4(String marketLvl4) {
		this.marketLvl4 = marketLvl4;
	}

	/**
	 * @return the marketLvl5
	 */
	public String getMarketLvl5() {
		return marketLvl5;
	}

	/**
	 * @param marketLvl5 the marketLvl5 to set
	 */
	public void setMarketLvl5(String marketLvl5) {
		this.marketLvl5 = marketLvl5;
	}

	/**
	 * @return the marketLvl6
	 */
	public String getMarketLvl6() {
		return marketLvl6;
	}

	/**
	 * @param marketLvl6 the marketLvl6 to set
	 */
	public void setMarketLvl6(String marketLvl6) {
		this.marketLvl6 = marketLvl6;
	}

	/**
	 * @return the marketLvl7
	 */
	public String getMarketLvl7() {
		return marketLvl7;
	}

	/**
	 * @param marketLvl7 the marketLvl7 to set
	 */
	public void setMarketLvl7(String marketLvl7) {
		this.marketLvl7 = marketLvl7;
	}

	/**
	 * @return the marketLvl8
	 */
	public String getMarketLvl8() {
		return marketLvl8;
	}

	/**
	 * @param marketLvl8 the marketLvl8 to set
	 */
	public void setMarketLvl8(String marketLvl8) {
		this.marketLvl8 = marketLvl8;
	}

	/**
	 * @return the riskReconCosDes
	 */
	public String getRiskReconCosDes() {
		return riskReconCosDes;
	}

	/**
	 * @param riskReconCosDes the riskReconCosDes to set
	 */
	public void setRiskReconCosDes(String riskReconCosDes) {
		this.riskReconCosDes = riskReconCosDes;
	}

	/**
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}

	/**
	 * @param tin the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * @return the dxTypeCode
	 */
	public String getDxTypeCode() {
		return dxTypeCode;
	}

	/**
	 * @param dxTypeCode the dxTypeCode to set
	 */
	public void setDxTypeCode(String dxTypeCode) {
		this.dxTypeCode = dxTypeCode;
	}

	/**
	 * @return the procTypeCode
	 */
	public String getProcTypeCode() {
		return procTypeCode;
	}

	/**
	 * @param procTypeCode the procTypeCode to set
	 */
	public void setProcTypeCode(String procTypeCode) {
		this.procTypeCode = procTypeCode;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
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
		if (!(object instanceof MembershipClaim)) {
			return false;
		}
		MembershipClaim other = (MembershipClaim) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.MembershipHospitalization[ id=" + id + " ]";
	}

}
