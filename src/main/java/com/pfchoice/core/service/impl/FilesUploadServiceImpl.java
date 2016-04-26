package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FilesUploadDao;
import com.pfchoice.core.entity.FilesUpload;
import com.pfchoice.core.service.FilesUploadService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FilesUploadServiceImpl implements FilesUploadService {

	@Autowired
	private FilesUploadDao filesUploadDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.FilesUploadService#deleteById(java.lang.Integer)
	 */
	@Override
	public FilesUpload deleteById(final Integer id) {
		return filesUploadDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.FilesUploadService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FilesUpload findById(final Integer id) {
		return filesUploadDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.FilesUploadService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return filesUploadDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.FilesUploadService#save(com.pfchoice.core.entity.FilesUpload)
	 */
	@Override
	public FilesUpload save(final FilesUpload bean) {
		return filesUploadDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.FilesUploadService#update(com.pfchoice.core.entity.FilesUpload)
	 */
	@Override
	public FilesUpload update(final FilesUpload bean) {
		Updater<FilesUpload> updater = new Updater<>(bean);
		return filesUploadDao.updateByUpdater(updater);
	}

}
