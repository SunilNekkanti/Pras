package com.pfchoice.core.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.google.gson.annotations.Expose;
import com.pfchoice.core.entity.Insurance;

/**
 *
 * @author Mohanasundharam
 */
@Entity(name = "reference_contract")
public class ReferenceContract extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ref_contract_Id", nullable = false)
	private Integer id;

	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
	private Insurance ins;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", referencedColumnName = "prvdr_id")
	private Provider prvdr;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "referenceContract")
	private Contract contract;

	/**
	 * 
	 */
	public ReferenceContract() {
		super();
	}

	/**
	 * @param id
	 */
	public ReferenceContract(final Integer id) {
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
	 * @return the ins
	 */
	public Insurance getIns() {
		return ins;
	}

	/**
	 * @param ins
	 *            the ins to set
	 */
	public void setIns(final Insurance ins) {
		this.ins = ins;
	}

	/**
	 * @return the prvdr
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr
	 *            the prvdr to set
	 */
	public void setPrvdr(Provider prvdr) {
		this.prvdr = prvdr;
	}

	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract
	 *            the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ReferenceContract)) {
			return false;
		}
		ReferenceContract other = (ReferenceContract) object;
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
