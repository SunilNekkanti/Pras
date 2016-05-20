package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ContactDao;
import com.pfchoice.core.entity.Contact;
import com.pfchoice.core.service.ContactService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#deleteById(java.lang.Integer)
	 */
	@Override
	public Contact deleteById(final Integer id) {
		return contactDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Contact findById(final Integer id) {
		return contactDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return contactDao.getPage(pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.InsuranceService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return contactDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#save(com.pfchoice.core.entity.Contact)
	 */
	@Override
	public Contact save(final Contact bean) {
		return contactDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#update(com.pfchoice.core.entity.Contact)
	 */
	@Override
	public Contact update(final Contact bean) {
		Updater<Contact> updater = new Updater<>(bean);
		return contactDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#findAllContactsByRefId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Contact> findAllContactsByRefId(final String refString, final Integer id) {
		return contactDao.findAllContactsByRefId(refString, id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContactService#findActiveContactByRefId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Contact findActiveContactByRefId(final String refString, final Integer id) {
		return contactDao.findActiveContactByRefId(refString, id);
	}
}
