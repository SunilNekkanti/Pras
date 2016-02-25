package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.ZipCode;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ZipCodeDao
{

	ZipCode deleteById(Integer id);

	ZipCode findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    ZipCode save(ZipCode bean);

    ZipCode updateByUpdater(Updater<ZipCode> updater);
    
    List<ZipCode> findAll();
  
    List<ZipCode> findByStateCode(Integer stateCode);
}
