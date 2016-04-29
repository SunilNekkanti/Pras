package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity(name = "contract")
public class Contract extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "contract_Id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "contract_NBR")
	private String contractNBR;

	@Expose
	@Column(name = "PMPM")
	private Double pmpm;

	@Expose
	@Column(name = "start_date")
	private Date startDate;

	@Expose
	@Column(name = "end_date")
	private Date endDate;

	@Expose
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ref_contract_id", referencedColumnName = "ref_contract_id")
	private ReferenceContract referenceContract;

	@Expose
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "file_upload_id", referencedColumnName = "file_upload_id")
	private FilesUpload filesUpload;

	@Expose
	@Transient
	private Integer insId;

	/**
	 * 
	 */
	public Contract() {
		super();
	}

	/**
	 * @param id
	 */
	public Contract(final Integer id) {
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
	 * @return the contractNBR
	 */
	public String getContractNBR() {
		return contractNBR;
	}

	/**
	 * @param contractNBR
	 *            the contractNBR to set
	 */
	public void setContractNBR(final String contractNBR) {
		this.contractNBR = contractNBR;
	}

	/**
	 * @return the pmpm
	 */
	public Double getPmpm() {
		return pmpm;
	}

	/**
	 * @param pmpm
	 *            the pmpm to set
	 */
	public void setPmpm(Double pmpm) {
		this.pmpm = pmpm;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the referenceContract
	 */
	public ReferenceContract getReferenceContract() {
		return referenceContract;
	}

	/**
	 * @param referenceContract
	 *            the referenceContract to set
	 */
	public void setReferenceContract(final ReferenceContract referenceContract) {
		this.referenceContract = referenceContract;
	}

	/**
	 * @return the filesUpload
	 */
	public FilesUpload getFilesUpload() {
		return filesUpload;
	}

	/**
	 * @param filesUpload
	 *            the filesUpload to set
	 */
	public void setFilesUpload(FilesUpload filesUpload) {
		this.filesUpload = filesUpload;
	}

	/**
	 * @return the insId
	 */
	public Integer getInsId() {
		return insId;
	}

	/**
	 * @param insId
	 *            the insId to set
	 */
	public void setInsId(Integer insId) {
		this.insId = insId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Contract)) {
			return false;
		}
		Contract other = (Contract) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.Contract[ id=" + id + " ]";
	}

}
