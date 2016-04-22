package com.pfchoice.core.service;

import com.pfchoice.core.entity.FilesUpload;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FilesUploadService {

	FilesUpload deleteById(Integer id);

	FilesUpload findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	FilesUpload save(FilesUpload bean);

	FilesUpload update(FilesUpload bean);

}
