/**
 * 
 */
package com.pfchoice.form;

import java.sql.Date;


/**
 * @author sarath
 *
 */
public class MembershipForm {

	private String firstName;
    private String lastName;
    private Byte genderId;
    private Integer countyCode;
    private Date dob;
    private String medicaidNo;
    private String medicareNo;
    private Integer fileId;
    private char activeInd;
    private Byte status;
    private Byte ethinicCode;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the genderId
	 */
	public Byte getGenderId() {
		return genderId;
	}
	/**
	 * @param genderId the genderId to set
	 */
	public void setGenderId(Byte genderId) {
		this.genderId = genderId;
	}
	/**
	 * @return the countyCode
	 */
	public Integer getCountyCode() {
		return countyCode;
	}
	/**
	 * @param countyCode the countyCode to set
	 */
	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the medicaidNo
	 */
	public String getMedicaidNo() {
		return medicaidNo;
	}
	/**
	 * @param medicaidNo the medicaidNo to set
	 */
	public void setMedicaidNo(String medicaidNo) {
		this.medicaidNo = medicaidNo;
	}
	/**
	 * @return the medicareNo
	 */
	public String getMedicareNo() {
		return medicareNo;
	}
	/**
	 * @param medicareNo the medicareNo to set
	 */
	public void setMedicareNo(String medicareNo) {
		this.medicareNo = medicareNo;
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
	/**
	 * @return the activeInd
	 */
	public char getActiveInd() {
		return activeInd;
	}
	/**
	 * @param activeInd the activeInd to set
	 */
	public void setActiveInd(char activeInd) {
		this.activeInd = activeInd;
	}
	/**
	 * @return the status
	 */
	public Byte getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}
	/**
	 * @return the ethinicCode
	 */
	public Byte getEthinicCode() {
		return ethinicCode;
	}
	/**
	 * @param ethinicCode the ethinicCode to set
	 */
	public void setEthinicCode(Byte ethinicCode) {
		this.ethinicCode = ethinicCode;
	}
	
}
