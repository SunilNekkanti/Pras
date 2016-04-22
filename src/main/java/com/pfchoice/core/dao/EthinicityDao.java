package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Ethinicity;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface EthinicityDao {

	Ethinicity deleteById(Byte id);

	Ethinicity findById(Byte id);

	Pagination getPage(int pageNo, int pageSize);

	Ethinicity save(Ethinicity bean);

	Ethinicity updateByUpdater(Updater<Ethinicity> updater);

	List<Ethinicity> findAll();
}
