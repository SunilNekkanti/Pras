package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Gender;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface GenderDao
{

	Gender deleteById(Byte id);

	Gender findById(Byte id);

    Pagination getPage(int pageNo, int pageSize);

    Gender save(Gender bean);

    Gender updateByUpdater(Updater<Gender> updater);
    
    List<Gender> findAll();

}
