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

	@Override
	public Provider findById(final Integer id) {
		return get(id);
	}

	@Override
	public Provider save(final Provider bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Provider deleteById(final Integer id) {
		Provider entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Provider> getEntityClass() {
		return Provider.class;
	}

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
