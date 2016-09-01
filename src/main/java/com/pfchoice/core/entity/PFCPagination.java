package com.pfchoice.core.entity;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.pfchoice.core.entity.MembershipCountPerHedisRule;

import ml.rugal.sshcommon.page.Pagination;

public class PFCPagination extends Pagination {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private List<MembershipCountPerHedisRule> mbrCountPerHedisRuleList;

	@Expose
	private Pagination pagination;
	
	public PFCPagination() {
		super();
	}
	
	public PFCPagination(Pagination pagination) {
		super();
		this.pagination = pagination;
	}

	public List<MembershipCountPerHedisRule> getMbrCountPerHedisRuleList() {
		return mbrCountPerHedisRuleList;
	}

	public void setMbrCountPerHedisRuleList(List<MembershipCountPerHedisRule> mbrCountPerHedisRuleList) {
		this.mbrCountPerHedisRuleList = mbrCountPerHedisRuleList;
	}
	
	
}
