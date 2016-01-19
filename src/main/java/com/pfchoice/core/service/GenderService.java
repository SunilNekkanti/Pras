package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Gender;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface GenderService
{

	Gender deleteById(Byte id);

	Gender findById(Byte id);

    Pagination getPage(int pageNo, int pageSize);

    Gender save(Gender bean);

    Gender update(Gender bean);

    List<Gender> findAll();
}
