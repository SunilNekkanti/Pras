package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
public class MedicalLossRatioGenerateDate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Long count;
 
	@Expose
	private Integer insId;
 
	@Expose
	private Integer prvdrId;
	
	@Expose
	private Date reportDate;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	
	/**
	 * @return the insId
	 */
	public Integer getInsId() {
		return insId;
	}

	/**
	 * @param insId the insId to set
	 */
	public void setInsId(Integer insId) {
		this.insId = insId;
	}

	/**
	 * @return the prvdrId
	 */
	public Integer getPrvdrId() {
		return prvdrId;
	}

	/**
	 * @param prvdrId the prvdrId to set
	 */
	public void setPrvdrId(Integer prvdrId) {
		this.prvdrId = prvdrId;
	}

	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

 
}
