package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Provider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderService
{

	Provider deleteById(Integer id);

	Provider findById(Integer id);

    Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

    Provider save(Provider bean);

    Provider update(Provider bean);
    
}
