package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Ethinicity;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EthinicityService {

	Ethinicity deleteById(Byte id);

	Ethinicity findById(Byte id);

	Pagination getPage(int pageNo, int pageSize);

	Ethinicity save(Ethinicity bean);

	Ethinicity update(Ethinicity bean);

	List<Ethinicity> findAll();
}
