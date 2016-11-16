package com.pfchoice.core.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
public class MembershipClaimsUnwanted implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String claimType;
	
	@Expose
	private String firstName;
 
	@Expose
	private String lastName;
	
	@Expose
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Expose
	private Character gender;
	
	@Expose
	private double unwantedClaims;

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public double getUnwantedClaims() {
		return unwantedClaims;
	}

	public void setUnwantedClaims(double unwantedClaims) {
		this.unwantedClaims = unwantedClaims;
	}
	
}
