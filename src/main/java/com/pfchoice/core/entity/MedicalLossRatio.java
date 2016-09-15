package com.pfchoice.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "medical_loss_ratio")
public class MedicalLossRatio extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "medical_loss_ratio_id", nullable = false)
	private Integer id;

	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ins_id", nullable = false, referencedColumnName = "insurance_id")
	private Insurance insId;

	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prvdr_id", nullable = false, referencedColumnName = "prvdr_id")
	private Provider prvdr;
	
	@Expose
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "report_generation_date")
	private Date reportGenDate;

	@Expose
	@Column(name = "activity_Month")
	private Integer activityMonth;
	
	@Expose
	@Column(name = "patients")
	private Integer patients;
	
	@Expose
	@Column(nullable = true, name = "fund")
	private BigDecimal fund;
	
	@Expose
	@Column(nullable = true, name = "prof")
	private BigDecimal prof;
	
	@Expose
	@Column(nullable = true, name = "inst")
	private BigDecimal inst;
	
	@Expose
	@Column(nullable = true, name = "pharmacy")
	private BigDecimal pharmacy;

	@Column(name = "file_id", nullable = false)
	private Integer fileId;

	/**
	 * 
	 */
	public MedicalLossRatio() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public MedicalLossRatio(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	public Insurance getInsId() {
		return insId;
	}

	public void setInsId(Insurance insId) {
		this.insId = insId;
	}

	public Provider getPrvdr() {
		return prvdr;
	}

	public void setPrvdr(Provider prvdr) {
		this.prvdr = prvdr;
	}

	public Date getReportGenDate() {
		return reportGenDate;
	}

	public void setReportGenDate(Date reportGenDate) {
		this.reportGenDate = reportGenDate;
	}

	public Integer getActivityMonth() {
		return activityMonth;
	}

	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	public Integer getPatients() {
		return patients;
	}

	public void setPatients(Integer patients) {
		this.patients = patients;
	}

	public BigDecimal getFund() {
		return fund;
	}

	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}

	public BigDecimal getProf() {
		return prof;
	}

	public void setProf(BigDecimal prof) {
		this.prof = prof;
	}

	public BigDecimal getInst() {
		return inst;
	}

	public void setInst(BigDecimal inst) {
		this.inst = inst;
	}

	public BigDecimal getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(BigDecimal pharmacy) {
		this.pharmacy = pharmacy;
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
		if (!(object instanceof MedicalLossRatio)) {
			return false;
		}
		MedicalLossRatio other = (MedicalLossRatio) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.AttPhysician[ id=" + id + " ]";
	}

}
