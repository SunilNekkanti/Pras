package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ContractDao;
import com.pfchoice.core.entity.Contract;

/**
 *
 * @author Sarath
 */
@Repository
public class ContractDaoImpl extends HibernateBaseDao<Contract, Integer> implements ContractDao {

	static final String REF_CONTRACT_PRVDR = "refContract.prvdr.id";
	static final String REF_CONTRACT_INS = "refContract.ins.id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContractDao#getPage(int, int)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContractDao#findById(java.lang.Integer)
	 */
	@Override
	public Contract findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.ContractDao#save(com.pfchoice.core.entity.Contract)
	 */
	@Override
	public Contract save(final Contract bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContractDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Contract deleteById(final Integer id) {
		Contract entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Contract> getEntityClass() {
		return Contract.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.ContractDao#findAllContractsByRefId(java.lang.
	 * String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> findAllContractsByRefId(final String refString, final Integer id) {
		String refRestrictionString = null;

		Criteria cr = getSession().createCriteria(getEntityClass(), "contract");
		cr.createAlias("contract.referenceContract", "refContract");
		if ("provider".equals(refString)) {
			refRestrictionString = REF_CONTRACT_PRVDR;
			cr.add(Restrictions.isNull(REF_CONTRACT_INS));
		} else if ("providerInsurance".equals(refString)) {
			refRestrictionString = REF_CONTRACT_PRVDR;
			cr.add(Restrictions.isNotNull(REF_CONTRACT_INS));
		} else if ("insurance".equals(refString)) {
			refRestrictionString = REF_CONTRACT_INS;
			cr.add(Restrictions.isNull(REF_CONTRACT_PRVDR));
		} else if ("insuranceProvider".equals(refString)) {
			refRestrictionString = REF_CONTRACT_INS;
			cr.add(Restrictions.isNotNull(REF_CONTRACT_PRVDR));
		}

		if (refRestrictionString != null) {
			cr.add(Restrictions.eq(refRestrictionString, id));
		}
		cr.add(Restrictions.eq("contract.activeInd", 'Y'));
		cr.add(Restrictions.eq("refContract.activeInd", 'Y'));
		return cr.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.ContractDao#findActiveContractByRefId(java.lang.
	 * String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Contract findActiveContractByRefId(final String refString, final Integer id) {
		Contract contract = null;
		String refRestrictionString = null;
		if ("provider".equals(refString)) {
			refRestrictionString = "prvdr.id";
		} else if ("insurance".equals(refString)) {
			refRestrictionString = REF_CONTRACT_INS;
		}

		Criteria cr = getSession().createCriteria(getEntityClass(), "contract");
		if (refRestrictionString != null) {
			cr.createAlias("contract.referenceContract", "refContract");
			cr.createAlias("refContract.prvdr", "prvdr");
			cr.add(Restrictions.eq(refRestrictionString, id));
		}

		cr.add(Restrictions.eq("contract.activeInd", 'Y'));
		List<Contract> list = cr.list();
		if (!list.isEmpty())
			contract = list.get(0);
		return contract;
	}
}
