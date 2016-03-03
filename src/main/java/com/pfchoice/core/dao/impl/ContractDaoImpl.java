package com.pfchoice.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pfchoice.core.dao.ContractDao;
import com.pfchoice.core.entity.Contract;

/**
 *
 * @author Sarath
 */
@Repository
public class ContractDaoImpl extends HibernateBaseDao<Contract, Integer> implements ContractDao
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContractDaoImpl.class
        .getName());

    @Override
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    public Contract findById(final Integer id)
    {
    	Contract entity = get(id);
        return entity;
    }

    @Override
    public Contract save(final Contract bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Contract deleteById(final Integer id)
    {
//        throw new UnsupportedOperationException();
    	Contract entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Contract> getEntityClass()
    {
        return Contract.class;
    }

    @SuppressWarnings("unchecked")
 	public List<Contract> findAllContractsByRefId(final String refString, final Integer id)
     {
    	String refRestrictionString = null;
    	 if("provider".equals(refString)){
				refRestrictionString = "refContract.prvdr.id";
			}else if("insurance".equals(refString)) {
				refRestrictionString = "refContract.ins.id";
			}
    	 
    	
     	Criteria cr = getSession().createCriteria(getEntityClass(), "contract");
     	
     			
     	if(refRestrictionString != null)
     	{
     		cr.createAlias("contract.referenceContract","refContract");
     		cr.add(Restrictions.eq(refRestrictionString, id));
     	}		
     	List<Contract> list = cr.list();
     	return list;
     }
    
    @SuppressWarnings("unchecked")
 	public Contract findActiveContractByRefId(final String refString, final Integer id)
     {
    	Contract contract  = null;
    	String refRestrictionString = null;
    	if("provider".equals(refString)){
				refRestrictionString = "refContract.prvdr.id";
		}else if("insurance".equals(refString)) {
				refRestrictionString = "refContract.ins.id";
		}
				
     	Criteria cr = getSession().createCriteria(getEntityClass(), "contract");
     	if(refRestrictionString != null)
     	{
     			cr.createAlias("contract.referenceContract","refContract");
     			cr.add(Restrictions.eq(refRestrictionString, id));
     	}		
   				
       			cr.add(Restrictions.eq("contract.activeInd", 'Y'));
     	List<Contract> list = cr.list();
     	if(list.size()>0)
     		contract =list.get(0);
     	return contract;
     }
}
