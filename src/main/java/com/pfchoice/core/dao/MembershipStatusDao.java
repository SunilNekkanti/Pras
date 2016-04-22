package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipStatus;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipStatusDao {

	MembershipStatus deleteById(Byte id);

	MembershipStatus findById(Byte id);

	Pagination getPage(int pageNo, int pageSize);

	MembershipStatus save(MembershipStatus bean);

	MembershipStatus updateByUpdater(Updater<MembershipStatus> updater);

	List<MembershipStatus> findAll();
}
