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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileTypeService#deleteById(java.lang.Integer)
	 */
	@Override
	public FileType deleteById(final Integer id) {
		return fileTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileTypeService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public FileType findById(final Integer id) {
		return fileTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FileTypeService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return fileTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.FileTypeService#getPage(int, int
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return fileTypeDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileTypeService#save(com.pfchoice.core.entity.
	 * FileType)
	 */
	@Override
	public FileType save(final FileType bean) {
		return fileTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileTypeService#update(com.pfchoice.core.entity
	 * .FileType)
	 */
	@Override
	public FileType update(final FileType bean) {
		Updater<FileType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return fileTypeDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.FileTypeService#findByCode(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public FileType findByCode(final String code) {
		return fileTypeDao.findByCode(code);
	}

	/**
	 * @param insId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination findByInsId(final Integer insId,final Integer pageNo, final Integer pageSize) {
		return fileTypeDao.findByInsId(insId, pageNo, pageSize);
	}

	
	
}
