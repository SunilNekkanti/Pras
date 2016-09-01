package com.pfchoice.core.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
public class MembershipCountPerHedisRule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Long count;
 
	@Expose
	private Integer hedisRuleId;
 
	@Expose
	private Character activeInd;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	public Integer getHedisRuleId() {
		return hedisRuleId;
	}
	
	public void setHedisRuleId(Integer hedisRuleId) {
		this.hedisRuleId = hedisRuleId;
	}
	
	public Character getActiveInd() {
		return activeInd;
	}
	
	public void setActiveInd(Character activeInd) {
		this.activeInd = activeInd;
	}
 
}
