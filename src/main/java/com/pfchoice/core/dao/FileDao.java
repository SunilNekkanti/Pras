package com.pfchoice.core.dao;

import com.pfchoice.core.entity.File;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FileDao
{

	File deleteById(Integer id);

	File findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    File save(File bean);

    File updateByUpdater(Updater<File> updater);

}
