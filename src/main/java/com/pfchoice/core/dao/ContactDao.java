package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Contact;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ContactDao
{

	Contact deleteById(Integer id);

	Contact findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Contact save(Contact bean);

    Contact updateByUpdater(Updater<Contact> updater);

}
