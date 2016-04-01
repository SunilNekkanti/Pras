package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.FilesUploadDao;
import com.pfchoice.core.entity.FilesUpload;
import com.pfchoice.core.service.FilesUploadService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class FilesUploadServiceImpl implements FilesUploadService
{

    @Autowired
    private FilesUploadDao filesUploadDao;

    @Override
    public FilesUpload deleteById(final Integer id)
    {
        //Used for transaction test
        return filesUploadDao.deleteById(id);
//        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public FilesUpload findById(final Integer id)
    {
        return filesUploadDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(final int pageNo, final int pageSize)
    {
        return filesUploadDao.getPage(pageNo, pageSize);
    }

    @Override
    public FilesUpload save(final FilesUpload bean)
    {
        //Used for transaction test
        return filesUploadDao.save(bean);
//        this.deleteById(1);
//        return null;
    }

    @Override
    public FilesUpload update(final FilesUpload bean)
    {
        Updater<FilesUpload> updater = new Updater<>(bean);
        return filesUploadDao.updateByUpdater(updater);
    }

}
