package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FileType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FileTypeDao
{

	FileType deleteById(Integer id);

	FileType findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    FileType save(FileType bean);

    FileType updateByUpdater(Updater<FileType> updater);

}
