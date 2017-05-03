package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.LeadMembershipProviderDao;
import com.pfchoice.core.entity.LeadMembershipProvider;

/**
 *
 * @author Sarath
 */
@Repository
public class LeadMembershipProviderDaoImpl extends HibernateBaseDao<LeadMembershipProvider, Integer>
		implements LeadMembershipProviderDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipProviderDao#getPage(int, int)
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
	 * @see
	 * com.pfchoice.core.dao.LeadMembershipProviderDao#findById(java.lang.Integer)
	 */
	@Override
	public LeadMembershipProvider findById(final Integer id) {
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.LeadMembershipProviderDao#save(com.pfchoice.core.entity
	 * .LeadMembershipProvider)
	 */
	@Override
	public LeadMembershipProvider save(final LeadMembershipProvider bean) {
		getSession().save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.LeadMembershipProviderDao#deleteById(java.lang.Integer)
	 */
	@Override
	public LeadMembershipProvider deleteById(final Integer id) {
		LeadMembershipProvider entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<LeadMembershipProvider> getEntityClass() {
		return LeadMembershipProvider.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.dao.LeadMembershipProviderDao#findAllByMbrId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LeadMembershipProvider> findAllByMbrId(final Integer id) {
		Criteria crit = createCriteria().createAlias("leadMbr", "leadMbr")
				.createAlias("prvdr", "prvdr")
				.add(Restrictions.eq("leadMbr.id", id))
				.add(Restrictions.eq("prvdr.activeInd", 'Y'))
				.add(Restrictions.eq("leadMbr.activeInd", 'Y'))
				.add(Restrictions.eq("activeInd", 'Y'));

		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.dao.LeadMembershipProviderDao#findByMbrId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LeadMembershipProvider findByMbrId(final Integer id) {
		LeadMembershipProvider dbMembershipProvider = null;
		Criteria cr = getSession().createCriteria(getEntityClass(), "leadMbrPrvdr").createAlias("leadMbrPrvdr.leadMbr", "leadMbr")
				.add(Restrictions.eq("leadMbr.id", id)).add(Restrictions.eq("leadMbrPrvdr.activeInd", 'Y'))
				.add(Restrictions.eq("leadMbr.activeInd", 'Y'));
		List<LeadMembershipProvider> list = cr.list();
		if (!list.isEmpty()) {
			dbMembershipProvider = list.get(0);
		}
		return dbMembershipProvider;
	}
}
