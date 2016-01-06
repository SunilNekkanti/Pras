package com.pfchoice.core.service;

import com.pfchoice.core.entity.File;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FileService
{

	File deleteById(Integer id);

	File findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    File save(File bean);

    File update(File bean);

}
