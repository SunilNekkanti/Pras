package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ProviderDao;
import com.pfchoice.core.entity.Provider;

/**
 *
 * @author Mohanasundharam
 */
@Repository
public class ProviderDaoImpl extends HibernateBaseDao<Provider, Integer> implements ProviderDao {

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProviderDao#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", new Character('Y')));

		Disjunction or = Restrictions.disjunction();

		if (sSearch != null && !"".equals(sSearch)) {
			Criterion name = Restrictions.ilike("name", "%" + sSearch + "%");
			Criterion code = Restrictions.ilike("code", "%" + sSearch + "%");

			or.add(name);
			or.add(code);
			crit.add(or);
		}

		if (sort != null && !"".equals(sort)) {
			if (sortdir != null && !"".equals(sortdir) && "desc".equals(sortdir)) {
				crit.addOrder(Order.desc(sort));
			} else {
				crit.addOrder(Order.asc(sort));
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return findByCriteria(crit, pageNo, pageSize);

	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProviderDao#findById(java.lang.Integer)
	 */
	@Override
	public Provider findById(final Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProviderDao#save(com.pfchoice.core.entity.Provider)
	 */
	@Override
	public Provider save(final Provider bean) {
		getSession().save(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProviderDao#deleteById(java.lang.Integer)
	 */
	@Override
	public Provider deleteById(final Integer id) {
		Provider entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see ml.rugal.sshcommon.hibernate.HibernateBaseDao#getEntityClass()
	 */
	@Override
	protected Class<Provider> getEntityClass() {
		return Provider.class;
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.dao.ProviderDao#findByInsId(java.lang.Integer)
	 */
	@Override
	public Pagination findByInsId(final Integer id) {

		Criteria crit = createCriteria();
		crit.createAlias("refContracts", "refContract");
		crit.createAlias("refContract.ins", "ins");
		crit.add(Restrictions.eq("activeInd", new Character('Y')));
		crit.add(Restrictions.eq("refContract.activeInd", new Character('Y')));
		crit.add(Restrictions.eq("ins.id", id));

		return findByCriteria(crit, 0, 200);
	}
}
