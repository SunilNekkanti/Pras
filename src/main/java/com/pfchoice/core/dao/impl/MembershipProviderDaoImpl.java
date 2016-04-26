package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.MembershipProviderDao;
import com.pfchoice.core.entity.MembershipProvider;

/**
 *
 * @author Sarath
 */
@Repository
public class MembershipProviderDaoImpl extends HibernateBaseDao<MembershipProvider, Integer>
		implements MembershipProviderDao {

	@Override
	public Pagination getPage(final int pageNo, final int pageSize) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("activeInd", 'Y'));
		return findByCriteria(crit, pageNo, pageSize);
	}

	@Override
	public MembershipProvider findById(final Integer id) {
		return get(id);
	}

	@Override
	public MembershipProvider save(final MembershipProvider bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public MembershipProvider deleteById(final Integer id) {
		MembershipProvider entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MembershipProvider> getEntityClass() {
		return MembershipProvider.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembershipProvider> findAllByMbrId(final Integer id) {
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrPrvdr").createAlias("mbrPrvdr.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrPrvdr.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));

		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public MembershipProvider findByMbrId(final Integer id) {
		MembershipProvider dbMembershipProvider = null;
		Criteria cr = getSession().createCriteria(getEntityClass(), "mbrPrvdr").createAlias("mbrPrvdr.mbr", "mbr")
				.add(Restrictions.eq("mbr.id", id)).add(Restrictions.eq("mbrPrvdr.activeInd", 'Y'))
				.add(Restrictions.eq("mbr.activeInd", 'Y'));
		List<MembershipProvider> list = cr.list();
		if(!list.isEmpty()){
			dbMembershipProvider = list.get(0);
		}
		return dbMembershipProvider;
	}
}
