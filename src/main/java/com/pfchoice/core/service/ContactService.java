package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Contact;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ContactService
{

	Contact deleteById(Integer id);

	Contact findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Contact save(Contact bean);

    Contact update(Contact bean);

    List<Contact> findAllContactsByRefId(String refString, Integer id);
    
    Contact findActiveContactByRefId(String refString, Integer id);

}
