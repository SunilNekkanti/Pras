package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FileDao;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.service.FileService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FileService#deleteById(java.lang.Integer)
	 */
	@Override
	public File deleteById(final Integer id) {
		return fileDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FileService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public File findById(final Integer id) {
		return fileDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FileService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return fileDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileService#save(com.pfchoice.core.entity.File)
	 */
	@Override
	public File save(final File bean) {
		return fileDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileService#update(com.pfchoice.core.entity.
	 * File)
	 */
	@Override
	public File update(final File bean) {
		Updater<File> updater = new Updater<>(bean);
		return fileDao.updateByUpdater(updater);
	}

}
