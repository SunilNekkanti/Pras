package com.pfchoice.core.service;

import com.pfchoice.core.entity.Ethinicity;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EthinicityService
{

	Ethinicity deleteById(Byte id);

	Ethinicity findById(Byte id);

    Pagination getPage(int pageNo, int pageSize);

    Ethinicity save(Ethinicity bean);

    Ethinicity update(Ethinicity bean);

}
