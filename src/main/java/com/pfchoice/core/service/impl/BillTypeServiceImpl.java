package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.BillTypeDao;
import com.pfchoice.core.entity.BillType;
import com.pfchoice.core.service.BillTypeService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class BillTypeServiceImpl implements BillTypeService {

	@Autowired
	private BillTypeDao billTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillType#deleteById(java.lang.Integer)
	 */
	@Override
	public BillType deleteById(final Integer id) {
		return billTypeDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillType#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public BillType findById(final Integer id) {
		return billTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.BillType#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return billTypeDao.getPage(pageNo, pageSize);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.BillTypeService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return billTypeDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillTypeService#save(com.pfchoice.core.entity.
	 * Hospital)
	 */
	@Override
	public BillType save(final BillType bean) {
		return billTypeDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillTypeService#update(com.pfchoice.core.entity
	 * .Hospital)
	 */
	@Override
	public BillType update(final BillType bean) {
		Updater<BillType> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return billTypeDao.updateByUpdater(updater);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillTypeService#isNameUnique(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public boolean isDescriptionUnique(Integer id, String descriptionName) {
		BillType billType = findByDescription(descriptionName);
		return (billType == null || ((id != null) && (billType.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.BillTypeService#findByName(java.lang.
	 * String)
	 */
	@Override
	public BillType findByDescription(String billTypeDescription) {
		BillType billType = billTypeDao.findByDescription(billTypeDescription);
		return billType;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.BillTypeService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		return billTypeDao.loadData(fileId, tableName);
	}
}
