package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MedicalLossRatioDao;
import com.pfchoice.core.entity.MedicalLossRatio;
import com.pfchoice.core.service.MedicalLossRatioService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MedicalLossRatioServiceImpl implements MedicalLossRatioService {

	@Autowired
	private MedicalLossRatioDao medicalLossRatioDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#deleteById(java.lang.Integer)
	 */
	@Override
	public MedicalLossRatio deleteById(final Integer id) {
		return medicalLossRatioDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MedicalLossRatio findById(final Integer id) {
		return medicalLossRatioDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return medicalLossRatioDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return medicalLossRatioDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#save(com.pfchoice.core.entity.MedicalLossRatio)
	 */
	@Override
	public MedicalLossRatio save(final MedicalLossRatio bean) {
		return medicalLossRatioDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#update(com.pfchoice.core.entity.MedicalLossRatio)
	 */
	@Override
	public MedicalLossRatio update(final MedicalLossRatio bean) {
		Updater<MedicalLossRatio> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return medicalLossRatioDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MedicalLossRatioService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return medicalLossRatioDao.loadData(fileId);
	}


}
