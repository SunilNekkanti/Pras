package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FileTypeDao;
import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.service.FileTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FileTypeServiceImpl implements FileTypeService {

	@Autowired
	private FileTypeDao fileTypeDao;

	@Override
	public FileType deleteById(final Integer id) {
		// Used for transaction test
		return fileTypeDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public FileType findById(final Integer id) {
		return fileTypeDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return fileTypeDao.getPage(pageNo, pageSize);
	}

	@Override
	public FileType save(final FileType bean) {
		// Used for transaction test
		return fileTypeDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public FileType update(final FileType bean) {
		Updater<FileType> updater = new Updater<>(bean);
		return fileTypeDao.updateByUpdater(updater);
	}

}
