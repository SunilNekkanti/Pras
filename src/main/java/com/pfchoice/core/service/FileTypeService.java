package com.pfchoice.core.service;

import com.pfchoice.core.entity.FileType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FileTypeService
{

	FileType deleteById(Integer id);

	FileType findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    FileType save(FileType bean);

    FileType update(FileType bean);

}
