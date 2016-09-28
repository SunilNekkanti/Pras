package com.pfchoice.core.entity;

import java.io.Serializable;

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
	private Integer reportMonth;

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
	 * @return the reportMonth
	 */
	public Integer getReportMonth() {
		return reportMonth;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}

 
}
