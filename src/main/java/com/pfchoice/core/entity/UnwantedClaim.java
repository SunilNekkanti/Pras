package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Component
public class UnwantedClaim  extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private String firstName;

	@Expose
	private String lastName;

	@Expose
	private String gender;
	
	@Expose
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Expose
	private String claimType;
	
	@Expose
	private Double unwantedClaims;

	/**
	 * 
	 */
	public UnwantedClaim() {
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Double getUnwantedClaims() {
		return unwantedClaims;
	}

	public void setUnwantedClaims(Double unwantedClaims) {
		this.unwantedClaims = unwantedClaims;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	
}
