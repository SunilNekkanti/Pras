package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FilesUpload;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FilesUploadDao {

	FilesUpload deleteById(Integer id);

	FilesUpload findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	FilesUpload save(FilesUpload bean);

	FilesUpload updateByUpdater(Updater<FilesUpload> updater);

}
