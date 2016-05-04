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
@Entity(name = "membership_hospitalization")
public class MembershipHospitalization extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mbr_hos_id", nullable = false)
	private Integer id;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hos_id", referencedColumnName = "hos_id")
	private Hospital hospital;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mbr_id", referencedColumnName = "mbr_id")
	private Membership mbr;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", referencedColumnName = "prvdr_id")
	private Provider prvdr;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ins_id", referencedColumnName = "insurance_id")
	private Insurance ins;
	
	@Expose
	@Column(name = "report")
	private String report;
	
	@Expose
	@Column(name = "plan_desc")
	private String planDesc;
	
	@Expose
	@Column(name = "authnum")
	private Integer authNum;
	
	@Expose
	@Column(name = "prior_admits")
	private Integer priorAdmits;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "admit_date")
	protected Date admitDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exp_dc_date")
	protected Date expDisDate;
	
	@Expose
	@Column(name = "file_id")
	private Integer fileId;


	/**
	 * 
	 */
	public MembershipHospitalization() {
		super();
	}

	/**
	 * @param id
	 */
	public MembershipHospitalization(final Integer id) {
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
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * @return the planDesc
	 */
	public String getPlanDesc() {
		return planDesc;
	}

	/**
	 * @param planDesc the planDesc to set
	 */
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	/**
	 * @return the authNum
	 */
	public Integer getAuthNum() {
		return authNum;
	}

	/**
	 * @param authNum the authNum to set
	 */
	public void setAuthNum(Integer authNum) {
		this.authNum = authNum;
	}

	/**
	 * @return the priorAdmits
	 */
	public Integer getPriorAdmits() {
		return priorAdmits;
	}

	/**
	 * @param priorAdmits the priorAdmits to set
	 */
	public void setPriorAdmits(Integer priorAdmits) {
		this.priorAdmits = priorAdmits;
	}

	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}

	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	/**
	 * @return the expDisDate
	 */
	public Date getExpDisDate() {
		return expDisDate;
	}

	/**
	 * @param expDisDate the expDisDate to set
	 */
	public void setExpDisDate(Date expDisDate) {
		this.expDisDate = expDisDate;
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
		if (!(object instanceof MembershipHospitalization)) {
			return false;
		}
		MembershipHospitalization other = (MembershipHospitalization) object;
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